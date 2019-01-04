package project.banka.service;

import project.banka.model.RezultatTransakcije;
import project.banka.model.Transakcija;
import project.banka.model.Uplata;

public interface PlacanjeService {

	public String generisiUrl(Uplata uplata);

	public String proveriBanku(Transakcija transakcija);

	public Uplata proveriUrl(String paymentUrl, Long paymentId);

	public RezultatTransakcije kupacProveriZahtev(Transakcija transakcija);

	public void invalidirajLinkUplate(Long uplataId);

	public String obradiIshodTransakcije(RezultatTransakcije rezultatTransakcije);
	
}
