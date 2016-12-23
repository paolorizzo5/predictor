package com.paolorizzo.predictor.dto;

import java.util.List;

public class FixtureDto implements Comparable<FixtureDto> {

	private Integer id;

	private Long date;

	private LeagueDto league;

	private String round;

	private String season;

	private TeamDto homeTeam;

	private Integer homeGoals;

	private TeamDto awayTeam;

	private Integer awayGoals;

	private String time;

	private String location;

	private String homeTeamYellowCardDetails;

	private String awayTeamYellowCardDetails;

	private String homeTeamRedCardDetails;

	private String awayTeamRedCardDetails;

	private List<OddDto> odds;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public LeagueDto getLeague() {
		return league;
	}

	public void setLeague(LeagueDto league) {
		this.league = league;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public TeamDto getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(TeamDto homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Integer getHomeGoals() {
		return homeGoals;
	}

	public void setHomeGoals(Integer homeGoals) {
		this.homeGoals = homeGoals;
	}

	public TeamDto getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(TeamDto awayTeam) {
		this.awayTeam = awayTeam;
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

	public List<OddDto> getOdds() {
		return odds;
	}

	public void setOdds(List<OddDto> odds) {
		this.odds = odds;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	@Override
	public int compareTo(FixtureDto o) {
		return this.getDate().compareTo(o.getDate());
	}

}
