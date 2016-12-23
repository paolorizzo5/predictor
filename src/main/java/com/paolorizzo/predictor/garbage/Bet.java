package com.paolorizzo.predictor.garbage;

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
@Table(name = "BETS")
public class Bet implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -148704510866132118L;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_EMAIL", nullable = false)
	private User user;
	
	@Id
	@Column(name="FIXTURE_HOME_TEAM_CODE",nullable= false)
	private String fixtureHomeTeamCode;

	@Id
	@Column(name="FIXTURE_AWAY_TEAM_CODE",nullable= false)
	private String fixtureAwayTeamCode;
	
	@Id
	@Column(name = "COMPETITION_ID", nullable = false, length = 10)
	private String competitionId;

	@Id
	@Column(name="FIXTURE_DATE",nullable= false)
	private Long fixtureDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BETTYPE_ID", nullable = false)
	private Bettype bettype;
	
	@Id
	@Column(name = "DATE", nullable = false)
	private Long date;
	
	@Column(name = "AMOUNT", nullable = false)
	private Integer amount;
	
	@Column(name = "MOLTIPLICATOR", nullable = false)
	private BigDecimal moltiplicator;
	
	@Column(name = "SUCCESS", nullable = false)
	private boolean success;
	
	@Column(name = "STATUS", nullable = false)
	private String status;
	
	public Bet() {
		// TODO Auto-generated constructor stub
	}

	
	
	



	public Bet(User user, String fixtureHomeTeamCode,
			String fixtureAwayTeamCode, String competitionId, Long fixtureDate,
			Bettype bettype, Long date, Integer amount,
			BigDecimal moltiplicator, boolean success, String status) {
		super();
		this.user = user;
		this.fixtureHomeTeamCode = fixtureHomeTeamCode;
		this.fixtureAwayTeamCode = fixtureAwayTeamCode;
		this.competitionId = competitionId;
		this.fixtureDate = fixtureDate;
		this.bettype = bettype;
		this.date = date;
		this.amount = amount;
		this.moltiplicator = moltiplicator;
		this.success = success;
		this.status = status;
	}







	

	public String getFixtureHomeTeamCode() {
		return fixtureHomeTeamCode;
	}







	public void setFixtureHomeTeamCode(String fixtureHomeTeamCode) {
		this.fixtureHomeTeamCode = fixtureHomeTeamCode;
	}







	public String getFixtureAwayTeamCode() {
		return fixtureAwayTeamCode;
	}







	public void setFixtureAwayTeamCode(String fixtureAwayTeamCode) {
		this.fixtureAwayTeamCode = fixtureAwayTeamCode;
	}







	public String getCompetitionId() {
		return competitionId;
	}







	public void setCompetitionId(String competitionId) {
		this.competitionId = competitionId;
	}







	public Long getFixtureDate() {
		return fixtureDate;
	}







	public void setFixtureDate(Long fixtureDate) {
		this.fixtureDate = fixtureDate;
	}







	public Bettype getBettype() {
		return bettype;
	}

	public void setBettype(Bettype bettype) {
		this.bettype = bettype;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
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

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
