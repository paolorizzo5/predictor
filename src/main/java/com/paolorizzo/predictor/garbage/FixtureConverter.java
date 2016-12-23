package com.paolorizzo.predictor.converters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.paolorizzo.predictor.hibernate.model.Fixture;
import com.paolorizzo.predictor.services.response.dto.FixtureDto;

public class FixtureConverter {

	public static List<FixtureDto> convert(List<Fixture> fixtures) {
		List<FixtureDto> fixtureDtos = new ArrayList<FixtureDto>();
		for (Fixture fixture : fixtures) {
			FixtureDto fixtureDto = new FixtureDto();
			fixtureDto.setAwayTeamName(fixture.getAwayTeamName());
			fixtureDto.setDate(fixture.getDate());
			fixtureDto.setDateTillStart(fixture.getDate() - System.currentTimeMillis());
			fixtureDto.setExtraTimeAwayGoals(fixture.getExtraTimeAwayGoals());
			fixtureDto.setExtraTimeHomeGoals(fixture.getExtraTimeHomeGoals());
			fixtureDto.setFullTimeAwayGoals(fixture.getFinalScore()
					.getAwayGoals());
			fixtureDto.setFullTimeHomeGoals(fixture.getFinalScore()
					.getHomeGoals());
			fixtureDto.setHalfTimeAwayGoals(fixture.getHalfTimeAwayGoals());
			fixtureDto.setHalfTimeHomeGoals(fixture.getHalfTimeHomeGoals());
			fixtureDto.setHomeTeamName(fixture.getHomeTeamName());
			fixtureDto.setMatchday(fixture.getMatchday());
			fixtureDto.setStatus(fixture.getStatus());
			fixtureDto.setUrl(fixture.getUrl());
			fixtureDto.setHomeTeamCode(fixture.getHomeTeamCode());
			fixtureDto.setAwayTeamCode(fixture.getAwayTeamCode());
			fixtureDto.setCompetitionId(fixture.getCompetition().getId());

			fixtureDtos.add(fixtureDto);
		}
		Collections.sort(fixtureDtos);
		return fixtureDtos;
	}

}
