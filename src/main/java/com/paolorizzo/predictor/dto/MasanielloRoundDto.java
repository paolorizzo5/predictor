package com.paolorizzo.predictor.dto;

import java.math.BigDecimal;
import java.util.List;

public class MasanielloRoundDto implements Comparable<MasanielloRoundDto> {
	
	
	private Integer roundId;
	
	private Integer matches;
	
	private Boolean success;
	
	private BigDecimal initialAmount;
	
	private BigDecimal finalAmount;
	
	private BigDecimal percentageInvested;
	
	private List<DirettaFixtureDto> fixtures;
	
	
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

	@Override
	public int compareTo(MasanielloRoundDto o) {
		return this.getRoundId().compareTo(o.getRoundId());
	}

	public List<DirettaFixtureDto> getFixtures() {
		return fixtures;
	}

	public void setFixtures(List<DirettaFixtureDto> fixtures) {
		this.fixtures = fixtures;
	}

	public BigDecimal getPercentageInvested() {
		return percentageInvested;
	}

	public void setPercentageInvested(BigDecimal percentageInvested) {
		this.percentageInvested = percentageInvested;
	}

}
