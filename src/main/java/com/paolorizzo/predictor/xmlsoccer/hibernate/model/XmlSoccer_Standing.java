package com.paolorizzo.predictor.xmlsoccer.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "XMLSOCCER_STANDINGS")
public class XmlSoccer_Standing implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2733759220111946008L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEAM_ID", nullable = false)
	private XmlSoccer_Team Team;

	@Column(name = "PLAYED")
	private int played;

	@Column(name = "PLAYED_AT_HOME")
	private int playedAtHome;

	@Column(name = "PLAYED_AWAY")
	private int playedAway;

	@Column(name = "WON")
	private int won;

	@Column(name = "DRAW")
	private int draw;

	@Column(name = "LOST")
	private int lost;

	@Column(name = "NUMBER_OF_SHOTS")
	private int numberOfShots;

	@Column(name = "YELLOW_CARDS")
	private int yellowCards;

	@Column(name = "RED_CARDS")
	private int redCards;

	@Column(name = "GOALS_FOR")
	private int goalsFor;

	@Column(name = "GOALS_AGAINST")
	private int goalsAgainst;

	@Column(name = "GOALS_DIFFERENCE")
	private int goalDifference;

	@Column(name = "POINTS")
	private int points;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEAGUE_ID", nullable = false)
	private XmlSoccer_League league;

	@Id
	@Column(name = "SEASON")
	private String season;

	@Id
	@Column(name = "ID")
	private int id;

	public XmlSoccer_Team getTeam() {
		return Team;
	}

	public void setTeam(XmlSoccer_Team team) {
		Team = team;
	}

	public int getPlayed() {
		return played;
	}

	public void setPlayed(int played) {
		this.played = played;
	}

	public int getPlayedAtHome() {
		return playedAtHome;
	}

	public void setPlayedAtHome(int playedAtHome) {
		this.playedAtHome = playedAtHome;
	}

	public int getPlayedAway() {
		return playedAway;
	}

	public void setPlayedAway(int playedAway) {
		this.playedAway = playedAway;
	}

	public int getWon() {
		return won;
	}

	public void setWon(int won) {
		this.won = won;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getLost() {
		return lost;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public int getNumberOfShots() {
		return numberOfShots;
	}

	public void setNumberOfShots(int numberOfShots) {
		this.numberOfShots = numberOfShots;
	}

	public int getYellowCards() {
		return yellowCards;
	}

	public void setYellowCards(int yellowCards) {
		this.yellowCards = yellowCards;
	}

	public int getRedCards() {
		return redCards;
	}

	public void setRedCards(int redCards) {
		this.redCards = redCards;
	}

	public int getGoalsFor() {
		return goalsFor;
	}

	public void setGoalsFor(int goalsFor) {
		this.goalsFor = goalsFor;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	public int getGoalDifference() {
		return goalDifference;
	}

	public void setGoalDifference(int goalDifference) {
		this.goalDifference = goalDifference;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
