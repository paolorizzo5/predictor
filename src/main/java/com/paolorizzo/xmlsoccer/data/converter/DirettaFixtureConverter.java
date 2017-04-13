package com.paolorizzo.xmlsoccer.data.converter;

import java.util.ArrayList;
import java.util.List;

import com.paolorizzo.predictor.dto.DirettaFixtureDto;
import com.paolorizzo.predictor.hibernate.model.DirettaFixture;

public class DirettaFixtureConverter {

	public static List<DirettaFixtureDto> convert(List<DirettaFixture> direttaFixtures) {
		 List<DirettaFixtureDto> list = new ArrayList<DirettaFixtureDto>();
		for (DirettaFixture direttaFixture : direttaFixtures) {
			DirettaFixtureDto dto = new DirettaFixtureDto();
			dto.setAwayGoals(direttaFixture.getAwayGoals());
			dto.setAwayTeam(direttaFixture.getAwayTeam());
			dto.setCurrentCompetition(direttaFixture.getCurrentCompetition());
			dto.setDate(direttaFixture.getDate().getTime());
			dto.setHomeGoals(direttaFixture.getHomeGoals());
			dto.setHomeTeam(direttaFixture.getHomeTeam());
			dto.setQuota1(direttaFixture.getQuota1());
			dto.setQuota2(direttaFixture.getQuota2());
			dto.setQuotaX(direttaFixture.getQuotaX());
			list.add(dto);
		}
		return list;
	}

}
