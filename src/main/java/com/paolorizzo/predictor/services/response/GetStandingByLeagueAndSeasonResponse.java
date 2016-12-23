package com.paolorizzo.predictor.services.response;

import java.util.List;

import com.paolorizzo.predictor.dto.StandingDto;

public class GetStandingByLeagueAndSeasonResponse {

	private List<StandingDto> standings;

	public List<StandingDto> getStandings() {
		return standings;
	}

	public void setStandings(List<StandingDto> standings) {
		this.standings = standings;
	}

}
