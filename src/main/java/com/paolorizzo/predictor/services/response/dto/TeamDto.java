package com.paolorizzo.predictor.services.response.dto;

import java.util.List;

public class TeamDto {

	private String code;

	private Long squadMarketValue;

	private String crestUrl;

	private String name;

	private String shortName;

	private TeamStatsDto teamStatsDto;

	public TeamDto() {
		// TODO Auto-generated constructor stub
	}

	

	public TeamDto(String code, Long squadMarketValue, String crestUrl,
			String name, String shortName, TeamStatsDto teamStatsDto) {
		super();
		this.code = code;
		this.squadMarketValue = squadMarketValue;
		this.crestUrl = crestUrl;
		this.name = name;
		this.shortName = shortName;
		this.teamStatsDto = teamStatsDto;
	}



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getSquadMarketValue() {
		return squadMarketValue;
	}

	public void setSquadMarketValue(Long squadMarketValue) {
		this.squadMarketValue = squadMarketValue;
	}

	public String getCrestUrl() {
		return crestUrl;
	}

	public void setCrestUrl(String crestUrl) {
		this.crestUrl = crestUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}



	public TeamStatsDto getTeamStatsDto() {
		return teamStatsDto;
	}



	public void setTeamStatsDto(TeamStatsDto teamStatsDto) {
		this.teamStatsDto = teamStatsDto;
	}

	
}
