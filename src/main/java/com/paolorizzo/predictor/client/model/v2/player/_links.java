package com.paolorizzo.predictor.client.model.v2.player;

import com.paolorizzo.predictor.client.model.v2.Team_API;

public class _links {
	private Self self;

	private Team_API team;

	public Self getSelf() {
		return self;
	}

	public void setSelf(Self self) {
		this.self = self;
	}

	public Team_API getTeam() {
		return team;
	}

	public void setTeam(Team_API team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "ClassPojo [self = " + self + ", team = " + team + "]";
	}
}
