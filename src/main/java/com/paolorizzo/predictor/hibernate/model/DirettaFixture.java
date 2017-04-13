package com.paolorizzo.predictor.hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.paolorizzo.predictor.dto.FixtureDto;

@Entity
@Table(name = "DIRETTA_FIXTURES")
public class DirettaFixture implements Serializable,Comparable<DirettaFixture> {
	
	@Id
	@Column(name = "DATE", nullable = false)
	private Date date;

	@Id
	@Column(name = "HOME_TEAM",length = 70, nullable = false)
	private String homeTeam;
	
	@Id
	@Column(name = "AWAY_TEAM",length = 70, nullable = false)
	private String awayTeam;


	@Column(name = "HOME_GOALS")
	private Integer homeGoals;

	@Column(name = "AWAY_GOALS")
	private Integer awayGoals;

	@Column(name = "QUOTA_1", nullable = false)
	private BigDecimal quota1;
	
	@Column(name = "QUOTA_X", nullable = false)
	private BigDecimal quotaX;
	
	@Column(name = "QUOTA_2", nullable = false)
	private BigDecimal quota2;
	
	@Column(name = "CURRENT_COMPETITION",length = 100)
	private String currentCompetition;


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
	
	
	@Override
	public int compareTo(DirettaFixture o) {
		return this.getDate().compareTo(o.getDate());
	}

	@Override
	public String toString() {
		return "DirettaFixture [date=" + date + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", homeGoals="
				+ homeGoals + ", awayGoals=" + awayGoals + ", quota1=" + quota1 + ", quotaX=" + quotaX + ", quota2="
				+ quota2 + ", currentCompetition=" + currentCompetition + "]";
	}
	
	
}
