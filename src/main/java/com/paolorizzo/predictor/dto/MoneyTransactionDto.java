package com.paolorizzo.predictor.dto;

import java.math.BigDecimal;


public class MoneyTransactionDto {
	
	private String insertDate;
	
	private String direction;
	
	private BigDecimal amount;
	
	private String reason;

	
	

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
	

}
