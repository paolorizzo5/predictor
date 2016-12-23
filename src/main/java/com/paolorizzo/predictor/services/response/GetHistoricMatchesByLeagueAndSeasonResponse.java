package com.paolorizzo.predictor.services.response;

import java.util.List;

import com.paolorizzo.predictor.dto.HistoricMatchDto;

public class GetHistoricMatchesByLeagueAndSeasonResponse {

	private List<HistoricMatchDto> historicMatches;

	public GetHistoricMatchesByLeagueAndSeasonResponse() {
		// TODO Auto-generated constructor stub
	}

	public List<HistoricMatchDto> getHistoricMatches() {
		return historicMatches;
	}

	public void setHistoricMatches(List<HistoricMatchDto> historicMatches) {
		this.historicMatches = historicMatches;
	}

}
