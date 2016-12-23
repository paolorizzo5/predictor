package com.paolorizzo.xmlsoccer.data.converter;

import java.util.ArrayList;
import java.util.List;

import com.github.pabloo99.xmlsoccer.api.dto.GetHistoricMatchesResultDto;
import com.paolorizzo.predictor.dto.HistoricMatchDto;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_HistoricMatch;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_League;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Team;

public class HistoricMatchDataConverter {

	public static XmlSoccer_HistoricMatch convert(
			GetHistoricMatchesResultDto getHistoricMatchesResultDto,
			String league, String season) {
		XmlSoccer_HistoricMatch historicMatch = new XmlSoccer_HistoricMatch();
		historicMatch.setAwayCorners(getHistoricMatchesResultDto
				.getAwayCorners());
		historicMatch.setAwayFouls(getHistoricMatchesResultDto.getAwayFouls());
		historicMatch.setAwayGoalDetails(getHistoricMatchesResultDto
				.getAwayGoalDetails());
		historicMatch.setAwayGoals(getHistoricMatchesResultDto.getAwayGoals());
		historicMatch.setAwayLineupDefense(getHistoricMatchesResultDto
				.getAwayLineupDefense());
		historicMatch.setAwayLineupForward(getHistoricMatchesResultDto
				.getAwayLineupForward());
		historicMatch.setAwayLineupGoalkeeper(getHistoricMatchesResultDto
				.getAwayLineupGoalkeeper());
		historicMatch.setAwayLineupMidfield(getHistoricMatchesResultDto
				.getAwayLineupMidfield());
		historicMatch.setAwayRedCards(getHistoricMatchesResultDto
				.getAwayRedCards());
		historicMatch.setAwayShots(getHistoricMatchesResultDto.getAwayShots());
		historicMatch.setAwayShotsOnTarget(getHistoricMatchesResultDto
				.getAwayShotsOnTarget());
		historicMatch.setAwayTeam(new XmlSoccer_Team(
				getHistoricMatchesResultDto.getAwayTeamId()));
		historicMatch.setAwayTeamFormation(getHistoricMatchesResultDto
				.getAwayTeamFormation());
		historicMatch.setAwayTeamRedCardDetails(getHistoricMatchesResultDto
				.getAwayTeamRedCardDetails());
		historicMatch.setAwayTeamYellowCardDetails(getHistoricMatchesResultDto
				.getAwayTeamYellowCardDetails());
		historicMatch.setAwayYellowCards(getHistoricMatchesResultDto
				.getAwayYellowCards());
		historicMatch.setDate(getHistoricMatchesResultDto.getDate());
		historicMatch.setFixtureMatchId(getHistoricMatchesResultDto
				.getFixtureMatchId());
		historicMatch.setHalfTimeAwayGoals(getHistoricMatchesResultDto
				.getHalfTimeAwayGoals());
		historicMatch.setHalfTimeHomeGoals(getHistoricMatchesResultDto
				.getHalfTimeHomeGoals());
		historicMatch.setHomeCorners(getHistoricMatchesResultDto
				.getHomeCorners());
		historicMatch.setHomeFouls(getHistoricMatchesResultDto.getHomeFouls());
		historicMatch.setHomeGoalDetails(getHistoricMatchesResultDto
				.getHomeGoalDetails());
		historicMatch.setHomeGoals(getHistoricMatchesResultDto.getHomeGoals());
		historicMatch.setHomeLineupDefense(getHistoricMatchesResultDto
				.getHomeLineupDefense());
		historicMatch.setHomeLineupForward(getHistoricMatchesResultDto
				.getHomeLineupForward());
		historicMatch.setHomeLineupGoalkeeper(getHistoricMatchesResultDto
				.getHomeLineupGoalkeeper());
		historicMatch.setHomeLineupMidfield(getHistoricMatchesResultDto
				.getHomeLineupMidfield());
		historicMatch.setHomeRedCards(getHistoricMatchesResultDto
				.getHomeRedCards());
		historicMatch.setHomeShots(getHistoricMatchesResultDto.getHomeShots());
		historicMatch.setHomeShotsOnTarget(getHistoricMatchesResultDto
				.getHomeShotsOnTarget());
		historicMatch.setHomeTeam(new XmlSoccer_Team(
				getHistoricMatchesResultDto.getHomeTeamId()));
		historicMatch.setHomeTeamFormation(getHistoricMatchesResultDto
				.getHomeTeamFormation());
		historicMatch.setHomeTeamRedCardDetails(getHistoricMatchesResultDto
				.getHomeTeamRedCardDetails());
		historicMatch.setHomeTeamYellowCardDetails(getHistoricMatchesResultDto
				.getHomeTeamYellowCardDetails());
		historicMatch.setHomeYellowCards(getHistoricMatchesResultDto
				.getHomeYellowCards());
		historicMatch.setId(getHistoricMatchesResultDto.getId());
		historicMatch.setLeague(new XmlSoccer_League(Integer.parseInt(league)));
		historicMatch.setRound(getHistoricMatchesResultDto.getRound());
		if (season != null) {
			historicMatch.setSeason(season);
		} else {
			historicMatch.setSeason(getHistoricMatchesResultDto.getSeason());
		}
		historicMatch
				.setSpectators(getHistoricMatchesResultDto.getSpectators());

		return historicMatch;
	}

	public static List<HistoricMatchDto> convert(
			List<XmlSoccer_HistoricMatch> historicMatches) {

		List<HistoricMatchDto> list = new ArrayList<HistoricMatchDto>();
		for (XmlSoccer_HistoricMatch xmlSoccer_HistoricMatch : historicMatches) {
			HistoricMatchDto dto = new HistoricMatchDto();
			dto.setAwayCorners(xmlSoccer_HistoricMatch.getAwayCorners());
			dto.setAwayFouls(xmlSoccer_HistoricMatch.getAwayFouls());
			dto.setAwayGoalDetails(xmlSoccer_HistoricMatch.getAwayGoalDetails());
			dto.setAwayGoals(xmlSoccer_HistoricMatch.getAwayGoals());
			dto.setAwayLineupDefense(xmlSoccer_HistoricMatch
					.getAwayLineupDefense());
			dto.setAwayLineupForward(xmlSoccer_HistoricMatch
					.getAwayLineupForward());
			dto.setAwayLineupGoalkeeper(xmlSoccer_HistoricMatch
					.getAwayLineupGoalkeeper());
			dto.setAwayLineupMidfield(xmlSoccer_HistoricMatch
					.getAwayLineupMidfield());
			dto.setAwayRedCards(xmlSoccer_HistoricMatch.getAwayRedCards());
			dto.setAwayShots(xmlSoccer_HistoricMatch.getAwayShots());
			dto.setAwayShotsOnTarget(xmlSoccer_HistoricMatch
					.getAwayShotsOnTarget());
			dto.setAwayTeam(TeamDataConverter.convert(xmlSoccer_HistoricMatch
					.getAwayTeam()));
			dto.setAwayTeamFormation(xmlSoccer_HistoricMatch
					.getAwayTeamFormation());
			dto.setAwayTeamRedCardDetails(xmlSoccer_HistoricMatch
					.getAwayTeamRedCardDetails());
			dto.setAwayTeamYellowCardDetails(xmlSoccer_HistoricMatch
					.getAwayTeamYellowCardDetails());
			dto.setAwayYellowCards(xmlSoccer_HistoricMatch.getAwayYellowCards());
			dto.setDate(xmlSoccer_HistoricMatch.getDate().getTime());
			dto.setFixtureMatchId(xmlSoccer_HistoricMatch.getFixtureMatchId());
			dto.setHalfTimeAwayGoals(xmlSoccer_HistoricMatch
					.getHalfTimeAwayGoals());
			dto.setHalfTimeHomeGoals(xmlSoccer_HistoricMatch
					.getHalfTimeHomeGoals());

			dto.setHomeCorners(xmlSoccer_HistoricMatch.getHomeCorners());
			dto.setHomeFouls(xmlSoccer_HistoricMatch.getHomeFouls());
			dto.setHomeGoalDetails(xmlSoccer_HistoricMatch.getHomeGoalDetails());
			dto.setHomeGoals(xmlSoccer_HistoricMatch.getHomeGoals());
			dto.setHomeLineupDefense(xmlSoccer_HistoricMatch
					.getHomeLineupDefense());
			dto.setHomeLineupForward(xmlSoccer_HistoricMatch
					.getHomeLineupForward());
			dto.setHomeLineupGoalkeeper(xmlSoccer_HistoricMatch
					.getHomeLineupGoalkeeper());
			dto.setHomeLineupMidfield(xmlSoccer_HistoricMatch
					.getHomeLineupMidfield());
			dto.setHomeRedCards(xmlSoccer_HistoricMatch.getHomeRedCards());
			dto.setHomeShots(xmlSoccer_HistoricMatch.getHomeShots());
			dto.setHomeShotsOnTarget(xmlSoccer_HistoricMatch
					.getHomeShotsOnTarget());
			dto.setHomeTeam(TeamDataConverter.convert(xmlSoccer_HistoricMatch
					.getHomeTeam()));
			dto.setHomeTeamFormation(xmlSoccer_HistoricMatch
					.getHomeTeamFormation());
			dto.setHomeTeamRedCardDetails(xmlSoccer_HistoricMatch
					.getHomeTeamRedCardDetails());
			dto.setHomeTeamYellowCardDetails(xmlSoccer_HistoricMatch
					.getHomeTeamYellowCardDetails());
			dto.setHomeYellowCards(xmlSoccer_HistoricMatch.getHomeYellowCards());

			dto.setId(xmlSoccer_HistoricMatch.getId());
			dto.setRound(xmlSoccer_HistoricMatch.getRound());
			dto.setSpectators(xmlSoccer_HistoricMatch.getSpectators());

			list.add(dto);

		}
		return list;
	}
}
