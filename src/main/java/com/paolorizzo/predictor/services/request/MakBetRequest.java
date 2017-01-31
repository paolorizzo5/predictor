package com.paolorizzo.predictor.services.request;

public class MakBetRequest {
	
	private Long insertDate;
	
	private Boolean isWinning;
	
	private String email;
	
	private String accountName;

	public Long getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Long insertDate) {
		this.insertDate = insertDate;
	}

	public Boolean getIsWinning() {
		return isWinning;
	}

	public void setIsWinning(Boolean isWinning) {
		this.isWinning = isWinning;
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
