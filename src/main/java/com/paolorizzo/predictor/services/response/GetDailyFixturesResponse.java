package com.paolorizzo.predictor.services.response;

import java.util.List;

import com.paolorizzo.predictor.dto.FixtureDto;

public class GetDailyFixturesResponse {

	private List<FixtureDto> fixtures;

	public GetDailyFixturesResponse() {
		// TODO Auto-generated constructor stub
	}

	public List<FixtureDto> getFixtures() {
		return fixtures;
	}

	public void setFixtures(List<FixtureDto> fixtures) {
		this.fixtures = fixtures;
	}

}
