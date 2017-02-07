package com.paolorizzo.predictor.dto;

import java.math.BigDecimal;

public class BetDto {
	
	private Long insertDate;
	
	private String eventDescription;
	
	private String bettypeDescription;
	
	private Integer amount;
	
	private BigDecimal moltiplicator;
	
	private String betStatus;

	public Long getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Long insertDate) {
		this.insertDate = insertDate;
	}

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

	public String getBetStatus() {
		return betStatus;
	}

	public void setBetStatus(String betStatus) {
		this.betStatus = betStatus;
	}
	
	

}
