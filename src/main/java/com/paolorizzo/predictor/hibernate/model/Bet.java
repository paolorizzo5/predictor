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
@Table(name = "BETS")
public class Bet implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7455576368763667885L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="ACCOUNT_USER_EMAIL"),
		@JoinColumn(name="ACCOUNT_NAME")})
	private Account account;
	
	@Id
	@Column(name = "INSERT_DATE", nullable = false)
	private Date insertDate;
	
	@Column(name = "EVENT_DESCRIPTION", length = 40,nullable = false)
	private String eventDescription;
	
	@Column(name = "BETTYPE_DESCRIPTION", length = 100,nullable = false)
	private String bettypeDescription;
	
	@Column(name = "AMOUNT",nullable = false)
	private Integer amount;
	
	@Column(name = "MOLTIPLICATOR",nullable = false)
	private BigDecimal moltiplicator;
	
	@Column(name = "BET_STATUS",nullable = false)
	private String betStatus;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getBettypeDescription() {
		return bettypeDescription;
	}

	public void setBettypeDescription(String bettypeDescription) {
		this.bettypeDescription = bettypeDescription;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public BigDecimal getMoltiplicator() {
		return moltiplicator;
	}

	public void setMoltiplicator(BigDecimal moltiplicator) {
		this.moltiplicator = moltiplicator;
	}

	public String getBetStatus() {
		return betStatus;
	}

	public void setBetStatus(String betStatus) {
		this.betStatus = betStatus;
	}
	
	
	
	
	
	

}
