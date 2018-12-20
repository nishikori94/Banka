package project.banka.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Racun {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;

	@Column(unique = true, nullable = false)
	public String brojRacuna;

	@Column
	private String sigurnosniKod;

	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date datumVazenja;

	@Column
	private String stanjeRacuna;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "casopis_id", nullable = false)
	public Casopis casopis;

	@ManyToOne
	public Banka banka;

	public Racun() {
		super();
	}

	public Racun(String brojRacuna, String sigurnosniKod, Date datumVazenja, String stanjeRacuna, Casopis casopis,
			Banka banka) {
		super();
		this.brojRacuna = brojRacuna;
		this.sigurnosniKod = sigurnosniKod;
		this.datumVazenja = datumVazenja;
		this.stanjeRacuna = stanjeRacuna;
		this.casopis = casopis;
		this.banka = banka;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public String getSigurnosniKod() {
		return sigurnosniKod;
	}

	public void setSigurnosniKod(String sigurnosniKod) {
		this.sigurnosniKod = sigurnosniKod;
	}

	public Date getDatumVazenja() {
		return datumVazenja;
	}

	public void setDatumVazenja(Date datumVazenja) {
		this.datumVazenja = datumVazenja;
	}

	public String getStanjeRacuna() {
		return stanjeRacuna;
	}

	public void setStanjeRacuna(String stanjeRacuna) {
		this.stanjeRacuna = stanjeRacuna;
	}

	public Casopis getCasopis() {
		return casopis;
	}

	public void setCasopis(Casopis casopis) {
		this.casopis = casopis;
	}

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
	}

}
