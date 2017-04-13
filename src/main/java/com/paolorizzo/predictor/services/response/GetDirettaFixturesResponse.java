package com.paolorizzo.predictor.services.response;

import java.util.List;

import com.paolorizzo.predictor.dto.DirettaFixtureDto;
import com.paolorizzo.predictor.dto.DirettaStatsDto;

public class GetDirettaFixturesResponse {
	
	private List<DirettaFixtureDto> direttaFixtures;
	
	private DirettaStatsDto stats;

	public List<DirettaFixtureDto> getDirettaFixtures() {
		return direttaFixtures;
	}

	public void setDirettaFixtures(List<DirettaFixtureDto> direttaFixtures) {
		this.direttaFixtures = direttaFixtures;
	}

	public DirettaStatsDto getStats() {
		return stats;
	}

	public void setStats(DirettaStatsDto stats) {
		this.stats = stats;
	}
	
	
	

}
