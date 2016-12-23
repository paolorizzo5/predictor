package com.paolorizzo.predictor.dto;

public class HistoricMatchDto implements Comparable<HistoricMatchDto> {

	private Integer id;

	private Integer fixtureMatchId;

	private Long date;

	private Integer round;

	private String spectators;

	private TeamDto homeTeam;

	private Integer homeCorners;

	private Integer homeGoals;

	private Integer halfTimeHomeGoals;

	private Integer homeShots;

	private Integer homeShotsOnTarget;

	private Integer homeFouls;

	private String homeGoalDetails;

	private String homeLineupGoalkeeper;

	private String homeLineupDefense;

	private String homeLineupMidfield;

	private String homeLineupForward;

	private Integer homeYellowCards;

	private Integer homeRedCards;

	private String homeTeamFormation;

	private TeamDto awayTeam;

	private Integer awayCorners;

	private Integer awayGoals;

	private Integer halfTimeAwayGoals;

	private Integer awayShots;

	private Integer awayShotsOnTarget;

	private Integer awayFouls;

	private String awayGoalDetails;

	private String awayLineupGoalkeeper;

	private String awayLineupDefense;

	private String awayLineupMidfield;

	private String awayLineupForward;

	private Integer awayYellowCards;

	private Integer awayRedCards;

	private String awayTeamFormation;

	private String homeTeamYellowCardDetails;

	private String awayTeamYellowCardDetails;

	private String homeTeamRedCardDetails;

	private String awayTeamRedCardDetails;

	private String season;

	public HistoricMatchDto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFixtureMatchId() {
		return fixtureMatchId;
	}

	public void setFixtureMatchId(Integer fixtureMatchId) {
		this.fixtureMatchId = fixtureMatchId;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	public String getSpectators() {
		return spectators;
	}

	public void setSpectators(String spectators) {
		this.spectators = spectators;
	}

	public TeamDto getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(TeamDto homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Integer getHomeCorners() {
		return homeCorners;
	}

	public void setHomeCorners(Integer homeCorners) {
		this.homeCorners = homeCorners;
	}

	public Integer getHomeGoals() {
		return homeGoals;
	}

	public void setHomeGoals(Integer homeGoals) {
		this.homeGoals = homeGoals;
	}

	public Integer getHalfTimeHomeGoals() {
		return halfTimeHomeGoals;
	}

	public void setHalfTimeHomeGoals(Integer halfTimeHomeGoals) {
		this.halfTimeHomeGoals = halfTimeHomeGoals;
	}

	public Integer getHomeShots() {
		return homeShots;
	}

	public void setHomeShots(Integer homeShots) {
		this.homeShots = homeShots;
	}

	public Integer getHomeShotsOnTarget() {
		return homeShotsOnTarget;
	}

	public void setHomeShotsOnTarget(Integer homeShotsOnTarget) {
		this.homeShotsOnTarget = homeShotsOnTarget;
	}

	public Integer getHomeFouls() {
		return homeFouls;
	}

	public void setHomeFouls(Integer homeFouls) {
		this.homeFouls = homeFouls;
	}

	public String getHomeGoalDetails() {
		return homeGoalDetails;
	}

	public void setHomeGoalDetails(String homeGoalDetails) {
		this.homeGoalDetails = homeGoalDetails;
	}

	public String getHomeLineupGoalkeeper() {
		return homeLineupGoalkeeper;
	}

	public void setHomeLineupGoalkeeper(String homeLineupGoalkeeper) {
		this.homeLineupGoalkeeper = homeLineupGoalkeeper;
	}

	public String getHomeLineupDefense() {
		return homeLineupDefense;
	}

	public void setHomeLineupDefense(String homeLineupDefense) {
		this.homeLineupDefense = homeLineupDefense;
	}

	public String getHomeLineupMidfield() {
		return homeLineupMidfield;
	}

	public void setHomeLineupMidfield(String homeLineupMidfield) {
		this.homeLineupMidfield = homeLineupMidfield;
	}

	public String getHomeLineupForward() {
		return homeLineupForward;
	}

	public void setHomeLineupForward(String homeLineupForward) {
		this.homeLineupForward = homeLineupForward;
	}

	public Integer getHomeYellowCards() {
		return homeYellowCards;
	}

	public void setHomeYellowCards(Integer homeYellowCards) {
		this.homeYellowCards = homeYellowCards;
	}

	public Integer getHomeRedCards() {
		return homeRedCards;
	}

	public void setHomeRedCards(Integer homeRedCards) {
		this.homeRedCards = homeRedCards;
	}

	public String getHomeTeamFormation() {
		return homeTeamFormation;
	}

	public void setHomeTeamFormation(String homeTeamFormation) {
		this.homeTeamFormation = homeTeamFormation;
	}

	public TeamDto getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(TeamDto awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Integer getAwayCorners() {
		return awayCorners;
	}

	public void setAwayCorners(Integer awayCorners) {
		this.awayCorners = awayCorners;
	}

	public Integer getAwayGoals() {
		return awayGoals;
	}

	public void setAwayGoals(Integer awayGoals) {
		this.awayGoals = awayGoals;
	}

	public Integer getHalfTimeAwayGoals() {
		return halfTimeAwayGoals;
	}

	public void setHalfTimeAwayGoals(Integer halfTimeAwayGoals) {
		this.halfTimeAwayGoals = halfTimeAwayGoals;
	}

	public Integer getAwayShots() {
		return awayShots;
	}

	public void setAwayShots(Integer awayShots) {
		this.awayShots = awayShots;
	}

	public Integer getAwayShotsOnTarget() {
		return awayShotsOnTarget;
	}

	public void setAwayShotsOnTarget(Integer awayShotsOnTarget) {
		this.awayShotsOnTarget = awayShotsOnTarget;
	}

	public Integer getAwayFouls() {
		return awayFouls;
	}

	public void setAwayFouls(Integer awayFouls) {
		this.awayFouls = awayFouls;
	}

	public String getAwayGoalDetails() {
		return awayGoalDetails;
	}

	public void setAwayGoalDetails(String awayGoalDetails) {
		this.awayGoalDetails = awayGoalDetails;
	}

	public String getAwayLineupGoalkeeper() {
		return awayLineupGoalkeeper;
	}

	public void setAwayLineupGoalkeeper(String awayLineupGoalkeeper) {
		this.awayLineupGoalkeeper = awayLineupGoalkeeper;
	}

	public String getAwayLineupDefense() {
		return awayLineupDefense;
	}

	public void setAwayLineupDefense(String awayLineupDefense) {
		this.awayLineupDefense = awayLineupDefense;
	}

	public String getAwayLineupMidfield() {
		return awayLineupMidfield;
	}

	public void setAwayLineupMidfield(String awayLineupMidfield) {
		this.awayLineupMidfield = awayLineupMidfield;
	}

	public String getAwayLineupForward() {
		return awayLineupForward;
	}

	public void setAwayLineupForward(String awayLineupForward) {
		this.awayLineupForward = awayLineupForward;
	}

	public Integer getAwayYellowCards() {
		return awayYellowCards;
	}

	public void setAwayYellowCards(Integer awayYellowCards) {
		this.awayYellowCards = awayYellowCards;
	}

	public Integer getAwayRedCards() {
		return awayRedCards;
	}

	public void setAwayRedCards(Integer awayRedCards) {
		this.awayRedCards = awayRedCards;
	}

	public String getAwayTeamFormation() {
		return awayTeamFormation;
	}

	public void setAwayTeamFormation(String awayTeamFormation) {
		this.awayTeamFormation = awayTeamFormation;
	}

	public String getHomeTeamYellowCardDetails() {
		return homeTeamYellowCardDetails;
	}

	public void setHomeTeamYellowCardDetails(String homeTeamYellowCardDetails) {
		this.homeTeamYellowCardDetails = homeTeamYellowCardDetails;
	}

	public String getAwayTeamYellowCardDetails() {
		return awayTeamYellowCardDetails;
	}

	public void setAwayTeamYellowCardDetails(String awayTeamYellowCardDetails) {
		this.awayTeamYellowCardDetails = awayTeamYellowCardDetails;
	}

	public String getHomeTeamRedCardDetails() {
		return homeTeamRedCardDetails;
	}

	public void setHomeTeamRedCardDetails(String homeTeamRedCardDetails) {
		this.homeTeamRedCardDetails = homeTeamRedCardDetails;
	}

	public String getAwayTeamRedCardDetails() {
		return awayTeamRedCardDetails;
	}

	public void setAwayTeamRedCardDetails(String awayTeamRedCardDetails) {
		this.awayTeamRedCardDetails = awayTeamRedCardDetails;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	@Override
	public int compareTo(HistoricMatchDto historicMatchDto) {
		return this.getDate().compareTo(historicMatchDto.getDate());
	}

}
