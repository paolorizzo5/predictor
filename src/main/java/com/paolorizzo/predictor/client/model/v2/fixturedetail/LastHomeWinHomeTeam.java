package com.paolorizzo.predictor.client.model.v2.fixturedetail;

import com.paolorizzo.predictor.client.model.v2.competitionfixtures.Odds;
import com.paolorizzo.predictor.client.model.v2.fixture.Result;

public class LastHomeWinHomeTeam {
	private Result result;

	private String status;

	private String matchday;

	private com.paolorizzo.predictor.client.model.v2.competitionfixtures._links _links;

	private String awayTeamName;

	private String date;

	private Odds odds;

	private String homeTeamName;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMatchday() {
		return matchday;
	}

	public void setMatchday(String matchday) {
		this.matchday = matchday;
	}

	public com.paolorizzo.predictor.client.model.v2.competitionfixtures._links get_links() {
		return _links;
	}

	public void set_links(
			com.paolorizzo.predictor.client.model.v2.competitionfixtures._links _links) {
		this._links = _links;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}

	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Odds getOdds() {
		return odds;
	}

	public void setOdds(Odds odds) {
		this.odds = odds;
	}

	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	@Override
	public String toString() {
		return "ClassPojo [result = " + result + ", status = " + status
				+ ", matchday = " + matchday + ", _links = " + _links
				+ ", awayTeamName = " + awayTeamName + ", date = " + date
				+ ", odds = " + odds + ", homeTeamName = " + homeTeamName + "]";
	}
}