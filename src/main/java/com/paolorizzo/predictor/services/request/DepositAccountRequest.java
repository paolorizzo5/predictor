package com.paolorizzo.predictor.services.request;

public class DepositAccountRequest extends SimpleRequest {
	
	
	private String accountName;
	
	private String amount;
	
	private String email;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
