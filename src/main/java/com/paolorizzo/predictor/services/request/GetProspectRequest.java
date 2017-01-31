package com.paolorizzo.predictor.services.request;

public class GetProspectRequest extends SimpleRequest{
	
	
	private String email;
		
	private String accountName;

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
