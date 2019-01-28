package project.banka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RezultatTransakcije {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String issuerOrderId;

	@Column
	private String issuerTimestamp;

	@Column
	private boolean rezultat;

	@Column
	protected String acquirerOrderId;

	@Column
	protected String acquirerTimestamp;

	@Column
	protected String acquirerSwiftCode;

	@Column
	protected Long uplataId;

	public RezultatTransakcije() {
		super();
	}

	public RezultatTransakcije(String issuerOrderId, String issuerTimestamp, boolean rezultat, String acquirerOrderId,
			String acquirerTimestamp, String acquirerSwiftCode, Long uplataId) {
		super();
		this.issuerOrderId = issuerOrderId;
		this.issuerTimestamp = issuerTimestamp;
		this.rezultat = rezultat;
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.acquirerSwiftCode = acquirerSwiftCode;
		this.uplataId = uplataId;
	}

	public RezultatTransakcije(boolean rezultat, String acquirerOrderId, String acquirerTimestamp,
			String acquirerSwiftCode, Long uplataId) {
		super();
		this.rezultat = rezultat;
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.acquirerSwiftCode = acquirerSwiftCode;
		this.uplataId = uplataId;
	}

	public String getIssuerOrderId() {
		return issuerOrderId;
	}

	public void setIssuerOrderId(String issuerOrderId) {
		this.issuerOrderId = issuerOrderId;
	}

	public String getIssuerTimestamp() {
		return issuerTimestamp;
	}

	public void setIssuerTimestamp(String issuerTimestamp) {
		this.issuerTimestamp = issuerTimestamp;
	}

	public boolean isRezultat() {
		return rezultat;
	}

	public void setRezultat(boolean rezultat) {
		this.rezultat = rezultat;
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

}
