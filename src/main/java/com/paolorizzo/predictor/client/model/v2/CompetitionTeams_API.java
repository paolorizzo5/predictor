package com.paolorizzo.predictor.client.model.v2;

import com.paolorizzo.predictor.client.model.v2.competitionteams._links;

public class CompetitionTeams_API {
	private Team_API[] teams;

	private String count;

	private _links _links;

	public Team_API[] getTeams() {
		return teams;
	}

	public void setTeams(Team_API[] teams) {
		this.teams = teams;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public _links get_links() {
		return _links;
	}

	public void set_links(_links _links) {
		this._links = _links;
	}

	@Override
	public String toString() {
		return "ClassPojo [teams = " + teams + ", count = " + count
				+ ", _links = " + _links + "]";
	}
}