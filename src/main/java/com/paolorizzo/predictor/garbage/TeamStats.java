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
@Table(name = "TEAMS_STATS")
public class TeamStats implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1319771688385962975L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({@JoinColumn(name = "COMPETITION_ID"), @JoinColumn(name = "TEAM_NAME")
			 })
	private Team team;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BETTYPE_ID", nullable = false)
	private Bettype bettype;

	@Column(name = "SUCCESSFUL_CASES")
	private Integer successFulCases;
	
	@Column(name = "TOTAL_CASES")
	private Integer totalCases;

	@Id
	@Column(name = "AT_HOME")
	private String atHome;

	public TeamStats() {
		// TODO Auto-generated constructor stub
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Bettype getBettype() {
		return bettype;
	}

	public void setBettype(Bettype bettype) {
		this.bettype = bettype;
	}

	public Integer getSuccessFulCases() {
		return successFulCases;
	}

	public void setSuccessFulCases(Integer successFulCases) {
		this.successFulCases = successFulCases;
	}

	public Integer getTotalCases() {
		return totalCases;
	}

	public void setTotalCases(Integer totalCases) {
		this.totalCases = totalCases;
	}

	public String getAtHome() {
		return atHome;
	}

	public void setAtHome(String atHome) {
		this.atHome = atHome;
	}

	@Override
	public String toString() {
		return "TeamStats [team=" + team + ", bettype=" + bettype
				+ ", successFulCases=" + successFulCases + ", totalCases="
				+ totalCases + ", atHome=" + atHome + "]";
	}

	


}
