package com.paolorizzo.predictor.services.request;

public class ViewOddsRequest {

	private String homeTeam;
	private String awayTeam;
	private String id;
	private String uniquecallid;

	public ViewOddsRequest() {
		// TODO Auto-generated constructor stub
	}

	public ViewOddsRequest(String homeTeam, String awayTeam, String id,
			String uniquecallid) {
		super();
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.id = id;
		this.uniquecallid = uniquecallid;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUniquecallid() {
		return uniquecallid;
	}

	public void setUniquecallid(String uniquecallid) {
		this.uniquecallid = uniquecallid;
	}

}
