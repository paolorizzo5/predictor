package com.paolorizzo.predictor.client.model.v2.fixturedetail;

public class Head2head {
	private Fixtures[] fixtures;

	private LastHomeWinHomeTeam lastHomeWinHomeTeam;

	private String homeTeamWins;

	private LastAwayWinAwayTeam lastAwayWinAwayTeam;

	private String count;

	private LastWinHomeTeam lastWinHomeTeam;

	private String timeFrameStart;

	private String awayTeamWins;

	private LastWinAwayTeam lastWinAwayTeam;

	private String draws;

	private String timeFrameEnd;

	public Fixtures[] getFixtures() {
		return fixtures;
	}

	public void setFixtures(Fixtures[] fixtures) {
		this.fixtures = fixtures;
	}

	public LastHomeWinHomeTeam getLastHomeWinHomeTeam() {
		return lastHomeWinHomeTeam;
	}

	public void setLastHomeWinHomeTeam(LastHomeWinHomeTeam lastHomeWinHomeTeam) {
		this.lastHomeWinHomeTeam = lastHomeWinHomeTeam;
	}

	public String getHomeTeamWins() {
		return homeTeamWins;
	}

	public void setHomeTeamWins(String homeTeamWins) {
		this.homeTeamWins = homeTeamWins;
	}

	public LastAwayWinAwayTeam getLastAwayWinAwayTeam() {
		return lastAwayWinAwayTeam;
	}

	public void setLastAwayWinAwayTeam(LastAwayWinAwayTeam lastAwayWinAwayTeam) {
		this.lastAwayWinAwayTeam = lastAwayWinAwayTeam;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public LastWinHomeTeam getLastWinHomeTeam() {
		return lastWinHomeTeam;
	}

	public void setLastWinHomeTeam(LastWinHomeTeam lastWinHomeTeam) {
		this.lastWinHomeTeam = lastWinHomeTeam;
	}

	public String getTimeFrameStart() {
		return timeFrameStart;
	}

	public void setTimeFrameStart(String timeFrameStart) {
		this.timeFrameStart = timeFrameStart;
	}

	public String getAwayTeamWins() {
		return awayTeamWins;
	}

	public void setAwayTeamWins(String awayTeamWins) {
		this.awayTeamWins = awayTeamWins;
	}

	public LastWinAwayTeam getLastWinAwayTeam() {
		return lastWinAwayTeam;
	}

	public void setLastWinAwayTeam(LastWinAwayTeam lastWinAwayTeam) {
		this.lastWinAwayTeam = lastWinAwayTeam;
	}

	public String getDraws() {
		return draws;
	}

	public void setDraws(String draws) {
		this.draws = draws;
	}

	public String getTimeFrameEnd() {
		return timeFrameEnd;
	}

	public void setTimeFrameEnd(String timeFrameEnd) {
		this.timeFrameEnd = timeFrameEnd;
	}

	@Override
	public String toString() {
		return "ClassPojo [fixtures = " + fixtures + ", lastHomeWinHomeTeam = "
				+ lastHomeWinHomeTeam + ", homeTeamWins = " + homeTeamWins
				+ ", lastAwayWinAwayTeam = " + lastAwayWinAwayTeam
				+ ", count = " + count + ", lastWinHomeTeam = "
				+ lastWinHomeTeam + ", timeFrameStart = " + timeFrameStart
				+ ", awayTeamWins = " + awayTeamWins + ", lastWinAwayTeam = "
				+ lastWinAwayTeam + ", draws = " + draws + ", timeFrameEnd = "
				+ timeFrameEnd + "]";
	}
}