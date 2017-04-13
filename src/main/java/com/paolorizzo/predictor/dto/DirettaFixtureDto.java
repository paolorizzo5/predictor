package com.paolorizzo.predictor.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class DirettaFixtureDto implements Serializable {
	
	private Long date;

	private String homeTeam;
	
	private String awayTeam;


	private Integer homeGoals;

	private Integer awayGoals;

	private BigDecimal quota1;
	
	private BigDecimal quotaX;
	
	private BigDecimal quota2;
	
	private String currentCompetition;


	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Integer getHomeGoals() {
		return homeGoals;
	}

	public void setHomeGoals(Integer homeGoals) {
		this.homeGoals = homeGoals;
	}

	public Integer getAwayGoals() {
		return awayGoals;
	}

	public void setAwayGoals(Integer awayGoals) {
		this.awayGoals = awayGoals;
	}

	public BigDecimal getQuota1() {
		return quota1;
	}

	public void setQuota1(BigDecimal quota1) {
		this.quota1 = quota1;
	}

	public BigDecimal getQuotaX() {
		return quotaX;
	}

	public void setQuotaX(BigDecimal quotaX) {
		this.quotaX = quotaX;
	}

	public BigDecimal getQuota2() {
		return quota2;
	}

	public void setQuota2(BigDecimal quota2) {
		this.quota2 = quota2;
	}

	public String getCurrentCompetition() {
		return currentCompetition;
	}

	public void setCurrentCompetition(String currentCompetition) {
		this.currentCompetition = currentCompetition;
	}
	
	
	
}
