package com.paolorizzo.predictor.services.request;

import java.math.BigDecimal;

public class AddProspectRequest extends SimpleRequest{
	
	
	private String name;
	
	private String email;
	
	private BigDecimal initialAmount;
	
	private Integer duration;
	
	private BigDecimal dailyPercentageExpected;
	
	private Integer stepFrequency;
	
	private String accountName;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getInitialAmount() {
		return initialAmount;
	}

	public void setInitialAmount(BigDecimal initialAmount) {
		this.initialAmount = initialAmount;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public BigDecimal getDailyPercentageExpected() {
		return dailyPercentageExpected;
	}

	public void setDailyPercentageExpected(BigDecimal dailyPercentageExpected) {
		this.dailyPercentageExpected = dailyPercentageExpected;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getStepFrequency() {
		return stepFrequency;
	}

	public void setStepFrequency(Integer stepFrequency) {
		this.stepFrequency = stepFrequency;
	} 
	
	

}
