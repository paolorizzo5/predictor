package com.paolorizzo.predictor.xmlsoccer.hibernate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "XMLSOCCER_HISTORIC_MATCHES")
public class XmlSoccer_HistoricMatch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2862593350826972563L;

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "FIXTURE_MATCH_ID")
	private Integer fixtureMatchId;

	@Column(name = "DATE")
	private Date date;

	@Column(name = "ROUND")
	private Integer round;

	@Column(name = "SPECTATORS", length = 10)
	private String spectators;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEAGUE_ID", nullable = false)
	private XmlSoccer_League league;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HOME_TEAM_ID", nullable = false)
	private XmlSoccer_Team homeTeam;

	@Column(name = "HOME_CORNERS")
	private Integer homeCorners;

	@Column(name = "HOME_GOALS")
	private Integer homeGoals;

	@Column(name = "HALF_TIME_HOME_GOALS")
	private Integer halfTimeHomeGoals;

	@Column(name = "HOME_SHOTS")
	private Integer homeShots;

	@Column(name = "HOME_SHOTS_ON_TARGET")
	private Integer homeShotsOnTarget;

	@Column(name = "HOME_FOULS")
	private Integer homeFouls;

	@Column(name = "HOME_GOAL_DETAILS", length = 300)
	private String homeGoalDetails;

	@Column(name = "HOME_LINEUP_GOALKEEPER", length = 300)
	private String homeLineupGoalkeeper;

	@Column(name = "HOME_LINEUP_DEFENSE", length = 300)
	private String homeLineupDefense;

	@Column(name = "HOME_LINEUP_MIDFIELD", length = 300)
	private String homeLineupMidfield;

	@Column(name = "HOME_LINEUP_FORWARD", length = 300)
	private String homeLineupForward;

	@Column(name = "HOME_YELLOW_CARDS")
	private Integer homeYellowCards;

	@Column(name = "HOME_RED_CARDS")
	private Integer homeRedCards;

	@Column(name = "HOME_TEAM_FORMATION", length = 300)
	private String homeTeamFormation;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AWAY_TEAM_ID", nullable = false)
	private XmlSoccer_Team awayTeam;

	@Column(name = "AWAY_CORNERS")
	private Integer awayCorners;

	@Column(name = "AWAY_GOALS")
	private Integer awayGoals;

	@Column(name = "HALF_TIME_AWAY_GOALS")
	private Integer halfTimeAwayGoals;

	@Column(name = "AWAY_SHOTS")
	private Integer awayShots;

	@Column(name = "AWAY_SHOTS_ON_TARGET")
	private Integer awayShotsOnTarget;

	@Column(name = "AWAY_FOULS")
	private Integer awayFouls;

	@Column(name = "AWAY_GOAL_DETAILS", length = 300)
	private String awayGoalDetails;

	@Column(name = "AWAY_LINEUP_GOALKEEPER", length = 50)
	private String awayLineupGoalkeeper;

	@Column(name = "AWAY_LINEUP_DEFENSE", length = 200)
	private String awayLineupDefense;

	@Column(name = "AWAY_LINEUP_MIDFIELD", length = 200)
	private String awayLineupMidfield;

	@Column(name = "AWAY_LINEUP_FORWARD", length = 200)
	private String awayLineupForward;

	@Column(name = "AWAY_YELLOW_CARDS")
	private Integer awayYellowCards;

	@Column(name = "AWAY_RED_CARDS")
	private Integer awayRedCards;

	@Column(name = "AWAY_TEAM_FORMATION", length = 200)
	private String awayTeamFormation;

	@Column(name = "HOME_TEAM_YELLOW_CARD_DETAILS", length = 200)
	private String homeTeamYellowCardDetails;

	@Column(name = "AWAY_TEAM_YELLOW_CARD_DETAILS", length = 200)
	private String awayTeamYellowCardDetails;

	@Column(name = "HOME_TEAM_RED_CARD_DETAILS", length = 200)
	private String homeTeamRedCardDetails;

	@Column(name = "AWAY_TEAM_RED_CARD_DETAILS", length = 200)
	private String awayTeamRedCardDetails;

	@Column(name = "SEASON", length = 10)
	private String season;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFixtureMatchId() {
		return fixtureMatchId;
	}

	public void setFixtureMatchId(Integer fixtureMatchId) {
		this.fixtureMatchId = fixtureMatchId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	public String getSpectators() {
		return spectators;
	}

	public void setSpectators(String spectators) {
		this.spectators = spectators;
	}

	public XmlSoccer_League getLeague() {
		return league;
	}

	public void setLeague(XmlSoccer_League league) {
		this.league = league;
	}

	public Integer getHomeCorners() {
		return homeCorners;
	}

	public void setHomeCorners(Integer homeCorners) {
		this.homeCorners = homeCorners;
	}

	public Integer getHomeGoals() {
		return homeGoals;
	}

	public void setHomeGoals(Integer homeGoals) {
		this.homeGoals = homeGoals;
	}

	public Integer getHalfTimeHomeGoals() {
		return halfTimeHomeGoals;
	}

	public void setHalfTimeHomeGoals(Integer halfTimeHomeGoals) {
		this.halfTimeHomeGoals = halfTimeHomeGoals;
	}

	public Integer getHomeShots() {
		return homeShots;
	}

	public void setHomeShots(Integer homeShots) {
		this.homeShots = homeShots;
	}

	public Integer getHomeShotsOnTarget() {
		return homeShotsOnTarget;
	}

	public void setHomeShotsOnTarget(Integer homeShotsOnTarget) {
		this.homeShotsOnTarget = homeShotsOnTarget;
	}

	public Integer getHomeFouls() {
		return homeFouls;
	}

	public void setHomeFouls(Integer homeFouls) {
		this.homeFouls = homeFouls;
	}

	public String getHomeGoalDetails() {
		return homeGoalDetails;
	}

	public void setHomeGoalDetails(String homeGoalDetails) {
		this.homeGoalDetails = homeGoalDetails;
	}

	public String getHomeLineupGoalkeeper() {
		return homeLineupGoalkeeper;
	}

	public void setHomeLineupGoalkeeper(String homeLineupGoalkeeper) {
		this.homeLineupGoalkeeper = homeLineupGoalkeeper;
	}

	public String getHomeLineupDefense() {
		return homeLineupDefense;
	}

	public void setHomeLineupDefense(String homeLineupDefense) {
		this.homeLineupDefense = homeLineupDefense;
	}

	public String getHomeLineupMidfield() {
		return homeLineupMidfield;
	}

	public void setHomeLineupMidfield(String homeLineupMidfield) {
		this.homeLineupMidfield = homeLineupMidfield;
	}

	public String getHomeLineupForward() {
		return homeLineupForward;
	}

	public void setHomeLineupForward(String homeLineupForward) {
		this.homeLineupForward = homeLineupForward;
	}

	public Integer getHomeYellowCards() {
		return homeYellowCards;
	}

	public void setHomeYellowCards(Integer homeYellowCards) {
		this.homeYellowCards = homeYellowCards;
	}

	public Integer getHomeRedCards() {
		return homeRedCards;
	}

	public void setHomeRedCards(Integer homeRedCards) {
		this.homeRedCards = homeRedCards;
	}

	public String getHomeTeamFormation() {
		return homeTeamFormation;
	}

	public void setHomeTeamFormation(String homeTeamFormation) {
		this.homeTeamFormation = homeTeamFormation;
	}

	public Integer getAwayCorners() {
		return awayCorners;
	}

	public void setAwayCorners(Integer awayCorners) {
		this.awayCorners = awayCorners;
	}

	public Integer getAwayGoals() {
		return awayGoals;
	}

	public void setAwayGoals(Integer awayGoals) {
		this.awayGoals = awayGoals;
	}

	public Integer getHalfTimeAwayGoals() {
		return halfTimeAwayGoals;
	}

	public void setHalfTimeAwayGoals(Integer halfTimeAwayGoals) {
		this.halfTimeAwayGoals = halfTimeAwayGoals;
	}

	public Integer getAwayShots() {
		return awayShots;
	}

	public void setAwayShots(Integer awayShots) {
		this.awayShots = awayShots;
	}

	public Integer getAwayShotsOnTarget() {
		return awayShotsOnTarget;
	}

	public void setAwayShotsOnTarget(Integer awayShotsOnTarget) {
		this.awayShotsOnTarget = awayShotsOnTarget;
	}

	public Integer getAwayFouls() {
		return awayFouls;
	}

	public void setAwayFouls(Integer awayFouls) {
		this.awayFouls = awayFouls;
	}

	public String getAwayGoalDetails() {
		return awayGoalDetails;
	}

	public void setAwayGoalDetails(String awayGoalDetails) {
		this.awayGoalDetails = awayGoalDetails;
	}

	public String getAwayLineupGoalkeeper() {
		return awayLineupGoalkeeper;
	}

	public void setAwayLineupGoalkeeper(String awayLineupGoalkeeper) {
		this.awayLineupGoalkeeper = awayLineupGoalkeeper;
	}

	public String getAwayLineupDefense() {
		return awayLineupDefense;
	}

	public void setAwayLineupDefense(String awayLineupDefense) {
		this.awayLineupDefense = awayLineupDefense;
	}

	public String getAwayLineupMidfield() {
		return awayLineupMidfield;
	}

	public void setAwayLineupMidfield(String awayLineupMidfield) {
		this.awayLineupMidfield = awayLineupMidfield;
	}

	public String getAwayLineupForward() {
		return awayLineupForward;
	}

	public void setAwayLineupForward(String awayLineupForward) {
		this.awayLineupForward = awayLineupForward;
	}

	public Integer getAwayYellowCards() {
		return awayYellowCards;
	}

	public void setAwayYellowCards(Integer awayYellowCards) {
		this.awayYellowCards = awayYellowCards;
	}

	public Integer getAwayRedCards() {
		return awayRedCards;
	}

	public void setAwayRedCards(Integer awayRedCards) {
		this.awayRedCards = awayRedCards;
	}

	public String getAwayTeamFormation() {
		return awayTeamFormation;
	}

	public void setAwayTeamFormation(String awayTeamFormation) {
		this.awayTeamFormation = awayTeamFormation;
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

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
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

}
