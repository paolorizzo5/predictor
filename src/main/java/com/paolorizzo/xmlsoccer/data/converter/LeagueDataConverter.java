package com.paolorizzo.xmlsoccer.data.converter;

import java.util.ArrayList;
import java.util.List;

import com.github.pabloo99.xmlsoccer.api.dto.GetAllLeaguesResultDto;
import com.paolorizzo.predictor.dto.LeagueDto;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_League;

public class LeagueDataConverter {

	public static XmlSoccer_League convert(
			GetAllLeaguesResultDto getAllLeaguesResultDto) {
		XmlSoccer_League league = new XmlSoccer_League();
		league.setCountry(getAllLeaguesResultDto.getCountry());
		league.setFixtures(getAllLeaguesResultDto.getFixtures());
		league.setHistoricalData(getAllLeaguesResultDto.getHistoricalData());
		league.setId(getAllLeaguesResultDto.getId());
		league.setLatestMatch(getAllLeaguesResultDto.getLatestMatch());
		league.setLivescore(getAllLeaguesResultDto.getLivescore());
		league.setName(getAllLeaguesResultDto.getName());
		league.setNumberOfMatches(getAllLeaguesResultDto.getNumberOfMatches());
		return league;
	}

	public static List<LeagueDto> convert(List<XmlSoccer_League> leagues) {
		List<LeagueDto> list = new ArrayList<LeagueDto>();

		for (XmlSoccer_League xmlSoccer_League : leagues) {
			LeagueDto element = new LeagueDto();
			element.setCountry(xmlSoccer_League.getCountry());
			element.setFixtures(xmlSoccer_League.getFixtures());
			element.setHistoricalData(xmlSoccer_League.getHistoricalData());
			element.setId(xmlSoccer_League.getId());
			element.setLatestMatch(xmlSoccer_League.getLatestMatch());
			element.setLivescore(xmlSoccer_League.getLivescore());
			element.setName(xmlSoccer_League.getName());
			element.setNumberOfMatches(xmlSoccer_League.getNumberOfMatches());
			list.add(element);
		}
		return list;
	}

	public static LeagueDto convert(XmlSoccer_League league) {
		LeagueDto dto = new LeagueDto();
		dto.setCountry(league.getCountry());
		dto.setFixtures(league.getFixtures());
		dto.setHistoricalData(league.getHistoricalData());
		dto.setId(league.getId());
		dto.setLatestMatch(league.getLatestMatch());
		dto.setLivescore(league.getLivescore());
		dto.setName(league.getName());
		dto.setNumberOfMatches(league.getNumberOfMatches());
		return dto;
	}

}
