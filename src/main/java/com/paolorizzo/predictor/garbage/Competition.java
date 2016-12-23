package com.paolorizzo.predictor.garbage;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COMPETITIONS")
public class Competition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3595388671607493690L;

	@Id
	@Column(name = "ID", nullable = false, length = 10)
	private String id;

	@Column(name = "NUMBER_OF_GAMES", nullable = false, length = 4)
	private Integer numberOfGames;

	@Column(name = "NUMBER_OF_TEAMS", nullable = false, length = 4)
	private Integer numberOfTeams;

	@Column(name = "LAST_UPDATED", nullable = false)
	private Long lastUpdated;

	@Column(name = "CURRENT_MATCHDAY", nullable = false, length = 4)
	private Integer currentMatchday;

	@Column(name = "YEAR", nullable = false, length = 4)
	private Integer year;

	@Column(name = "CAPTION", nullable = false, length = 50)
	private String caption;

	@Column(name = "LEAGUE", nullable = false, length = 50)
	private String league;

	@Column(name = "NUMBER_OF_MATCHDAYS", nullable = false, length = 20)
	private Integer numberOfMatchdays;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "competition")
	private List<Team> teams;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "competition")
	private List<Fixture> fixtures;

	public Competition() {
		// TODO Auto-generated constructor stub
	}

	public Competition(String id) {
		super();
		this.id = id;
	}

	public Competition(String id, Integer numberOfGames, Integer numberOfTeams,
			Long lastUpdated, Integer currentMatchday, Integer year,
			String caption, String league, Integer numberOfMatchdays,
			List<Team> teams, List<Fixture> fixtures) {
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
		this.teams = teams;
		this.fixtures = fixtures;
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

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<Fixture> getFixtures() {
		return fixtures;
	}

	public void setFixtures(List<Fixture> fixtures) {
		this.fixtures = fixtures;
	}

	@Override
	public String toString() {
		return "Competition [id=" + id + ", numberOfGames=" + numberOfGames
				+ ", numberOfTeams=" + numberOfTeams + ", lastUpdated="
				+ lastUpdated + ", currentMatchday=" + currentMatchday
				+ ", year=" + year + ", caption=" + caption + ", league="
				+ league + ", numberOfMatchdays=" + numberOfMatchdays
				+ ", teams=" + teams + ", fixtures=" + fixtures + "]";
	}

}
