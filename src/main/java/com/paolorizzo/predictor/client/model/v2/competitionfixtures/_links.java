package com.paolorizzo.predictor.client.model.v2.competitionfixtures;

import com.paolorizzo.predictor.client.model.v2.fixture.AwayTeam;
import com.paolorizzo.predictor.client.model.v2.fixture.HomeTeam;

public class _links {
	private AwayTeam awayTeam;

	private Self self;

	private HomeTeam homeTeam;

	private Competition competition;

	public AwayTeam getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(AwayTeam awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Self getSelf() {
		return self;
	}

	public void setSelf(Self self) {
		this.self = self;
	}

	public HomeTeam getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(HomeTeam homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	@Override
	public String toString() {
		return "ClassPojo [awayTeam = " + awayTeam + ", self = " + self
				+ ", homeTeam = " + homeTeam + ", competition = " + competition
				+ "]";
	}
}
