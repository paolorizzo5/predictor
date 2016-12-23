package com.paolorizzo.xmlsoccer.data.converter;

import java.util.ArrayList;
import java.util.List;

import com.github.pabloo99.xmlsoccer.api.dto.GetLeagueStandingsResultDto;
import com.paolorizzo.predictor.dto.StandingDto;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_League;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Standing;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Team;

public class StandingDataConverter {

	public static XmlSoccer_Standing convert(
			GetLeagueStandingsResultDto getLeagueStandingsResultDto,
			String league, String season) {
		XmlSoccer_Standing standing = new XmlSoccer_Standing();
		standing.setDraw(getLeagueStandingsResultDto.getDraw());
		standing.setGoalDifference(getLeagueStandingsResultDto
				.getGoalDifference());
		standing.setGoalsAgainst(getLeagueStandingsResultDto.getGoalsAgainst());
		standing.setGoalsFor(getLeagueStandingsResultDto.getGoalsFor());
		standing.setId(getLeagueStandingsResultDto.getId());
		standing.setLeague(new XmlSoccer_League(Integer.parseInt(league)));
		standing.setLost(getLeagueStandingsResultDto.getLost());
		standing.setNumberOfShots(getLeagueStandingsResultDto
				.getNumberOfShots());
		standing.setPlayed(getLeagueStandingsResultDto.getPlayed());
		standing.setPlayedAtHome(getLeagueStandingsResultDto.getPlayedAtHome());
		standing.setPlayedAway(getLeagueStandingsResultDto.getPlayedAway());
		standing.setPoints(getLeagueStandingsResultDto.getPoints());
		standing.setRedCards(getLeagueStandingsResultDto.getRedCards());
		standing.setSeason(season);
		standing.setTeam(new XmlSoccer_Team(getLeagueStandingsResultDto
				.getTeamId()));
		standing.setWon(getLeagueStandingsResultDto.getWon());
		standing.setYellowCards(getLeagueStandingsResultDto.getYellowCards());

		return standing;
	}

	public static List<StandingDto> convert(List<XmlSoccer_Standing> standings) {
		List<StandingDto> list = new ArrayList<StandingDto>();

		for (XmlSoccer_Standing xmlSoccer_Standing : standings) {
			StandingDto element = new StandingDto();
			element.setDraw(xmlSoccer_Standing.getDraw());
			element.setGoalDifference(xmlSoccer_Standing.getGoalDifference());
			element.setGoalsAgainst(xmlSoccer_Standing.getGoalsAgainst());
			element.setGoalsFor(xmlSoccer_Standing.getGoalsFor());
			element.setId(xmlSoccer_Standing.getId());
			element.setLeague(LeagueDataConverter.convert(xmlSoccer_Standing
					.getLeague()));
			element.setLost(xmlSoccer_Standing.getLost());
			element.setNumberOfShots(xmlSoccer_Standing.getNumberOfShots());
			element.setPlayed(xmlSoccer_Standing.getPlayed());
			element.setPlayedAtHome(xmlSoccer_Standing.getPlayedAtHome());
			element.setPlayedAway(xmlSoccer_Standing.getPlayedAway());
			element.setPoints(xmlSoccer_Standing.getPoints());
			element.setRedCards(xmlSoccer_Standing.getRedCards());
			element.setSeason(xmlSoccer_Standing.getSeason());
			element.setTeam(TeamDataConverter.convert(xmlSoccer_Standing
					.getTeam()));
			element.setWon(xmlSoccer_Standing.getWon());
			element.setYellowCards(xmlSoccer_Standing.getYellowCards());
			list.add(element);
		}

		return list;
	}

}
