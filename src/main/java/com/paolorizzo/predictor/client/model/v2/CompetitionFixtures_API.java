package com.paolorizzo.predictor.client.model.v2;

import com.paolorizzo.predictor.client.model.v2.competitionfixtures.Fixture;
import com.paolorizzo.predictor.client.model.v2.competitionfixtures._links;

public class CompetitionFixtures_API {
	private Fixture[] fixtures;

	private String count;

	private _links _links;

	public Fixture[] getFixtures() {
		return fixtures;
	}

	public void setFixtures(Fixture[] fixtures) {
		this.fixtures = fixtures;
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
		return "ClassPojo [fixtures = " + fixtures + ", count = " + count
				+ ", _links = " + _links + "]";
	}
}