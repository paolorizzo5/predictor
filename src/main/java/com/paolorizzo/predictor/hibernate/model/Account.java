package com.paolorizzo.predictor.hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "ACCOUNTS")
public class Account implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6627605908926623615L;

	@Id
	@Column(name = "NAME", nullable = false, length = 30)
	private String name;
	
	@Column(name = "DESCRIPTION", nullable = true, length = 100)
	private String description;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_EMAIL", nullable = false)
	private User user;
	
	

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "account")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private List<MoneyTransaction> moneyTransactions;
	

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "account")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private List<Bet> bets;
	
	@Column(name = "INSERT_DATE", nullable = false)
	private Date insertDate;
	
	@Column(name = "LIVE_AMOUNT", nullable = false)
	private BigDecimal liveAmount;
	
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "account")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private Prospect prospect;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "account")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private List<AccountStats> accountStats;
	
	public Account(String name, User user) {
		super();
		this.name = name;
		this.user = user;
	}

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<MoneyTransaction> getMoneyTransactions() {
		if(moneyTransactions == null){
			return new ArrayList<MoneyTransaction>();
		}
		return moneyTransactions;
	}

	public void setMoneyTransactions(List<MoneyTransaction> moneyTransactions) {
		this.moneyTransactions = moneyTransactions;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public List<Bet> getBets() {
		if(bets == null){
			return new ArrayList<Bet>();
		}
		return bets;
	}

	public void setBets(List<Bet> bets) {
		this.bets = bets;
	}

	public Prospect getProspect() {
		return prospect;
	}

	public void setProspect(Prospect prospect) {
		this.prospect = prospect;
	}

	public BigDecimal getLiveAmount() {
		return liveAmount;
	}

	public void setLiveAmount(BigDecimal liveAmount) {
		this.liveAmount = liveAmount;
	}

	public List<AccountStats> getAccountStats() {
		if (accountStats == null){
			return new ArrayList<AccountStats>();
		}
		return accountStats;
	}

	public void setAccountStats(List<AccountStats> accountStats) {
		this.accountStats = accountStats;
	}
	
	
	
	

}
