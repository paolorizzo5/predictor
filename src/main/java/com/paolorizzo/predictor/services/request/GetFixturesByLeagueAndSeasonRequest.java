package com.paolorizzo.predictor.services.request;

public class GetFixturesByLeagueAndSeasonRequest  extends SimpleRequest{

	private String league;

	private String season;

	public GetFixturesByLeagueAndSeasonRequest() {
		// TODO Auto-generated constructor stub
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
