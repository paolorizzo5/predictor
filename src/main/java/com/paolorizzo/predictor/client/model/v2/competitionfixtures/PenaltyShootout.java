package com.paolorizzo.predictor.client.model.v2.competitionfixtures;

public class PenaltyShootout {
	private String goalsHomeTeam;

	private String goalsAwayTeam;

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
		return "ClassPojo [goalsHomeTeam = " + goalsHomeTeam
				+ ", goalsAwayTeam = " + goalsAwayTeam + "]";
	}
}
