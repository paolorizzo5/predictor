package com.paolorizzo.predictor.dto;

public class StandingDto implements Comparable<StandingDto> {

	private TeamDto team;

	private int played;

	private int playedAtHome;

	private int playedAway;

	private int won;

	private int draw;

	private int lost;

	private int numberOfShots;

	private int yellowCards;

	private int redCards;

	private int goalsFor;

	private int goalsAgainst;

	private int goalDifference;

	private int points;

	private LeagueDto league;

	private String season;

	private int id;

	public TeamDto getTeam() {
		return team;
	}

	public void setTeam(TeamDto team) {
		this.team = team;
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

	public LeagueDto getLeague() {
		return league;
	}

	public void setLeague(LeagueDto league) {
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

	@Override
	public int compareTo(StandingDto o) {
		if (o.getPoints() > this.getPoints()) {
			return 1;
		} else if (o.getPoints() < this.getPoints()) {
			return -1;
		} else {
			if (o.getGoalDifference() > this.getGoalDifference()) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
