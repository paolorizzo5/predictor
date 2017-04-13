package com.paolorizzo.predictor.utils;

public class FixtureUtils {

	public static boolean homeTeamWins(Integer homeGoals, Integer awayGoals) {
		return homeGoals > awayGoals;
	}
	
	public static boolean isDraw(Integer homeGoals, Integer awayGoals) {
		return homeGoals == awayGoals;
	}
	
	public static boolean awayTeamWins(Integer homeGoals, Integer awayGoals) {
		return homeGoals < awayGoals;
	}
	

}
