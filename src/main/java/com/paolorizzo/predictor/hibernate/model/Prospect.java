package com.paolorizzo.predictor.hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


@Entity
@Table(name = "PROSPECTS")
public class Prospect implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8005276341275305902L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_EMAIL", nullable = false)
	private User user;
	
	@Id
	@Column(name = "NAME", nullable = false, length = 30)
	private String name;
	
	@Column(name = "INITIAL_AMOUNT", nullable = false)
	private BigDecimal initialAmount;
	
	@Column(name = "DURATION", nullable = false)
	private Integer duration;
	
	@Column(name = "INSERT_DATE", nullable = false)
	private Date insertDate;
	
	@Column(name = "DAILY_PERCENTAGE_EXPECTED", nullable = false)
	private BigDecimal dailyPercentageExpected;

	
	
	@OneToOne(fetch = FetchType.LAZY)
	private Account account;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "prospect")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private List<ProspectElement> prospectElements;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Plan plan;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<ProspectElement> getProspectElements() {
		return prospectElements;
	}

	public void setProspectElements(List<ProspectElement> prospectElements) {
		this.prospectElements = prospectElements;
	}
	
}
