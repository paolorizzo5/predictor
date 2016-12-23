package com.paolorizzo.predictor.dto;

import java.util.Date;

public class LeagueDto {

	private Integer id;

	private String name;

	private String country;

	private String historicalData;

	private String fixtures;

	private String livescore;

	private int numberOfMatches;

	private Date latestMatch;

	public LeagueDto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHistoricalData() {
		return historicalData;
	}

	public void setHistoricalData(String historicalData) {
		this.historicalData = historicalData;
	}

	public String getFixtures() {
		return fixtures;
	}

	public void setFixtures(String fixtures) {
		this.fixtures = fixtures;
	}

	public String getLivescore() {
		return livescore;
	}

	public void setLivescore(String livescore) {
		this.livescore = livescore;
	}

	public int getNumberOfMatches() {
		return numberOfMatches;
	}

	public void setNumberOfMatches(int numberOfMatches) {
		this.numberOfMatches = numberOfMatches;
	}

	public Date getLatestMatch() {
		return latestMatch;
	}

	public void setLatestMatch(Date latestMatch) {
		this.latestMatch = latestMatch;
	}

}
