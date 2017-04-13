package com.paolorizzo.predictor.hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MASANIELLO_ROUNDS")
public class MasanielloRound implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4995896566598922846L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="MASANIELLO_NAME"),
		@JoinColumn(name="MASANIELLO_USER_EMAIL")})
	private Masaniello masaniello;
	
	@Id
	@Column(name = "ROUND_ID", nullable = false)
	private Integer roundId;
	
	@Column(name = "MATCHES", nullable = false)
	private Integer matches;
	
	@Column(name = "SUCCESS", nullable = false)
	private Boolean success;
	
	@Column(name = "INITIAL_AMOUNT", nullable = false)
	private BigDecimal initialAmount;
	
	@Column(name = "FINAL_AMOUNT", nullable = false)
	private BigDecimal finalAmount;
	
	
	public Integer getMatches() {
		return matches;
	}

	public void setMatches(Integer matches) {
		this.matches = matches;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public BigDecimal getInitialAmount() {
		return initialAmount;
	}

	public void setInitialAmount(BigDecimal initialAmount) {
		this.initialAmount = initialAmount;
	}

	public BigDecimal getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(BigDecimal finalAmount) {
		this.finalAmount = finalAmount;
	}

	public Masaniello getMasaniello() {
		return masaniello;
	}

	public void setMasaniello(Masaniello masaniello) {
		this.masaniello = masaniello;
	}

	public Integer getRoundId() {
		return roundId;
	}

	public void setRoundId(Integer roundId) {
		this.roundId = roundId;
	}
	
	
	
	
}
