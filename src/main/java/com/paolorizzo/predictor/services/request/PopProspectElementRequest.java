package com.paolorizzo.predictor.services.request;

public class PopProspectElementRequest {
	
	private String prospectName;
	
	private Integer incremental;
	
	private String email;
	
	private String accountName;

	public String getProspectName() {
		return prospectName;
	}

	public void setProspectName(String prospectName) {
		this.prospectName = prospectName;
	}

	public Integer getIncremental() {
		return incremental;
	}

	public void setIncremental(Integer incremental) {
		this.incremental = incremental;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	

}
