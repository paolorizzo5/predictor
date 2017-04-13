package com.paolorizzo.predictor.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.paolorizzo.predictor.hibernate.model.Masaniello;

public class MasanielloRoundDto {
	
	
	private Integer roundId;
	
	private Integer matches;
	
	private Boolean success;
	
	private BigDecimal initialAmount;
	
	private BigDecimal finalAmount;
	
	
	public Integer getMatches() {
		return matches;
	}

	public void setMatches(Integer matches) {
		this.matches = matches;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public BigDecimal getInitialAmount() {
		return initialAmount;
	}

	public void setInitialAmount(BigDecimal initialAmount) {
		this.initialAmount = initialAmount;
	}

	public BigDecimal getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(BigDecimal finalAmount) {
		this.finalAmount = finalAmount;
	}

	public Integer getRoundId() {
		return roundId;
	}

	public void setRoundId(Integer roundId) {
		this.roundId = roundId;
	}

}
