package com.paolorizzo.predictor.client.model.v2.competitionfixtures;

public class Odds {
	private String draw;

	private String awayWin;

	private String homeWin;

	public String getDraw() {
		return draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public String getAwayWin() {
		return awayWin;
	}

	public void setAwayWin(String awayWin) {
		this.awayWin = awayWin;
	}

	public String getHomeWin() {
		return homeWin;
	}

	public void setHomeWin(String homeWin) {
		this.homeWin = homeWin;
	}

	@Override
	public String toString() {
		return "ClassPojo [draw = " + draw + ", awayWin = " + awayWin
				+ ", homeWin = " + homeWin + "]";
	}
}
