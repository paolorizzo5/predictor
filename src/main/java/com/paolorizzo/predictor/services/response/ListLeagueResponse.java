package com.paolorizzo.predictor.services.response;

import java.util.List;

import com.paolorizzo.predictor.dto.LeagueDto;

public class ListLeagueResponse {

	private List<LeagueDto> leagues;

	public ListLeagueResponse() {
		// TODO Auto-generated constructor stub
	}

	public ListLeagueResponse(List<LeagueDto> leagues) {
		super();
		this.leagues = leagues;
	}

	public List<LeagueDto> getLeagues() {
		return leagues;
	}

	public void setLeagues(List<LeagueDto> leagues) {
		this.leagues = leagues;
	}

}
