package com.paolorizzo.predictor.services.response;

import java.util.List;

import com.paolorizzo.predictor.dto.OddDto;

public class GetOddsByFixtureIdResponse {

	private List<OddDto> odds;

	public List<OddDto> getOdds() {
		return odds;
	}

	public void setOdds(List<OddDto> odds) {
		this.odds = odds;
	}

}
