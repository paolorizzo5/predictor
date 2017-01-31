package com.paolorizzo.predictor.services.request;

import java.math.BigDecimal;

public class AddBetRequest extends SimpleRequest {
	
	private String eventDescription;
	
	private String bettypeDescription;
	
	private Integer amount;
	
	private BigDecimal moltiplicator;
	
	private String email;
	
	private String accountName;

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getBettypeDescription() {
		return bettypeDescription;
	}

	public void setBettypeDescription(String bettypeDescription) {
		this.bettypeDescription = bettypeDescription;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public BigDecimal getMoltiplicator() {
		return moltiplicator;
	}

	public void setMoltiplicator(BigDecimal moltiplicator) {
		this.moltiplicator = moltiplicator;
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
