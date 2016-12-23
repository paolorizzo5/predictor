package com.paolorizzo.predictor.converters;

import java.util.ArrayList;
import java.util.List;

import com.paolorizzo.predictor.hibernate.model.Competition;
import com.paolorizzo.predictor.services.response.dto.CompetitionDto;

public class CompetitionConverter {

	public static List<CompetitionDto> convert(List<Competition> competitions) {
		List<CompetitionDto> competitionDtos = new ArrayList<CompetitionDto>();
		for (Competition competition : competitions) {
			CompetitionDto competitionDto = new CompetitionDto();
			competitionDto.setCaption(competition.getCaption());
			competitionDto.setCurrentMatchday(competition.getCurrentMatchday());
			competitionDto.setFixtureDtos(FixtureConverter.convert(competition
					.getFixtures()));

			competitionDto.setId(competition.getId());
			competitionDto.setLastUpdated(competition.getLastUpdated());
			competitionDto.setLeague(competition.getLeague());
			competitionDto.setNumberOfGames(competition.getNumberOfGames());
			competitionDto.setNumberOfMatchdays(competition
					.getNumberOfMatchdays());
			competitionDto.setNumberOfTeams(competition.getNumberOfTeams());
			competitionDto.setTeamDtos(TeamConverter.convert(competition
					.getTeams()));
			competitionDto.setYear(competition.getYear());
			competitionDtos.add(competitionDto);
		}
		return competitionDtos;

	}

	public static List<CompetitionDto> convertMinimal(
			List<Competition> competitions) {
		List<CompetitionDto> competitionDtos = new ArrayList<CompetitionDto>();
		for (Competition competition : competitions) {
			CompetitionDto competitionDto = new CompetitionDto();
			competitionDto.setCaption(competition.getCaption());
			competitionDto.setCurrentMatchday(competition.getCurrentMatchday());

			competitionDto.setId(competition.getId());
			competitionDto.setLastUpdated(competition.getLastUpdated());
			competitionDto.setLeague(competition.getLeague());
			competitionDto.setNumberOfGames(competition.getNumberOfGames());
			competitionDto.setNumberOfMatchdays(competition
					.getNumberOfMatchdays());

			competitionDto.setYear(competition.getYear());
			competitionDtos.add(competitionDto);
		}
		return competitionDtos;

	}
}
