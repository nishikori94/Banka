package project.banka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import project.banka.model.RezultatTransakcije;
import project.banka.model.Transakcija;
import project.banka.model.Uplata;
import project.banka.service.PlacanjeService;

@RestController
@RequestMapping("/placanje")
@CrossOrigin(origins = "https://localhost:4202")
public class PlacanjeController {

	@Autowired
	private PlacanjeService placanjeService;

	@Autowired
	RestTemplate restTemplate;

	// generisanje PAYMENT_URL i PAYMENT_ID i vracanje linka ka frontu banke
	@PostMapping("/generisiUrl")
	@ResponseBody
	public String genrisiUrl(@RequestBody Uplata uplata) {
		System.out.println("[BANKA] Generisi url");
		String ret = placanjeService.generisiUrl(uplata);
		return ret;
	}

	// kada sa beka vatimo link sa payment_url i payment_id ka frontu, onda front
	// pri inicijalizaciji salje beku na proveru da li uplata postoji i je l'
	// aktivan link. Ako jeste vraca Uplatu(na osnovu payment_id) na front i
	// prikazuje front za unos podataka sa kartice.
	// nakon toga se na frontu kreira transakcija zajedno sa podacima sa kartice i
	// payment_id koja se salje na proveri /proveriPodatke
	@GetMapping(path = "/proveriUrl/{paymentUrl}/{paymentId}") // , produces = MediaType.APPLICATION_JSON_VALUE
	@ResponseBody
	public Uplata proveriUrl(@PathVariable("paymentUrl") String paymentUrl, @PathVariable("paymentId") Long paymentId) {
		System.out.println("[BANKA] Provera url-a");
		return placanjeService.proveriUrl(paymentUrl, paymentId);
	}

	@PostMapping("/proveriPodatke")
	@ResponseBody
	public String unesiPodatke(@RequestBody Transakcija transakcija) {
		System.out.println("[BANKA] Unos podataka sa kartice");
		transakcija.setNazivVlasnikaKartice(transakcija.getNazivVlasnikaKartice().trim());
		transakcija.setSigurnosniKod(transakcija.getSigurnosniKod().trim());
		transakcija.setPan(transakcija.getPan().trim());
		String url = placanjeService.proveriBanku(transakcija);
		return url;
	}

	// banka kupca prihvata transakciju od pcc-a(koji ga je nasao), proverava da li
	// je u redu, rezervise sredstva i vraca rezultat transakcije pcc-u
	@PostMapping("/proveriZahtev")
	public String proveriZahtev(@RequestBody Transakcija transakcija) {
		System.out.println("[BANKA] Provera zahteva dobijenog od PCC-a i prosledjivanje nazad PCC-u");
		RezultatTransakcije rz = placanjeService.kupacProveriZahtev(transakcija);
		final String putanja = "https://localhost:9098/transakcija/proslediOdgovor";
		return restTemplate.postForObject(putanja, rz, String.class);
	}

	@PostMapping("/obradiIshodTransakcije")
	public String obradiIshodTransakcije(@RequestBody RezultatTransakcije rezultatTransakcije) {
		System.out.println("[BANKA] Obradjivanje ishoda transakcije");
		return placanjeService.obradiIshodTransakcije(rezultatTransakcije);
	}

	@GetMapping("/invalidirajLink")
	public void invalidirajLink(@PathVariable("uplataId") Long uplataId) {
		System.out.println("[BANKA] Invalidacija linka uplate");
		placanjeService.invalidirajLinkUplate(uplataId);
	}

}
