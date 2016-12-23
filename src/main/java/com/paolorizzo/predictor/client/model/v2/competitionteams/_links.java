package com.paolorizzo.predictor.client.model.v2.competitionteams;


public class _links {
	private Self self;

	private Competition competition;

	public Self getSelf() {
		return self;
	}

	public void setSelf(Self self) {
		this.self = self;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	@Override
	public String toString() {
		return "ClassPojo [self = " + self + ", competition = " + competition
				+ "]";
	}
}