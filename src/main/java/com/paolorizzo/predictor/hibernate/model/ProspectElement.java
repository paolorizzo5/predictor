package com.paolorizzo.predictor.hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROSPECT_ELEMENTS")
public class ProspectElement implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3143043293647515476L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="PROSPECT_USER_EMAIL"),
		@JoinColumn(name="PROSPECT_NAME")})
	private Prospect prospect;
	
	@Column(name = "START_DATE", nullable = false)
	private Date startDate;
	
	@Column(name = "END_DATE", nullable = false)
	private Date endDate;
	
	@Id
	@Column(name = "INCREMENTAL", nullable = false)
	private Integer incremental;
	
	@Column(name = "EXPECTED_GOAL", nullable = false)
	private BigDecimal expectedGoal;
	
	@Column(name = "AMOUNT_ACHIEVED")
	private BigDecimal amountAchieved;
	
	@Column(name = "LIVE_AMOUNT")
	private BigDecimal liveAmount;
	
	@Column(name = "TERMINATION_DATE", nullable = true)
	private Date terminationDate;
	
	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public Prospect getProspect() {
		return prospect;
	}

	public void setProspect(Prospect prospect) {
		this.prospect = prospect;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
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

	public BigDecimal getAmountAchieved() {
		return amountAchieved;
	}

	public void setAmountAchieved(BigDecimal amountAchieved) {
		this.amountAchieved = amountAchieved;
	}

	public BigDecimal getLiveAmount() {
		return liveAmount;
	}

	public void setLiveAmount(BigDecimal liveAmount) {
		this.liveAmount = liveAmount;
	}
	
	
	
	
	
	
	

}
