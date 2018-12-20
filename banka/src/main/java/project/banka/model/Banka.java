package project.banka.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Banka {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;

	@Column
	public String swiftKod; // 8 : banka4kod + drzavaOznaka + NMOznaka

	@Column
	public String naziv;

	@Column
	public String port;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "banka", cascade = CascadeType.ALL)
	public List<Racun> racuni;

	public Banka() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Banka(String swiftKod, String naziv, String port, List<Racun> racuni) {
		super();
		this.swiftKod = swiftKod;
		this.naziv = naziv;
		this.port = port;
		this.racuni = racuni;
	}

	public String getSwiftKod() {
		return swiftKod;
	}

	public void setSwiftKod(String swiftKod) {
		this.swiftKod = swiftKod;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public List<Racun> getRacuni() {
		return racuni;
	}

	public void setRacuni(List<Racun> racuni) {
		this.racuni = racuni;
	}

}
