package com.paolorizzo.predictor.hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
@Table(name = "MASANIELLO_PLANS")
public class MasanielloPlan implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 638238266551828496L;

	@Id
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="USER_EMAIL")})
	private User user;
	
	@Column(name = "AMOUNT", nullable = false)
	private BigDecimal amount;
	
	@Column(name = "ROUNDS", nullable = false)
	private Integer rounds;
	
	@Column(name = "EVENT_TO_WIN", nullable = false)
	private Integer eventToWin;
	
	@Column(name = "PERCENTAGE", nullable = false)
	private BigDecimal percentage;
	
	@Column(name = "AVERAGE_QUOTE", nullable = false)
	private BigDecimal averageQuote;
	
	@Column(name = "ADDITIONAL_QUOTE", nullable = false)
	private BigDecimal additionalQuote;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "masanielloPlan")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private List<Masaniello> masaniellos;

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "masanielloPlan")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private List<PlanFilter> planFilters;

	
	public MasanielloPlan() {
		// TODO Auto-generated constructor stub
	}
	
	

	public MasanielloPlan(String name, User user, BigDecimal amount, Integer rounds, Integer eventToWin, BigDecimal percentage, BigDecimal averageQuote, BigDecimal additionalQuote, List<Masaniello> masaniellos) {
		super();
		this.name = name;
		this.user = user;
		this.amount = amount;
		this.rounds = rounds;
		this.eventToWin = eventToWin;
		this.percentage = percentage;
		this.averageQuote = averageQuote;
		this.masaniellos = masaniellos;
		this.additionalQuote = additionalQuote;
	}



	public MasanielloPlan(String name, User user) {
		this.name = name;
		this.user = user;
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

	public List<Masaniello> getMasaniellos() {
		if(masaniellos == null)
			return new ArrayList<Masaniello>();
		return masaniellos;
	}

	public void setMasaniellos(List<Masaniello> masaniellos) {
		this.masaniellos = masaniellos;
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

	public BigDecimal getAverageQuote() {
		return averageQuote;
	}

	public void setAverageQuote(BigDecimal averageQuote) {
		this.averageQuote = averageQuote;
	}

	public BigDecimal getAdditionalQuote() {
		return additionalQuote;
	}

	public void setAdditionalQuote(BigDecimal additionalQuote) {
		this.additionalQuote = additionalQuote;
	}



	public List<PlanFilter> getPlanFilters() {
		return planFilters;
	}



	public void setPlanFilters(List<PlanFilter> planFilters) {
		this.planFilters = planFilters;
	}
	
	
	
}
