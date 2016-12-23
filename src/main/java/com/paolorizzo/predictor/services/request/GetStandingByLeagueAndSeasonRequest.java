package com.paolorizzo.predictor.services.request;

public class GetStandingByLeagueAndSeasonRequest {

	private String uniquecallid;

	private String league;

	private String season;

	public GetStandingByLeagueAndSeasonRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getUniquecallid() {
		return uniquecallid;
	}

	public void setUniquecallid(String uniquecallid) {
		this.uniquecallid = uniquecallid;
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
