package com.paolorizzo.predictor.hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "MASANIELLOS")
public class Masaniello implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8724007860231027352L;

	@Id
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="USER_EMAIL")})
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "masaniello")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private List<MasanielloRound> masanielloRounds;
	
	@Column(name = "AMOUNT", nullable = false)
	private BigDecimal amount;
	
	@Column(name = "ROUNDS", nullable = false)
	private Integer rounds;
	
	@Column(name = "EVENT_TO_WIN", nullable = false)
	private Integer eventToWin;
	
	@Column(name = "EVENT_TYPE", nullable = false)
	private String eventType;
	
	@Column(name = "PERCENTAGE", nullable = false)
	private BigDecimal percentage;
	
	@Column(name = "AVERAGE_QUOTE", nullable = false)
	private BigDecimal averageQuote;

	
	
	
	public Masaniello(String name, User user) {
		super();
		this.name = name;
		this.user = user;
	}

	public Masaniello() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<MasanielloRound> getMasanielloRounds() {
		return masanielloRounds;
	}

	public void setMasanielloRounds(List<MasanielloRound> masanielloRounds) {
		this.masanielloRounds = masanielloRounds;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getRounds() {
		return rounds;
	}

	public void setRounds(Integer rounds) {
		this.rounds = rounds;
	}

	public Integer getEventToWin() {
		return eventToWin;
	}

	public void setEventToWin(Integer eventToWin) {
		this.eventToWin = eventToWin;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public BigDecimal getAverageQuote() {
		return averageQuote;
	}

	public void setAverageQuote(BigDecimal averageQuote) {
		this.averageQuote = averageQuote;
	}
	
	
	
	

}
