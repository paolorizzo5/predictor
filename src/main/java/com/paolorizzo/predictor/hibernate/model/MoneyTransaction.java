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
@Table(name = "MONEY_TRANSACTIONS")
public class MoneyTransaction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7293244952905314726L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="ACCOUNT_USER_EMAIL"),
		@JoinColumn(name="ACCOUNT_NAME")})
	private Account account;
	
	@Id
	@Column(name = "INSERT_DATE", nullable = false)
	private Date insertDate;
	
	@Column(name = "DIRECTION", nullable = false, length = 1)
	private String direction;
	
	@Column(name = "AMOUNT", nullable = false)
	private BigDecimal amount;

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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	

	
	

}
