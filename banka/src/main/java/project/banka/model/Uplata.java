package project.banka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Uplata {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column
	private long uplataId;

	@Column
	private String amount;

	@Column
	private Long merchantOrderId;

	@Column
	private String merchantTimestamp;

	@Column
	private String merchantId;

	@Column
	private String merchantPassword;

	@Column
	private String successUrl;

	@Column
	private String failedUrl;

	@Column
	private String errorUrl;

	@Column
	private String uplataLink;

	@Column
	private boolean aktivanLink;

	@Column
	private String bankaPort;

	public Uplata() {
	}

	public Uplata(String amount, Long merchantOrderId, String merchantTimestamp, String merchantId,
			String merchantPassword, String successUrl, String failedUrl, String errorUrl, String uplataLink,
			boolean aktivanLink, Long uplataId, String bankaPort) {
		super();
		this.amount = amount;
		this.merchantOrderId = merchantOrderId;
		this.merchantTimestamp = merchantTimestamp;
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
		this.successUrl = successUrl;
		this.failedUrl = failedUrl;
		this.errorUrl = errorUrl;
		this.uplataLink = uplataLink;
		this.aktivanLink = aktivanLink;
		this.uplataId = uplataId;
		this.bankaPort = bankaPort;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Long getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(Long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public String getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(String merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getFailedUrl() {
		return failedUrl;
	}

	public void setFailedUrl(String failedUrl) {
		this.failedUrl = failedUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}

	public String getUplataLink() {
		return uplataLink;
	}

	public void setUplataLink(String uplataLink) {
		this.uplataLink = uplataLink;
	}

	public boolean isAktivanLink() {
		return aktivanLink;
	}

	public void setAktivanLink(boolean aktivanLink) {
		this.aktivanLink = aktivanLink;
	}

	public long getUplataId() {
		return uplataId;
	}

	public void setUplataId(long uplataId) {
		this.uplataId = uplataId;
	}

	public String getBankaPort() {
		return bankaPort;
	}

	public void setBankaPort(String bankaPort) {
		this.bankaPort = bankaPort;
	}

}
