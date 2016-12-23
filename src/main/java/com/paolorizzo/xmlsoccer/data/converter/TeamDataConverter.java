package com.paolorizzo.xmlsoccer.data.converter;

import com.github.pabloo99.xmlsoccer.api.dto.GetTeamResultDto;
import com.paolorizzo.predictor.dto.TeamDto;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Team;

public class TeamDataConverter {

	public static XmlSoccer_Team convert(GetTeamResultDto getTeamResultDto) {
		XmlSoccer_Team team = new XmlSoccer_Team();
		team.setCountry(getTeamResultDto.getCountry());
		team.setHomePageURL(getTeamResultDto.getHomePageURL());
		team.setId(getTeamResultDto.getTeamId());
		team.setName(getTeamResultDto.getName());
		team.setStadium(getTeamResultDto.getStadium());
		team.setWikiLink(getTeamResultDto.getWikiLink());

		return team;
	}

	public static TeamDto convert(XmlSoccer_Team team) {
		TeamDto dto = new TeamDto();
		dto.setCountry(team.getCountry());
		dto.setHomePageURL(team.getHomePageURL());
		dto.setId(team.getId());
		dto.setName(team.getName());
		dto.setStadium(team.getStadium());
		dto.setWikiLink(team.getWikiLink());
		return dto;
	}

}
