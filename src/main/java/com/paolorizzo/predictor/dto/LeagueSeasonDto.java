package com.paolorizzo.predictor.dto;

public class LeagueSeasonDto {

	private String league;
	private String season;

	public LeagueSeasonDto() {
		// TODO Auto-generated constructor stub
	}

	public LeagueSeasonDto(String league, String season) {
		super();
		this.league = league;
		this.season = season;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

}
