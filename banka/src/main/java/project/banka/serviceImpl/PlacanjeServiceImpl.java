package project.banka.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import project.banka.model.Banka;
import project.banka.model.Casopis;
import project.banka.model.Racun;
import project.banka.model.RezultatTransakcije;
import project.banka.model.Transakcija;
import project.banka.model.Uplata;
import project.banka.repository.CasopisRepository;
import project.banka.repository.RacunRepository;
import project.banka.repository.RezultatTransakcijeRepository;
import project.banka.repository.TransakcijaRepository;
import project.banka.repository.UplataRepository;
import project.banka.service.PlacanjeService;

@Service
public class PlacanjeServiceImpl implements PlacanjeService {

	@Autowired
	private CasopisRepository casopisRepository;

	@Autowired
	private UplataRepository uplataRepository;

	@Autowired
	private RacunRepository racunRep;

	@Autowired
	private TransakcijaRepository transakcijaRep;

	@Autowired
	private RezultatTransakcijeRepository rezultatTransakcijeRep;

	public String generisiUrl(Uplata uplata) {
		Casopis casopis = casopisRepository.findCasopisByMerchantId(uplata.getMerchantId());
		if (casopis.getMerchantPassword().equals(uplata.getMerchantPassword())) {
			String url = generatePaymentUrl(uplata.getMerchantOrderId());
			uplata.setUplataLink(url);
			uplata.setAktivanLink(true);
			uplata.setUplataId(uplata.id);
			uplata.setId(null);
			uplataRepository.save(uplata);
			url = url + "/" + uplata.getUplataId(); // stavljam id koji baza izgenerise kad se snimi uplata
			return url;
		} else {
			return uplata.getErrorUrl();
		}
	}

	private String generatePaymentUrl(Long id) {
		String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lower = upper.toLowerCase();
		String numbers = "1234567890";

		String characters = upper + lower + numbers;
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		int length = random.nextInt(100);

		while (builder.length() < length) {
			int index = (int) (random.nextFloat() * characters.length());
			builder.append(characters.charAt(index));
		}

		String ret = "http://localhost:4202/" + builder.toString();

		return ret;
	}

	@Override
	public Uplata proveriUrl(String paymentUrl, Long paymentId) {
		// TODO Auto-generated method stub
		Uplata uplata = uplataRepository.getUplataByUplataLinkContainingAndId(paymentUrl, paymentId);
		if (uplata != null && uplata.isAktivanLink()) {
			return uplata;
		}
		return null;
	}

	@Override
	public void proveriBanku(Transakcija transakcija) {
		Uplata uplata = uplataRepository.findById(transakcija.getUplataId()).get();
		Banka banka = casopisRepository.findCasopisByMerchantId(uplata.getMerchantId()).getRacun().getBanka();
		// TREBA NAMESTITI USLOV ZA BANKE. DA LI PRVE 3, 4 CIFRE ILI NESTO SLICNO; za
		// sad sam stavio prve 4 cifre da oznacavaju banku
		if (transakcija.getPan().substring(0, 4).equals(banka.getPort())) { // ISTA BANKA
			Racun racun = racunRep.findByBrojRacuna(transakcija.getPan());
			RezultatTransakcije rz = new RezultatTransakcije(false, transakcija.getAcquirerOrderId(),
					transakcija.getAcquirerTimestamp(), transakcija.getAcquirerSwiftCode(), transakcija.getUplataId());
			if (racun.getSigurnosniKod().equals(transakcija.getSigurnosniKod())) // mozda dodati proveru datuma i
																					// cardholder name
				if (Double.parseDouble(racun.getStanjeRacuna()) - Double.parseDouble(transakcija.getIznos()) > 0) {
					// rz.setRezultat(true);
					racun.setStanjeRacuna(Double.toString(
							Double.parseDouble(racun.getStanjeRacuna()) - Double.parseDouble(transakcija.getIznos())));
					racunRep.save(racun);
				}
			obradiIshodTransakcije(rz);
		} else { // NIJE ISTA BANKA - generisi acq_timestamp i acq_order_id i prosledi pcc-u
			String formattedDate = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			transakcija.setAcquirerTimestamp(formattedDate);
			transakcija.setAcquirerOrderId(generateAcquirerOrderId());
			final String putanja = "http://localhost:9098/transakcija/proslediZahtev";
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForObject(putanja, transakcija, Void.class);
		}
		// videti sta treba da vrati, verovatno rezultat transakcije sa linkovima i sl.

	}

	private String generateAcquirerOrderId() {
		String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lower = upper.toLowerCase();
		String numbers = "1234567890";
		String characters = upper + lower + numbers;
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		int length = 10;

		while (builder.length() < length) {
			int index = (int) (random.nextFloat() * characters.length());
			builder.append(characters.charAt(index));
		}

		return builder.toString();
	}

	@Override
	public RezultatTransakcije kupacProveriZahtev(Transakcija transakcija) {
		// TODO Auto-generated method stub
		Transakcija transakcijaZaId = transakcijaRep.save(transakcija);
		Racun racun = racunRep.findByBrojRacuna(transakcija.getPan());
		String issuerTimestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		RezultatTransakcije rz = new RezultatTransakcije(transakcijaZaId.getId().toString(), issuerTimestamp, false,
				transakcija.getAcquirerOrderId(), transakcija.getAcquirerTimestamp(),
				transakcija.getAcquirerSwiftCode(), transakcija.getUplataId());
		rz.setAcquirerSwiftCode(transakcija.getPan().substring(0, 4));
		if (racun.getSigurnosniKod().equals(transakcija.getSigurnosniKod())) {
			if (Double.parseDouble(racun.getStanjeRacuna()) - Double.parseDouble(transakcija.getIznos()) > 0) {
				rz.setRezultat(true);
				racun.setStanjeRacuna(Double.toString(
						Double.parseDouble(racun.getStanjeRacuna()) - Double.parseDouble(transakcija.getIznos())));
				racunRep.save(racun);
			}
		}

		return rz;
	}

	@Override
	public void invalidirajLinkUplate(RezultatTransakcije rezultatTransakcije) {
		Uplata uplata = uplataRepository.findUplataByUplataId(rezultatTransakcije.getUplataId());
		uplata.setAktivanLink(false);
		uplataRepository.save(uplata);
		rezultatTransakcijeRep.save(rezultatTransakcije);
	}

	@Override
	public void obradiIshodTransakcije(RezultatTransakcije rezultatTransakcije) {
		invalidirajLinkUplate(rezultatTransakcije);
		if (rezultatTransakcije.isRezultat()) {
			final String putanja = "http://localhost:9091/placanje/zavrsiUplatu/" + rezultatTransakcije.getUplataId();
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForObject(putanja, rezultatTransakcije, Void.class);
			return;
		}
		final String putanja = "http://localhost:9091/placanje/otkaziUplatu/" + rezultatTransakcije.getUplataId();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(putanja, rezultatTransakcije, Void.class);
	}

}
