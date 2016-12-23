package com.paolorizzo.predictor.services.response.fixture;

import java.util.List;

import com.paolorizzo.predictor.services.response.dto.FixtureDto;

public class CompetitionTimedFixturesResponse {

	private List<FixtureDto> fixtureDtos;

	public CompetitionTimedFixturesResponse() {
		// TODO Auto-generated constructor stub
	}

	public CompetitionTimedFixturesResponse(List<FixtureDto> fixtureDtos) {
		super();
		this.fixtureDtos = fixtureDtos;
	}

	public List<FixtureDto> getFixtureDtos() {
		return fixtureDtos;
	}

	public void setFixtureDtos(List<FixtureDto> fixtureDtos) {
		this.fixtureDtos = fixtureDtos;
	}

}
