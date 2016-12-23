package com.paolorizzo.predictor.client.model.v2.competition;


public class _links {
	private Fixtures fixtures;

	private Teams teams;

	private Self self;

	private LeagueTable leagueTable;

	public Fixtures getFixtures() {
		return fixtures;
	}

	public void setFixtures(Fixtures fixtures) {
		this.fixtures = fixtures;
	}

	public Teams getTeams() {
		return teams;
	}

	public void setTeams(Teams teams) {
		this.teams = teams;
	}

	public Self getSelf() {
		return self;
	}

	public void setSelf(Self self) {
		this.self = self;
	}

	public LeagueTable getLeagueTable() {
		return leagueTable;
	}

	public void setLeagueTable(LeagueTable leagueTable) {
		this.leagueTable = leagueTable;
	}

	@Override
	public String toString() {
		return "ClassPojo [fixtures = " + fixtures + ", teams = " + teams
				+ ", self = " + self + ", leagueTable = " + leagueTable + "]";
	}
}
