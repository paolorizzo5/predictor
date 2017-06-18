package com.paolorizzo.predictor.utils;

public class FixtureUtils {

	public boolean homeTeamWins(Integer homeGoals, Integer awayGoals) {
		return homeGoals > awayGoals;
	}
	
	public boolean isDraw(Integer homeGoals, Integer awayGoals) {
		return homeGoals == awayGoals;
	}
	
	public boolean awayTeamWins(Integer homeGoals, Integer awayGoals) {
		return homeGoals < awayGoals;
	}
	

}
