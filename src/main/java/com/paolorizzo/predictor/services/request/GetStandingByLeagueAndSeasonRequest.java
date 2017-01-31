package com.paolorizzo.predictor.services.request;

public class GetStandingByLeagueAndSeasonRequest  extends SimpleRequest{

	private String league;

	private String season;

	public GetStandingByLeagueAndSeasonRequest() {
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
