package com.paolorizzo.predictor.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AccountStatsDto {
	
	private Long logDate;
	
	private BigDecimal amount;

	public Long getLogDate() {
		return logDate;
	}

	public void setLogDate(Long logDate) {
		this.logDate = logDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	

}
