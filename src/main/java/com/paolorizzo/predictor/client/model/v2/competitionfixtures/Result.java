package com.paolorizzo.predictor.client.model.v2.competitionfixtures;

import com.paolorizzo.predictor.client.model.v2.fixture.HalfTime;

public class Result {
	private ExtraTime extraTime;

	private HalfTime halfTime;

	private String goalsHomeTeam;

	private String goalsAwayTeam;

	private PenaltyShootout penaltyShootout;

	public ExtraTime getExtraTime() {
		return extraTime;
	}

	public void setExtraTime(ExtraTime extraTime) {
		this.extraTime = extraTime;
	}

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
		return "ClassPojo [extraTime = " + extraTime + ", halfTime = "
				+ halfTime + ", goalsHomeTeam = " + goalsHomeTeam
				+ ", goalsAwayTeam = " + goalsAwayTeam + "]";
	}

	public PenaltyShootout getPenaltyShootout() {
		return penaltyShootout;
	}

	public void setPenaltyShootout(PenaltyShootout penaltyShootout) {
		this.penaltyShootout = penaltyShootout;
	}
}