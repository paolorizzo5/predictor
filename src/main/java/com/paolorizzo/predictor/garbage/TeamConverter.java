package com.paolorizzo.predictor.converters;

import java.util.ArrayList;
import java.util.List;

import com.paolorizzo.predictor.hibernate.model.Team;
import com.paolorizzo.predictor.services.response.dto.TeamDto;
import com.paolorizzo.predictor.services.response.dto.TeamStatsDto;

public class TeamConverter {

	public static List<TeamDto> convert(List<Team> teams) {
		List<TeamDto> teamDtos = new ArrayList<TeamDto>();

		for (Team team : teams) {
			TeamDto teamDto = new TeamDto();
			teamDto.setCode(team.getCode());
			teamDto.setCrestUrl(team.getCrestUrl());
			teamDto.setName(team.getName());
			teamDto.setShortName(team.getShortName());
			teamDto.setSquadMarketValue(team.getSquadMarketValue());
			teamDtos.add(teamDto);
			teamDto.setTeamStatsDto(new TeamStatsDto());
			
		}
		return teamDtos;
	}
}
