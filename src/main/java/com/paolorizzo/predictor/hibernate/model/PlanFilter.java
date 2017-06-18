package com.paolorizzo.predictor.hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.ws.rs.Encoded;

import com.paolorizzo.predictor.hibernate.model.DirettaFixture;

@Entity
@Table(name = "MASANIELLO_PLAN_FILTERS")
public class PlanFilter implements Serializable {
	
private String competition;
	
	@Id
	@Column(name = "STEP")
	private Integer step;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="MASANIELLOPLAN_NAME")
	})
	private MasanielloPlan masanielloPlan;
	
	
	@Column(name = "HOME_TEAM",length = 70, nullable = true)
	private String homeTeam;

	@Column(name = "AWAY_TEAM",length = 70, nullable = true)
	private String awayTeam;


	@Column(name = "AWAY_GOALS")
	private Integer awayGoals;
	
	@Column(name = "QUOTA_1_FROM", nullable = true)
	private BigDecimal quota1From;
	
	@Column(name = "QUOTA_X_FROM", nullable = true)
	private BigDecimal quotaXFrom;
	
	@Column(name = "QUOTA_2_FROM", nullable = true)
	private BigDecimal quota2From;
	
	@Column(name = "QUOTA_1_TO", nullable = true)
	private BigDecimal quota1To;
	
	@Column(name = "QUOTA_X_TO", nullable = true)
	private BigDecimal quotaXTo;
	
	@Column(name = "QUOTA_2_TO", nullable = true)
	private BigDecimal quota2To;
	
	
	@Column(name = "BET_TYPE", nullable = false)
	private String betType;
	
	@Column(name = "DATE_FROM", nullable = true)
	private Date dateFrom;
	
	@Column(name = "DATE_TO", nullable = true)
	private Date dateTo;
	
	@Column(name = "AMOUNT", nullable = false)
	private BigDecimal amount;
	
	
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

	public MasanielloPlan getMasanielloPlan() {
		return masanielloPlan;
	}

	public void setMasanielloPlan(MasanielloPlan masanielloPlan) {
		this.masanielloPlan = masanielloPlan;
	}

	
	
	

}
