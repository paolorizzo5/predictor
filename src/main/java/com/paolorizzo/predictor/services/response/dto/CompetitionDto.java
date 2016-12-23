package com.paolorizzo.predictor.services.response.dto;

import java.util.List;

public class CompetitionDto {

	private String id;

	private Integer numberOfGames;

	private Integer numberOfTeams;

	private Long lastUpdated;

	private Integer currentMatchday;

	private Integer year;

	private String caption;

	private String league;

	private Integer numberOfMatchdays;

	private List<TeamDto> teamDtos;

	private List<FixtureDto> fixtureDtos;

	public CompetitionDto() {
		// TODO Auto-generated constructor stub
	}

	public CompetitionDto(String id, Integer numberOfGames,
			Integer numberOfTeams, Long lastUpdated, Integer currentMatchday,
			Integer year, String caption, String league,
			Integer numberOfMatchdays, List<TeamDto> teamDtos,
			List<FixtureDto> fixtureDtos) {
		super();
		this.id = id;
		this.numberOfGames = numberOfGames;
		this.numberOfTeams = numberOfTeams;
		this.lastUpdated = lastUpdated;
		this.currentMatchday = currentMatchday;
		this.year = year;
		this.caption = caption;
		this.league = league;
		this.numberOfMatchdays = numberOfMatchdays;
		this.teamDtos = teamDtos;
		this.fixtureDtos = fixtureDtos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getNumberOfGames() {
		return numberOfGames;
	}

	public void setNumberOfGames(Integer numberOfGames) {
		this.numberOfGames = numberOfGames;
	}

	public Integer getNumberOfTeams() {
		return numberOfTeams;
	}

	public void setNumberOfTeams(Integer numberOfTeams) {
		this.numberOfTeams = numberOfTeams;
	}

	public Long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Integer getCurrentMatchday() {
		return currentMatchday;
	}

	public void setCurrentMatchday(Integer currentMatchday) {
		this.currentMatchday = currentMatchday;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public Integer getNumberOfMatchdays() {
		return numberOfMatchdays;
	}

	public void setNumberOfMatchdays(Integer numberOfMatchdays) {
		this.numberOfMatchdays = numberOfMatchdays;
	}

	public List<TeamDto> getTeamDtos() {
		return teamDtos;
	}

	public void setTeamDtos(List<TeamDto> teamDtos) {
		this.teamDtos = teamDtos;
	}

	public List<FixtureDto> getFixtureDtos() {
		return fixtureDtos;
	}

	public void setFixtureDtos(List<FixtureDto> fixtureDtos) {
		this.fixtureDtos = fixtureDtos;
	}

}
