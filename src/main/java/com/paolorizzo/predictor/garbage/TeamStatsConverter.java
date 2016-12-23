package com.paolorizzo.predictor.converters;

import java.util.ArrayList;
import java.util.List;

import com.paolorizzo.predictor.hibernate.model.TeamStats;
import com.paolorizzo.predictor.services.response.dto.TeamStatsDto;

public class TeamStatsConverter {

	@Deprecated
	public static List<TeamStatsDto> convert(List<TeamStats> teamStats) {
		List<TeamStatsDto> teamStatsDtos = new ArrayList<TeamStatsDto>();

//		for (TeamStats teamStat : teamStats) {
//			teamStatsDtos.add(new TeamStatsDto(teamStat.getBettype()
//					.getDescription(),teamStat.getBettype().getBettypeGroup().getId(), teamStat.getSuccessFulCases(),teamStat.getTotalCases(), teamStat
//					.getAtHome()));
//		}
		return teamStatsDtos;
	}

}
