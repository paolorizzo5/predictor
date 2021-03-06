package com.paolorizzo.predictor.dto;

import java.math.BigDecimal;

public class ProspectElementDto {
	
	private Long startDate;
	
	private Long endDate;
	
	private Integer incremental;
	
	private BigDecimal expectedGoal;
	
	private BigDecimal previousExpectedGoal;
	
	private Boolean current;
	
	private Long terminationDate;

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public Integer getIncremental() {
		return incremental;
	}

	public void setIncremental(Integer incremental) {
		this.incremental = incremental;
	}

	public BigDecimal getExpectedGoal() {
		return expectedGoal;
	}

	public void setExpectedGoal(BigDecimal expectedGoal) {
		this.expectedGoal = expectedGoal;
	}


	public BigDecimal getPreviousExpectedGoal() {
		return previousExpectedGoal;
	}


	public void setPreviousExpectedGoal(BigDecimal previousExpectedGoal) {
		this.previousExpectedGoal = previousExpectedGoal;
	}


	public Boolean getCurrent() {
		return current;
	}


	public void setCurrent(Boolean current) {
		this.current = current;
	}

	public Long getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Long terminationDate) {
		this.terminationDate = terminationDate;
	}
	
	
	

}
