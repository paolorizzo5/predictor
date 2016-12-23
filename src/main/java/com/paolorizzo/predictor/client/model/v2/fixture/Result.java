package com.paolorizzo.predictor.client.model.v2.fixture;

public class Result {
	private HalfTime halfTime;

	private String goalsHomeTeam;

	private String goalsAwayTeam;
	
	private String extraTime;

	public HalfTime getHalfTime() {
		return halfTime;
	}

	public void setHalfTime(HalfTime halfTime) {
		this.halfTime = halfTime;
	}

	public String getGoalsHomeTeam() {
		return goalsHomeTeam;
	}

	public void setGoalsHomeTeam(String goalsHomeTeam) {
		this.goalsHomeTeam = goalsHomeTeam;
	}

	public String getGoalsAwayTeam() {
		return goalsAwayTeam;
	}

	public void setGoalsAwayTeam(String goalsAwayTeam) {
		this.goalsAwayTeam = goalsAwayTeam;
	}

	@Override
	public String toString() {
		return "ClassPojo [halfTime = " + halfTime + ", goalsHomeTeam = "
				+ goalsHomeTeam + ", goalsAwayTeam = " + goalsAwayTeam + "]";
	}

	public String getExtraTime() {
		return extraTime;
	}

	public void setExtraTime(String extraTime) {
		this.extraTime = extraTime;
	}
}