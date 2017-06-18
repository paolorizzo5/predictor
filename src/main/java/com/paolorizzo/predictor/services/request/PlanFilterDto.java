package com.paolorizzo.predictor.services.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import com.paolorizzo.predictor.hibernate.model.DirettaFixture;

public class PlanFilterDto implements Serializable,Comparable<PlanFilterDto> {
	
private String competition;
	
	private String homeTeam;
	
	private String awayTeam;


	private Integer awayGoals;
	
	private BigDecimal quota1From;
	
	private BigDecimal quotaXFrom;
	
	private BigDecimal quota2From;
	
	private BigDecimal quota1To;
	
	private BigDecimal quotaXTo;
	
	private BigDecimal quota2To;
	
	
	private String betType;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private BigDecimal amount;
	
	private List<DirettaFixture> direttaFixtures;

	private Integer step;
	
	public String getCompetition() {
		return competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
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

	public BigDecimal getQuota1From() {
		return quota1From;
	}

	public void setQuota1From(BigDecimal quota1From) {
		this.quota1From = quota1From;
	}

	public BigDecimal getQuotaXFrom() {
		return quotaXFrom;
	}

	public void setQuotaXFrom(BigDecimal quotaXFrom) {
		this.quotaXFrom = quotaXFrom;
	}

	public BigDecimal getQuota2From() {
		return quota2From;
	}

	public void setQuota2From(BigDecimal quota2From) {
		this.quota2From = quota2From;
	}

	public BigDecimal getQuota1To() {
		return quota1To;
	}

	public void setQuota1To(BigDecimal quota1To) {
		this.quota1To = quota1To;
	}

	public BigDecimal getQuotaXTo() {
		return quotaXTo;
	}

	public void setQuotaXTo(BigDecimal quotaXTo) {
		this.quotaXTo = quotaXTo;
	}

	public BigDecimal getQuota2To() {
		return quota2To;
	}

	public void setQuota2To(BigDecimal quota2To) {
		this.quota2To = quota2To;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public List<DirettaFixture> getDirettaFixtures() {
		return direttaFixtures;
	}

	public void setDirettaFixtures(List<DirettaFixture> direttaFixtures) {
		this.direttaFixtures = direttaFixtures;
	}

	@Override
	public int compareTo(PlanFilterDto o) {
		return this.getDirettaFixtures().size() - o.getDirettaFixtures().size();
	}

	public String getBetType() {
		return betType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	
	
	

}
