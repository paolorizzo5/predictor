package com.paolorizzo.predictor.services.response.dto;

public class FixtureDto implements Comparable<FixtureDto> {

	private Integer halfTimeHomeGoals;

	private Integer halfTimeAwayGoals;

	private Integer fullTimeHomeGoals;

	private Integer fullTimeAwayGoals;

	private Integer extraTimeHomeGoals;

	private Integer extraTimeAwayGoals;

	private String status;

	private String matchday;

	private String awayTeamName;

	private String awayTeamCode;

	private Long date;
	
	private Long dateTillStart;

	private String homeTeamName;

	private String homeTeamCode;

	private String url;
	
	private String competitionId;

	public FixtureDto() {
		// TODO Auto-generated constructor stub
	}

	

	
	public FixtureDto(Integer halfTimeHomeGoals, Integer halfTimeAwayGoals,
			Integer fullTimeHomeGoals, Integer fullTimeAwayGoals,
			Integer extraTimeHomeGoals, Integer extraTimeAwayGoals,
			String status, String matchday, String awayTeamName,
			String awayTeamCode, Long date, Long dateTillStart,
			String homeTeamName, String homeTeamCode, String url,
			String competitionId) {
		super();
		this.halfTimeHomeGoals = halfTimeHomeGoals;
		this.halfTimeAwayGoals = halfTimeAwayGoals;
		this.fullTimeHomeGoals = fullTimeHomeGoals;
		this.fullTimeAwayGoals = fullTimeAwayGoals;
		this.extraTimeHomeGoals = extraTimeHomeGoals;
		this.extraTimeAwayGoals = extraTimeAwayGoals;
		this.status = status;
		this.matchday = matchday;
		this.awayTeamName = awayTeamName;
		this.awayTeamCode = awayTeamCode;
		this.date = date;
		this.dateTillStart = dateTillStart;
		this.homeTeamName = homeTeamName;
		this.homeTeamCode = homeTeamCode;
		this.url = url;
		this.competitionId = competitionId;
	}




	public String getCompetitionId() {
		return competitionId;
	}




	public void setCompetitionId(String competitionId) {
		this.competitionId = competitionId;
	}




	public Integer getHalfTimeHomeGoals() {
		return halfTimeHomeGoals;
	}

	public void setHalfTimeHomeGoals(Integer halfTimeHomeGoals) {
		this.halfTimeHomeGoals = halfTimeHomeGoals;
	}

	public Integer getHalfTimeAwayGoals() {
		return halfTimeAwayGoals;
	}

	public void setHalfTimeAwayGoals(Integer halfTimeAwayGoals) {
		this.halfTimeAwayGoals = halfTimeAwayGoals;
	}

	public Integer getFullTimeHomeGoals() {
		return fullTimeHomeGoals;
	}

	public void setFullTimeHomeGoals(Integer fullTimeHomeGoals) {
		this.fullTimeHomeGoals = fullTimeHomeGoals;
	}

	public Integer getFullTimeAwayGoals() {
		return fullTimeAwayGoals;
	}

	public void setFullTimeAwayGoals(Integer fullTimeAwayGoals) {
		this.fullTimeAwayGoals = fullTimeAwayGoals;
	}

	public Integer getExtraTimeHomeGoals() {
		return extraTimeHomeGoals;
	}

	public void setExtraTimeHomeGoals(Integer extraTimeHomeGoals) {
		this.extraTimeHomeGoals = extraTimeHomeGoals;
	}

	public Integer getExtraTimeAwayGoals() {
		return extraTimeAwayGoals;
	}

	public void setExtraTimeAwayGoals(Integer extraTimeAwayGoals) {
		this.extraTimeAwayGoals = extraTimeAwayGoals;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMatchday() {
		return matchday;
	}

	public void setMatchday(String matchday) {
		this.matchday = matchday;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}

	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAwayTeamCode() {
		return awayTeamCode;
	}

	public void setAwayTeamCode(String awayTeamCode) {
		this.awayTeamCode = awayTeamCode;
	}

	public String getHomeTeamCode() {
		return homeTeamCode;
	}

	public void setHomeTeamCode(String homeTeamCode) {
		this.homeTeamCode = homeTeamCode;
	}

	@Override
	public int compareTo(FixtureDto o) {
		return this.getDate() < o.getDate() ? -1
				: this.getDate() > o.getDate() ? 1 : 0;
	}

	public Long getDateTillStart() {
		return dateTillStart;
	}

	public void setDateTillStart(Long dateTillStart) {
		this.dateTillStart = dateTillStart;
	}

}
