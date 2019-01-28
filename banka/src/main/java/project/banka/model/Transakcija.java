package project.banka.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transakcija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	protected String pan;
	
	@Column
	protected String sigurnosniKod;
	
	@Column
	protected String nazivVlasnikaKartice;
	
	@Column
	protected Date datumVazenja;
	
	@Column
	protected String iznos;
	
	@Column
	protected String acquirerOrderId;
	
	@Column
	protected String acquirerTimestamp;
	
	@Column
	protected String acquirerSwiftCode;

	@Column
	protected Long uplataId;

	public Transakcija() {
		super();
	}

	public Transakcija(String pan, String sigurnosniKod, String nazivVlasnikaKartice, Date datumVazenja, String iznos,
			String acquirerOrderId, String acquirerTimestamp, String acquirerSwiftCode, Long uplataId) {
		super();
		this.pan = pan;
		this.sigurnosniKod = sigurnosniKod;
		this.nazivVlasnikaKartice = nazivVlasnikaKartice;
		this.datumVazenja = datumVazenja;
		this.iznos = iznos;
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.acquirerSwiftCode = acquirerSwiftCode;
		this.uplataId = uplataId;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getSigurnosniKod() {
		return sigurnosniKod;
	}

	public void setSigurnosniKod(String sigurnosniKod) {
		this.sigurnosniKod = sigurnosniKod;
	}

	public String getNazivVlasnikaKartice() {
		return nazivVlasnikaKartice;
	}

	public void setNazivVlasnikaKartice(String nazivVlasnikaKartice) {
		this.nazivVlasnikaKartice = nazivVlasnikaKartice;
	}

	public Date getDatumVazenja() {
		return datumVazenja;
	}

	public void setDatumVazenja(Date datumVazenja) {
		this.datumVazenja = datumVazenja;
	}

	public String getIznos() {
		return iznos;
	}

	public void setIznos(String iznos) {
		this.iznos = iznos;
	}

	public String getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(String acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}

	public String getAcquirerTimestamp() {
		return acquirerTimestamp;
	}

	public void setAcquirerTimestamp(String acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}

	public String getAcquirerSwiftCode() {
		return acquirerSwiftCode;
	}

	public void setAcquirerSwiftCode(String acquirerSwiftCode) {
		this.acquirerSwiftCode = acquirerSwiftCode;
	}

	public Long getUplataId() {
		return uplataId;
	}

	public void setUplataId(Long uplataId) {
		this.uplataId = uplataId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
