package com.paolorizzo.predictor.client.model.v2;

import com.paolorizzo.predictor.client.model.v2.competitionfixtures.Fixture;
import com.paolorizzo.predictor.client.model.v2.fixturedetail.Head2head;

public class FixtureDetail_API {
	private Head2head head2head;

	private Fixture fixture;

	public Head2head getHead2head() {
		return head2head;
	}

	public void setHead2head(Head2head head2head) {
		this.head2head = head2head;
	}

	public Fixture getFixture() {
		return fixture;
	}

	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}

	@Override
	public String toString() {
		return "ClassPojo [head2head = " + head2head + ", fixture = " + fixture
				+ "]";
	}
}