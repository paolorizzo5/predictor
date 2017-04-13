package com.paolorizzo.predictor.dto;

import java.util.ArrayList;
import java.util.List;

public class FixturePreviewDto {

	private String homeTeamName;
	private String awayTeamName;

	private Integer gamesPlayed;

	private Integer homeWins;
	private Integer draws;
	private Integer awayWins;

	private Integer over05;
	private Integer over15;
	private Integer over25;
	private Integer over35;
	private Integer over45;
	private Integer over55;

	private Integer under05;
	private Integer under15;
	private Integer under25;
	private Integer under35;
	private Integer under45;
	private Integer under55;

	private Integer goal;
	private Integer noGoal;
	private Integer homeGoal;
	private Integer awayGoal;

	private Integer handicapHome2_1;
	private Integer handicapHome2_X;
	private Integer handicapHome2_2;

	private Integer handicapHome1_1;
	private Integer handicapHome1_X;
	private Integer handicapHome1_2;

	private Integer handicapAway2_1;
	private Integer handicapAway2_X;
	private Integer handicapAway2_2;

	private Integer handicapAway1_1;
	private Integer handicapAway1_X;
	private Integer handicapAway1_2;

	private List<HistoricMatchDto> previousMatches;
	
	

	public FixturePreviewDto() {
		this.gamesPlayed = 0;

		this.draws = 0;
		this.homeWins = 0;
		this.awayWins = 0;

		this.under05 = 0;
		this.under15 = 0;
		this.under25 = 0;
		this.under35 = 0;
		this.under45 = 0;
		this.under55 = 0;

		this.over05 = 0;
		this.over15 = 0;
		this.over25 = 0;
		this.over35 = 0;
		this.over45 = 0;
		this.over55 = 0;

		this.goal = 0;
		this.noGoal = 0;
		this.homeGoal = 0;
		this.awayGoal = 0;

		this.handicapAway1_1 = 0;
		this.handicapAway1_2 = 0;
		this.handicapAway1_X = 0;
		this.handicapAway2_1 = 0;
		this.handicapAway2_2 = 0;
		this.handicapAway2_X = 0;
		this.handicapHome1_1 = 0;
		this.handicapHome1_2 = 0;
		this.handicapHome1_X = 0;
		this.handicapHome2_1 = 0;
		this.handicapHome2_2 = 0;
		this.handicapHome2_X = 0;

		setPreviousMatches(new ArrayList<HistoricMatchDto>());
	}

	public Integer getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(Integer gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public Integer getHomeWins() {
		return homeWins;
	}

	public void setHomeWins(Integer homeWins) {
		this.homeWins = homeWins;
	}

	public Integer getDraws() {
		return draws;
	}

	public void setDraws(Integer draws) {
		this.draws = draws;
	}

	public Integer getAwayWins() {
		return awayWins;
	}

	public void setAwayWins(Integer awayWins) {
		this.awayWins = awayWins;
	}

	public Integer getOver05() {
		return over05;
	}

	public void setOver05(Integer over05) {
		this.over05 = over05;
	}

	public Integer getOver15() {
		return over15;
	}

	public void setOver15(Integer over15) {
		this.over15 = over15;
	}

	public Integer getOver25() {
		return over25;
	}

	public void setOver25(Integer over25) {
		this.over25 = over25;
	}

	public Integer getOver35() {
		return over35;
	}

	public void setOver35(Integer over35) {
		this.over35 = over35;
	}

	public Integer getOver45() {
		return over45;
	}

	public void setOver45(Integer over45) {
		this.over45 = over45;
	}

	public Integer getOver55() {
		return over55;
	}

	public void setOver55(Integer over55) {
		this.over55 = over55;
	}

	public Integer getUnder05() {
		return under05;
	}

	public void setUnder05(Integer under05) {
		this.under05 = under05;
	}

	public Integer getUnder15() {
		return under15;
	}

	public void setUnder15(Integer under15) {
		this.under15 = under15;
	}

	public Integer getUnder25() {
		return under25;
	}

	public void setUnder25(Integer under25) {
		this.under25 = under25;
	}

	public Integer getUnder35() {
		return under35;
	}

	public void setUnder35(Integer under35) {
		this.under35 = under35;
	}

	public Integer getUnder45() {
		return under45;
	}

	public void setUnder45(Integer under45) {
		this.under45 = under45;
	}

	public Integer getUnder55() {
		return under55;
	}

	public void setUnder55(Integer under55) {
		this.under55 = under55;
	}

	public Integer getGoal() {
		return goal;
	}

	public void setGoal(Integer goal) {
		this.goal = goal;
	}

	public Integer getNoGoal() {
		return noGoal;
	}

	public void setNoGoal(Integer noGoal) {
		this.noGoal = noGoal;
	}

	public Integer getHomeGoal() {
		return homeGoal;
	}

	public void setHomeGoal(Integer homeGoal) {
		this.homeGoal = homeGoal;
	}

	public Integer getAwayGoal() {
		return awayGoal;
	}

	public void setAwayGoal(Integer awayGoal) {
		this.awayGoal = awayGoal;
	}

	public List<HistoricMatchDto> getPreviousMatches() {
		return previousMatches;
	}

	public void setPreviousMatches(List<HistoricMatchDto> previousMatches) {
		this.previousMatches = previousMatches;
	}

	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}

	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	public Integer getHandicapHome2_1() {
		return handicapHome2_1;
	}

	public void setHandicapHome2_1(Integer handicapHome2_1) {
		this.handicapHome2_1 = handicapHome2_1;
	}

	public Integer getHandicapHome2_X() {
		return handicapHome2_X;
	}

	public void setHandicapHome2_X(Integer handicapHome2_X) {
		this.handicapHome2_X = handicapHome2_X;
	}

	public Integer getHandicapHome2_2() {
		return handicapHome2_2;
	}

	public void setHandicapHome2_2(Integer handicapHome2_2) {
		this.handicapHome2_2 = handicapHome2_2;
	}

	public Integer getHandicapHome1_1() {
		return handicapHome1_1;
	}

	public void setHandicapHome1_1(Integer handicapHome1_1) {
		this.handicapHome1_1 = handicapHome1_1;
	}

	public Integer getHandicapHome1_X() {
		return handicapHome1_X;
	}

	public void setHandicapHome1_X(Integer handicapHome1_X) {
		this.handicapHome1_X = handicapHome1_X;
	}

	public Integer getHandicapHome1_2() {
		return handicapHome1_2;
	}

	public void setHandicapHome1_2(Integer handicapHome1_2) {
		this.handicapHome1_2 = handicapHome1_2;
	}

	public Integer getHandicapAway2_1() {
		return handicapAway2_1;
	}

	public void setHandicapAway2_1(Integer handicapAway2_1) {
		this.handicapAway2_1 = handicapAway2_1;
	}

	public Integer getHandicapAway2_X() {
		return handicapAway2_X;
	}

	public void setHandicapAway2_X(Integer handicapAway2_X) {
		this.handicapAway2_X = handicapAway2_X;
	}

	public Integer getHandicapAway2_2() {
		return handicapAway2_2;
	}

	public void setHandicapAway2_2(Integer handicapAway2_2) {
		this.handicapAway2_2 = handicapAway2_2;
	}

	public Integer getHandicapAway1_1() {
		return handicapAway1_1;
	}

	public void setHandicapAway1_1(Integer handicapAway1_1) {
		this.handicapAway1_1 = handicapAway1_1;
	}

	public Integer getHandicapAway1_X() {
		return handicapAway1_X;
	}

	public void setHandicapAway1_X(Integer handicapAway1_X) {
		this.handicapAway1_X = handicapAway1_X;
	}

	public Integer getHandicapAway1_2() {
		return handicapAway1_2;
	}

	public void setHandicapAway1_2(Integer handicapAway1_2) {
		this.handicapAway1_2 = handicapAway1_2;
	}

}
