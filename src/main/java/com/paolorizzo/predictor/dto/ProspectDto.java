package com.paolorizzo.predictor.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProspectDto {
	
	private String name;
	
	private BigDecimal initialAmount;
	
	private Integer duration;
	
	private Date insertDate;
	
	private BigDecimal dailyPercentageExpected;
	
	private List<ProspectElementDto> prospectElements;

	private List<ProspectElementDto> visibleProspectElements;
	
	private BigDecimal nextGoal;

	private Long nextGoalExpiration;

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	public BigDecimal getDailyPercentageExpected() {
		return dailyPercentageExpected;
	}


	public void setDailyPercentageExpected(BigDecimal dailyPercentageExpected) {
		this.dailyPercentageExpected = dailyPercentageExpected;
	}


	public List<ProspectElementDto> getProspectElements() {
		return prospectElements;
	}


	public void setProspectElements(List<ProspectElementDto> prospectElements) {
		this.prospectElements = prospectElements;
	}


	public List<ProspectElementDto> getVisibleProspectElements() {
		return visibleProspectElements;
	}


	public void setVisibleProspectElements(List<ProspectElementDto> visibleProspectElements) {
		this.visibleProspectElements = visibleProspectElements;
	}


	public BigDecimal getNextGoal() {
		return nextGoal;
	}


	public void setNextGoal(BigDecimal nextGoal) {
		this.nextGoal = nextGoal;
	}


	public Long getNextGoalExpiration() {
		return nextGoalExpiration;
	}


	public void setNextGoalExpiration(Long nextGoalExpiration) {
		this.nextGoalExpiration = nextGoalExpiration;
	}


	
	
	
	
	
}
