package com.paolorizzo.predictor.xmlsoccer.hibernate.model;

import java.io.Serializable;
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
import javax.persistence.Table;

@Entity
@Table(name = "XMLSOCCER_FIXTURES")
public class XmlSoccer_Fixture implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6860747632982975618L;

	@Id
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "DATE", nullable = false)
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEAGUE_ID", nullable = false)
	private XmlSoccer_League league;

	@Column(name = "ROUND_NUMBER")
	private String round;

	@Column(name = "SEASON")
	private String season;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HOME_TEAM_ID", nullable = false)
	private XmlSoccer_Team homeTeam;

	@Column(name = "HOME_GOALS")
	private Integer homeGoals;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AWAY_TEAM_ID", nullable = false)
	private XmlSoccer_Team awayTeam;

	@Column(name = "AWAY_GOALS")
	private Integer awayGoals;

	@Column(name = "time")
	private String time;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "HOME_TEAM_YELLOW_CARD_DETAILS")
	private String homeTeamYellowCardDetails;

	@Column(name = "AWAY_TEAM_YELLOW_CARD_DETAILS")
	private String awayTeamYellowCardDetails;

	@Column(name = "HOME_TEAM_RED_CARD_DETAILS")
	private String homeTeamRedCardDetails;

	@Column(name = "AWAY_TEAM_RED_CARD_DETAILS")
	private String awayTeamRedCardDetails;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fixture", cascade = CascadeType.ALL)
	private List<XmlSoccer_Odd> odds;

	public XmlSoccer_Fixture(int id) {
		this.id = id;
	}

	public XmlSoccer_Fixture() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public Integer getHomeGoals() {
		return homeGoals;
	}

	public void setHomeGoals(Integer homeGoals) {
		this.homeGoals = homeGoals;
	}

	public Integer getAwayGoals() {
		return awayGoals;
	}

	public void setAwayGoals(Integer awayGoals) {
		this.awayGoals = awayGoals;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHomeTeamYellowCardDetails() {
		return homeTeamYellowCardDetails;
	}

	public void setHomeTeamYellowCardDetails(String homeTeamYellowCardDetails) {
		this.homeTeamYellowCardDetails = homeTeamYellowCardDetails;
	}

	public String getAwayTeamYellowCardDetails() {
		return awayTeamYellowCardDetails;
	}

	public void setAwayTeamYellowCardDetails(String awayTeamYellowCardDetails) {
		this.awayTeamYellowCardDetails = awayTeamYellowCardDetails;
	}

	public String getHomeTeamRedCardDetails() {
		return homeTeamRedCardDetails;
	}

	public void setHomeTeamRedCardDetails(String homeTeamRedCardDetails) {
		this.homeTeamRedCardDetails = homeTeamRedCardDetails;
	}

	public String getAwayTeamRedCardDetails() {
		return awayTeamRedCardDetails;
	}

	public void setAwayTeamRedCardDetails(String awayTeamRedCardDetails) {
		this.awayTeamRedCardDetails = awayTeamRedCardDetails;
	}

	public List<XmlSoccer_Odd> getOdds() {
		return odds;
	}

	public void setOdds(List<XmlSoccer_Odd> odds) {
		this.odds = odds;
	}

	public XmlSoccer_Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(XmlSoccer_Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public XmlSoccer_Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(XmlSoccer_Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public XmlSoccer_League getLeague() {
		return league;
	}

	public void setLeague(XmlSoccer_League league) {
		this.league = league;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

}
