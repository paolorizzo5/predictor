package com.paolorizzo.predictor.client.model.v2;

import com.paolorizzo.predictor.client.model.v2.competition._links;

public class Competition_API {

	private String id;

	private String numberOfGames;

	private String numberOfTeams;

	private String lastUpdated;

	private String currentMatchday;

	private _links _links;

	private String year;

	private String caption;

	private String league;

	private String numberOfMatchdays;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumberOfGames() {
		return numberOfGames;
	}

	public void setNumberOfGames(String numberOfGames) {
		this.numberOfGames = numberOfGames;
	}

	public String getNumberOfTeams() {
		return numberOfTeams;
	}

	public void setNumberOfTeams(String numberOfTeams) {
		this.numberOfTeams = numberOfTeams;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getCurrentMatchday() {
		return currentMatchday;
	}

	public void setCurrentMatchday(String currentMatchday) {
		this.currentMatchday = currentMatchday;
	}

	public _links get_links() {
		return _links;
	}

	public void set_links(_links _links) {
		this._links = _links;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
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

	public String getNumberOfMatchdays() {
		return numberOfMatchdays;
	}

	public void setNumberOfMatchdays(String numberOfMatchdays) {
		this.numberOfMatchdays = numberOfMatchdays;
	}

	@Override
	public String toString() {
		return "ClassPojo [id = " + id + ", numberOfGames = " + numberOfGames
				+ ", numberOfTeams = " + numberOfTeams + ", lastUpdated = "
				+ lastUpdated + ", currentMatchday = " + currentMatchday
				+ ", _links = " + _links + ", year = " + year + ", caption = "
				+ caption + ", league = " + league + ", numberOfMatchdays = "
				+ numberOfMatchdays + "]";
	}
}