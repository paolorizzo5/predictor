package com.paolorizzo.predictor.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jboss.resteasy.spi.BadRequestException;

import com.paolorizzo.predictor.hibernate.model.Bettype;
import com.paolorizzo.predictor.hibernate.model.BettypeGroup;
import com.paolorizzo.predictor.hibernate.model.FinalScore;

public class BettypeUtils {

	public static List<Bettype> createDefault(List<FinalScore> finalScores) {
		List<Bettype> bettypes = new ArrayList<Bettype>();

		// CREAZIONE 1X2
		bettypes.addAll(create1x2(finalScores));
		bettypes.addAll(createGoals(finalScores));
		bettypes.addAll(createUnderOver(finalScores));
		bettypes.addAll(createHandicap(finalScores));
		

		return bettypes;
	}

	private static Collection<? extends Bettype> createHandicap(
			List<FinalScore> finalScores) {
		BettypeGroup bettypeHandicap = new BettypeGroup("BETTYPEGROUP.HANDICAP.ID", "BETTYPEGROUP.HANDICAP.DESCRIPTION", null);
		
		Bettype bettypeHandicapHome2_1 = new Bettype("HND.001.H2_1", "FULLTIME.HND.001.H2_1",bettypeHandicap);
		Bettype bettypeHandicapHome2_X = new Bettype("HND.002.H2_X", "FULLTIME.HND.002.H2_X",bettypeHandicap);
		Bettype bettypeHandicapHome2_2 = new Bettype("HND.003.H2_2", "FULLTIME.HND.003.H2_2",bettypeHandicap);
		Bettype bettypeHandicapHome1_1 = new Bettype("HND.004.H1_1", "FULLTIME.HND.004.H1_1",bettypeHandicap);
		Bettype bettypeHandicapHome1_X = new Bettype("HND.005.H1_X", "FULLTIME.HND.005.H1_X",bettypeHandicap);
		Bettype bettypeHandicapHome1_2 = new Bettype("HND.006.H1_2", "FULLTIME.HND.006.H1_2",bettypeHandicap);
		Bettype bettypeHandicapAway1_1 = new Bettype("HND.007.A1_1", "FULLTIME.HND.007.A1_1",bettypeHandicap);
		Bettype bettypeHandicapAway1_X = new Bettype("HND.008.A1_X", "FULLTIME.HND.008.A1_X",bettypeHandicap);
		Bettype bettypeHandicapAway1_2 = new Bettype("HND.009.A1_2", "FULLTIME.HND.009.A1_2",bettypeHandicap);
		Bettype bettypeHandicapAway2_1 = new Bettype("HND.010.A2_1", "FULLTIME.HND.010.A2_1",bettypeHandicap);
		Bettype bettypeHandicapAway2_X = new Bettype("HND.011.A2_X", "FULLTIME.HND.011.A2_X",bettypeHandicap);
		Bettype bettypeHandicapAway2_2 = new Bettype("HND.012.A2_2", "FULLTIME.HND.012.A2_2",bettypeHandicap);
		
		List<FinalScore> finalScoresHandicapHome2_1 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresHandicapHome2_X = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresHandicapHome2_2 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresHandicapHome1_1 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresHandicapHome1_X = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresHandicapHome1_2 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresHandicapAway1_1 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresHandicapAway1_X = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresHandicapAway1_2 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresHandicapAway2_1 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresHandicapAway2_X = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresHandicapAway2_2 = new ArrayList<FinalScore>();
		
		
		
		
		

		for (FinalScore finalScore : finalScores) {
			switch (finalScore.getHomeGoals() - finalScore.getAwayGoals()) {
			case -10:
			case -9:
			case -8:
			case -7:
			case -6:
			case -5:
			case -4:
			case -3:
				finalScoresHandicapHome2_2.add(finalScore);
				finalScoresHandicapHome1_2.add(finalScore);
				finalScoresHandicapAway1_2.add(finalScore);
				finalScoresHandicapAway2_2.add(finalScore);
				break;
			case -2:
				finalScoresHandicapHome2_2.add(finalScore);
				finalScoresHandicapHome1_2.add(finalScore);
				finalScoresHandicapAway1_2.add(finalScore);
				finalScoresHandicapAway2_X.add(finalScore);
				break;
			case -1:
				finalScoresHandicapHome2_2.add(finalScore);
				finalScoresHandicapHome1_2.add(finalScore);
				finalScoresHandicapAway1_X.add(finalScore);
				finalScoresHandicapAway2_1.add(finalScore);
				break;
			case 0:
				finalScoresHandicapHome2_2.add(finalScore);
				finalScoresHandicapHome1_2.add(finalScore);
				finalScoresHandicapAway1_1.add(finalScore);
				finalScoresHandicapAway2_1.add(finalScore);
				break;
			case 1:
				finalScoresHandicapHome2_2.add(finalScore);
				finalScoresHandicapHome1_X.add(finalScore);
				finalScoresHandicapAway1_1.add(finalScore);
				finalScoresHandicapAway2_1.add(finalScore);
				break;
			case 2:
				finalScoresHandicapHome2_X.add(finalScore);
				finalScoresHandicapHome1_1.add(finalScore);
				finalScoresHandicapAway1_1.add(finalScore);
				finalScoresHandicapAway2_1.add(finalScore);
				break;
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				finalScoresHandicapHome2_1.add(finalScore);
				finalScoresHandicapHome1_1.add(finalScore);
				finalScoresHandicapAway1_1.add(finalScore);
				finalScoresHandicapAway2_1.add(finalScore);
				break;
			default:
				break;
			}
		}

		bettypeHandicapHome2_1.setFinalScores(finalScoresHandicapHome2_1);
		bettypeHandicapHome2_X.setFinalScores(finalScoresHandicapHome2_X);
		bettypeHandicapHome2_2.setFinalScores(finalScoresHandicapHome2_2);
		bettypeHandicapHome1_1.setFinalScores(finalScoresHandicapHome1_1);
		bettypeHandicapHome1_X.setFinalScores(finalScoresHandicapHome1_X);
		bettypeHandicapHome1_2.setFinalScores(finalScoresHandicapHome1_2);
		bettypeHandicapAway1_1.setFinalScores(finalScoresHandicapAway1_1);
		bettypeHandicapAway1_X.setFinalScores(finalScoresHandicapAway1_X);
		bettypeHandicapAway1_2.setFinalScores(finalScoresHandicapAway1_2);
		bettypeHandicapAway2_1.setFinalScores(finalScoresHandicapAway2_1);
		bettypeHandicapAway2_X.setFinalScores(finalScoresHandicapAway2_X);
		bettypeHandicapAway2_2.setFinalScores(finalScoresHandicapAway2_2);

		
		return Arrays.asList(bettypeHandicapHome2_1,bettypeHandicapHome2_X,bettypeHandicapHome2_2,
				bettypeHandicapHome1_1,bettypeHandicapHome1_X,bettypeHandicapHome1_2,bettypeHandicapAway1_1,
				bettypeHandicapAway1_X,bettypeHandicapAway1_2,bettypeHandicapAway2_1,bettypeHandicapAway2_X,bettypeHandicapAway2_2);
	}

	private static List<Bettype> createGoals(List<FinalScore> finalScores) {
		BettypeGroup bettypeGroup = new BettypeGroup("BETTYPEGROUP.GOALS.ID", "BETTYPEGROUP.GOALS.DESCRIPTION", null);
		
		Bettype bettypeGoal = new Bettype("GNG.GOAL", "FULLTIME.GNG.BOTH_TEAM_SCORES",bettypeGroup);
		Bettype bettypeNoGoal = new Bettype("GNG.NOGOAL",
				"FULLTIME.GNG.NO_BOTH_TEAM_SCORES",bettypeGroup);

		Bettype bettypeHomeTeamScores = new Bettype("HA.HOMEGOAL",
				"FULLTIME.HA.HOMEGOAL",bettypeGroup);
		Bettype bettypeAwayTeamScores = new Bettype("HA.AWAYGOAL",
				"FULLTIME.HA.AWAYGOAL",bettypeGroup);

		List<FinalScore> finalScoresGoal = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresNoGoal = new ArrayList<FinalScore>();

		List<FinalScore> finalScoresHomeTeamScores = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresAwayTeamScores = new ArrayList<FinalScore>();

		for (FinalScore finalScore : finalScores) {
			if (finalScore.getHomeGoals() > 0 && finalScore.getAwayGoals() > 0) {
				finalScoresGoal.add(finalScore);
			} else {
				finalScoresNoGoal.add(finalScore);
			}

			if (finalScore.getHomeGoals() > 0) {
				finalScoresHomeTeamScores.add(finalScore);
			}
			if (finalScore.getAwayGoals() > 0) {
				finalScoresAwayTeamScores.add(finalScore);
			}
		}

		bettypeGoal.setFinalScores(finalScoresGoal);
		bettypeNoGoal.setFinalScores(finalScoresNoGoal);

		bettypeHomeTeamScores.setFinalScores(finalScoresHomeTeamScores);
		bettypeAwayTeamScores.setFinalScores(finalScoresAwayTeamScores);

		return Arrays.asList(bettypeGoal, bettypeNoGoal,
				bettypeHomeTeamScores, bettypeAwayTeamScores);

	}

	private static List<Bettype> create1x2(List<FinalScore> finalScores) {
		BettypeGroup bettypeGroup = new BettypeGroup("BETTYPEGROUP.1X2.ID", "BETTYPEGROUP.1X2.DESCRIPTION", null);
		Bettype bettype1 = new Bettype("001_1", "FULLTIME.1X2.001.HOME_TEAM_WINS",bettypeGroup);
		Bettype bettypex = new Bettype("002_X", "FULLTIME.1X2.002.DRAW",bettypeGroup);
		Bettype bettype2 = new Bettype("003_2", "FULLTIME.1X2.003.AWAY_TEAM_WINS",bettypeGroup);

		List<FinalScore> finalScores1 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresx = new ArrayList<FinalScore>();
		List<FinalScore> finalScores2 = new ArrayList<FinalScore>();

		for (FinalScore finalScore : finalScores) {
			if (finalScore.getHomeGoals() > finalScore.getAwayGoals()) {
				finalScores1.add(finalScore);
			} else if (finalScore.getHomeGoals() == finalScore.getAwayGoals()) {
				finalScoresx.add(finalScore);
			} else {
				finalScores2.add(finalScore);
			}
		}

		bettype1.setFinalScores(finalScores1);
		bettypex.setFinalScores(finalScoresx);
		bettype2.setFinalScores(finalScores2);
		return Arrays.asList(bettype1, bettype2, bettypex);
	}

	public static List<BettypeGroup> createDefaultGroups(List<FinalScore> finalScores) {
		List<BettypeGroup> bettypeGroups = new ArrayList<BettypeGroup>();
		bettypeGroups.add(new BettypeGroup("BETTYPEGROUP.1X2.ID", "BETTYPEGROUP.1X2.DESCRIPTION", null));
		bettypeGroups.add(new BettypeGroup("BETTYPEGROUP.GOALS.ID", "BETTYPEGROUP.GOALS.DESCRIPTION", null));
		bettypeGroups.add(new BettypeGroup("BETTYPEGROUP.UNDEROVER.ID", "BETTYPEGROUP.UNDEROVER.DESCRIPTION", null));
		bettypeGroups.add(new BettypeGroup("BETTYPEGROUP.HANDICAP.ID", "BETTYPEGROUP.HANDICAP.DESCRIPTION", null));
		return bettypeGroups;
	}

	private static List<Bettype> createUnderOver(List<FinalScore> finalScores) {
		BettypeGroup bettypeGroup = new BettypeGroup("BETTYPEGROUP.UNDEROVER.ID", "BETTYPEGROUP.UNDEROVER.DESCRIPTION", null);
		
		Bettype bettypeOver05 = new Bettype("OVER05", "FULLTIME.OVER_05",bettypeGroup);
		Bettype bettypeOver15 = new Bettype("OVER15", "FULLTIME.OVER_15",bettypeGroup);
		Bettype bettypeOver25 = new Bettype("OVER25", "FULLTIME.OVER_25",bettypeGroup);
		Bettype bettypeOver35 = new Bettype("OVER35", "FULLTIME.OVER_35",bettypeGroup);
		Bettype bettypeOver45 = new Bettype("OVER45", "FULLTIME.OVER_45",bettypeGroup);
		Bettype bettypeOver55 = new Bettype("OVER55", "FULLTIME.OVER_55",bettypeGroup);

		Bettype bettypeUnder05 = new Bettype("UNDER05", "FULLTIME.UNDER_05",bettypeGroup);
		Bettype bettypeUnder15 = new Bettype("UNDER15", "FULLTIME.UNDER_15",bettypeGroup);
		Bettype bettypeUnder25 = new Bettype("UNDER25", "FULLTIME.UNDER_25",bettypeGroup);
		Bettype bettypeUnder35 = new Bettype("UNDER35", "FULLTIME.UNDER_35",bettypeGroup);
		Bettype bettypeUnder45 = new Bettype("UNDER45", "FULLTIME.UNDER_45",bettypeGroup);
		Bettype bettypeUnder55 = new Bettype("UNDER55", "FULLTIME.UNDER_55",bettypeGroup);
		
		List<FinalScore> finalScoresOver05 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresOver15 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresOver25 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresOver35 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresOver45 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresOver55 = new ArrayList<FinalScore>();

		List<FinalScore> finalScoresUnder05 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresUnder15 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresUnder25 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresUnder35 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresUnder45 = new ArrayList<FinalScore>();
		List<FinalScore> finalScoresUnder55 = new ArrayList<FinalScore>();
		
		for (FinalScore finalScore : finalScores) {
			switch (finalScore.getHomeGoals() + finalScore.getAwayGoals()) {
			case 0:
				finalScoresUnder05.add(finalScore);
				finalScoresUnder15.add(finalScore);
				finalScoresUnder25.add(finalScore);
				finalScoresUnder35.add(finalScore);
				finalScoresUnder45.add(finalScore);
				finalScoresUnder55.add(finalScore);
				break;
			case 1:
				finalScoresOver05.add(finalScore);
				finalScoresUnder15.add(finalScore);
				finalScoresUnder25.add(finalScore);
				finalScoresUnder35.add(finalScore);
				finalScoresUnder45.add(finalScore);
				finalScoresUnder55.add(finalScore);
				break;
			case 2:
				finalScoresOver05.add(finalScore);
				finalScoresOver15.add(finalScore);
				finalScoresUnder25.add(finalScore);
				finalScoresUnder35.add(finalScore);
				finalScoresUnder45.add(finalScore);
				finalScoresUnder55.add(finalScore);
				break;
			case 3:
				finalScoresOver05.add(finalScore);
				finalScoresOver15.add(finalScore);
				finalScoresOver25.add(finalScore);
				finalScoresUnder35.add(finalScore);
				finalScoresUnder45.add(finalScore);
				finalScoresUnder55.add(finalScore);
				break;
			case 4:
				finalScoresOver05.add(finalScore);
				finalScoresOver15.add(finalScore);
				finalScoresOver25.add(finalScore);
				finalScoresOver35.add(finalScore);
				finalScoresUnder45.add(finalScore);
				finalScoresUnder55.add(finalScore);
				break;
			case 5:
				finalScoresOver05.add(finalScore);
				finalScoresOver15.add(finalScore);
				finalScoresOver25.add(finalScore);
				finalScoresOver35.add(finalScore);
				finalScoresOver45.add(finalScore);
				finalScoresUnder55.add(finalScore);
				break;
			default:
				finalScoresOver05.add(finalScore);
				finalScoresOver15.add(finalScore);
				finalScoresOver25.add(finalScore);
				finalScoresOver35.add(finalScore);
				finalScoresOver45.add(finalScore);
				finalScoresOver55.add(finalScore);

				break;
			}
		}
		
		bettypeOver05.setFinalScores(finalScoresOver05);
		bettypeOver15.setFinalScores(finalScoresOver15);
		bettypeOver25.setFinalScores(finalScoresOver25);
		bettypeOver35.setFinalScores(finalScoresOver35);
		bettypeOver45.setFinalScores(finalScoresOver45);
		bettypeOver55.setFinalScores(finalScoresOver55);

		bettypeUnder05.setFinalScores(finalScoresUnder05);
		bettypeUnder15.setFinalScores(finalScoresUnder15);
		bettypeUnder25.setFinalScores(finalScoresUnder25);
		bettypeUnder35.setFinalScores(finalScoresUnder35);
		bettypeUnder45.setFinalScores(finalScoresUnder45);
		bettypeUnder55.setFinalScores(finalScoresUnder55);
		
		return Arrays.asList(bettypeOver05,
				bettypeOver15, bettypeOver25, bettypeOver35, bettypeOver45,
				bettypeOver55, bettypeUnder05, bettypeUnder15, bettypeUnder25,
				bettypeUnder35, bettypeUnder45, bettypeUnder55);
	}
}
