package com.paolorizzo.xmlsoccer.data.converter;

import java.util.ArrayList;
import java.util.List;

import com.github.pabloo99.xmlsoccer.api.dto.GetFixturesResultDto;
import com.paolorizzo.predictor.dto.FixtureDto;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Fixture;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_League;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Team;

public class FixtureDataConverter {

	public static XmlSoccer_Fixture convert(
			GetFixturesResultDto getFixturesResultDto, String league,
			String season) {
		XmlSoccer_Fixture xmlSoccer_Fixture = new XmlSoccer_Fixture();
		xmlSoccer_Fixture.setAwayGoals(getFixturesResultDto.getAwayGoals());
		xmlSoccer_Fixture.setAwayTeam(new XmlSoccer_Team(getFixturesResultDto
				.getAwayTeamId()));
		xmlSoccer_Fixture.setAwayTeamRedCardDetails(getFixturesResultDto
				.getAwayTeamRedCardDetails());
		xmlSoccer_Fixture.setAwayTeamYellowCardDetails(getFixturesResultDto
				.getAwayTeamYellowCardDetails());
		xmlSoccer_Fixture.setDate(getFixturesResultDto.getDate());
		xmlSoccer_Fixture.setHomeGoals(getFixturesResultDto.getHomeGoals());
		xmlSoccer_Fixture.setHomeTeam(new XmlSoccer_Team(getFixturesResultDto
				.getHomeTeamId()));
		xmlSoccer_Fixture.setHomeTeamRedCardDetails(getFixturesResultDto
				.getHomeTeamRedCardDetails());
		xmlSoccer_Fixture.setHomeTeamYellowCardDetails(getFixturesResultDto
				.getHomeTeamYellowCardDetails());
		xmlSoccer_Fixture.setId(getFixturesResultDto.getId());
		xmlSoccer_Fixture.setLeague(new XmlSoccer_League(Integer
				.parseInt(league)));
		xmlSoccer_Fixture.setLocation(getFixturesResultDto.getLocation());
		xmlSoccer_Fixture.setSeason(season);
		return xmlSoccer_Fixture;
	}

	public static List<FixtureDto> convert(List<XmlSoccer_Fixture> fixtures) {
		List<FixtureDto> fixtureDtos = new ArrayList<FixtureDto>();

		for (XmlSoccer_Fixture xmlSoccer_Fixture : fixtures) {
			FixtureDto dto = new FixtureDto();
			dto.setAwayGoals(xmlSoccer_Fixture.getAwayGoals());
			dto.setAwayTeam(TeamDataConverter.convert(xmlSoccer_Fixture
					.getAwayTeam()));
			dto.setAwayTeamRedCardDetails(xmlSoccer_Fixture
					.getAwayTeamRedCardDetails());
			dto.setAwayTeamYellowCardDetails(xmlSoccer_Fixture
					.getAwayTeamYellowCardDetails());
			dto.setDate(xmlSoccer_Fixture.getDate().getTime());
			dto.setHomeGoals(xmlSoccer_Fixture.getHomeGoals());
			dto.setHomeTeam(TeamDataConverter.convert(xmlSoccer_Fixture
					.getHomeTeam()));
			dto.setHomeTeamRedCardDetails(xmlSoccer_Fixture
					.getHomeTeamRedCardDetails());
			dto.setHomeTeamYellowCardDetails(xmlSoccer_Fixture
					.getHomeTeamYellowCardDetails());
			dto.setId(xmlSoccer_Fixture.getId());
			dto.setLeague(LeagueDataConverter.convert(xmlSoccer_Fixture
					.getLeague()));
			dto.setLocation(xmlSoccer_Fixture.getLocation());
			dto.setOdds(OddDataConverter.convert(xmlSoccer_Fixture.getOdds()));
			dto.setRound(xmlSoccer_Fixture.getRound());
			dto.setTime(xmlSoccer_Fixture.getTime());
			fixtureDtos.add(dto);
		}
		return fixtureDtos;
	}
}
