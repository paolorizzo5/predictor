package com.paolorizzo.predictor.jobs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.paolorizzo.predictor.business.BettypeBusiness;
import com.paolorizzo.predictor.business.CompetitionBusiness;
import com.paolorizzo.predictor.business.FixtureBusiness;
import com.paolorizzo.predictor.business.TeamBusiness;
import com.paolorizzo.predictor.business.TeamStatsBusiness;
import com.paolorizzo.predictor.constants.BettypeConstants;
import com.paolorizzo.predictor.enums.HomeAwayEnum;
import com.paolorizzo.predictor.hibernate.model.Bettype;
import com.paolorizzo.predictor.hibernate.model.Competition;
import com.paolorizzo.predictor.hibernate.model.Fixture;
import com.paolorizzo.predictor.hibernate.model.Team;
import com.paolorizzo.predictor.hibernate.model.TeamStats;
import com.paolorizzo.predictor.spring.AppContext;

public class StatsEngineJob implements Job {

	@Autowired
	FixtureBusiness fixtureBusinessBean;

	@Autowired
	CompetitionBusiness competitionBusinessBean;

	@Autowired
	BettypeBusiness bettypeBusinessBean;

	@Autowired
	TeamBusiness teamBusinessBean;

	@Autowired
	TeamStatsBusiness teamStatsBusinessBean;

	Logger logger = LogManager.getLogger("root");

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		long millis = System.currentTimeMillis();
		logger.debug("running StatsEngineJob");

		fixtureBusinessBean = AppContext.getApplicationContext().getBean(
				"fixtureBusinessBean", FixtureBusiness.class);

		competitionBusinessBean = AppContext.getApplicationContext().getBean(
				"competitionBusinessBean", CompetitionBusiness.class);

		bettypeBusinessBean = AppContext.getApplicationContext().getBean(
				"bettypeBusinessBean", BettypeBusiness.class);

		teamBusinessBean = AppContext.getApplicationContext().getBean(
				"teamBusinessBean", TeamBusiness.class);

		teamStatsBusinessBean = AppContext.getApplicationContext().getBean(
				"teamStatsBusinessBean", TeamStatsBusiness.class);

		teamStatsBusinessBean.clear();

		List<Bettype> bettypes = bettypeBusinessBean.list();

		List<Competition> competitions = competitionBusinessBean.list();

		List<TeamStats> teamStats = new ArrayList<TeamStats>();
		
		if (teamStatsBusinessBean.isEmpty()){
			
	

		for (Competition competition : competitions) {
			logger.debug("iterate through competitions "
					+ competition.getCaption());

			for (Team team : competition.getTeams()) {

				logger.debug("iterate through teams " + team.getName());

				teamStatsBusinessBean.insertAll(getTeamStats(team, competition,
						bettypes, HomeAwayEnum.HOME));
				teamStatsBusinessBean.insertAll(getTeamStats(team, competition,
						bettypes, HomeAwayEnum.AWAY));
				teamStatsBusinessBean.insertAll(getTeamStats(team, competition,
						bettypes, HomeAwayEnum.ALL));

				// teamBusinessBean.update(team);
			}
		}

		logger.debug("finished execution of StatsEngineJob DURATION="
				+ (System.currentTimeMillis() - millis));
		}
	}

	private List<TeamStats> getTeamStats(Team team, Competition competition,
			List<Bettype> bettypes, HomeAwayEnum atHome) {

		List<TeamStats> teamStats = new ArrayList<TeamStats>();
		List<Fixture> fixtures = fixtureBusinessBean.getByTeam(team,
				competition, atHome);
		for (Bettype bettype : bettypes) {

			Integer totalCount = 0;
			Integer happenedCount = 0;

			for (Fixture fixture : fixtures) {
				totalCount++;
				boolean isHappened = isHappened(bettype, fixture, team);
				if (isHappened) {
					happenedCount++;
				}
			}
			TeamStats teamStat = new TeamStats();
			teamStat.setBettype(bettype);
			teamStat.setTeam(team);
			teamStat.setAtHome(atHome.getText());
			teamStat.setSuccessFulCases(happenedCount);
			teamStat.setTotalCases(totalCount);
			teamStats.add(teamStat);

		}
		return teamStats;
	}

	private boolean isHappened(Bettype bettype, Fixture fixture, Team team) {
		if(fixture.getHomeTeamCode().equals("98") && fixture.getAwayTeamCode().equals("115")){
			logger.debug("");
		}
		if (BettypeConstants._1.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals() > fixture
					.getFinalScore().getAwayGoals();

		} else if (BettypeConstants._X.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals() == fixture
					.getFinalScore().getAwayGoals();

		} else if (BettypeConstants._2.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals() < fixture
					.getFinalScore().getAwayGoals();
		} else if (BettypeConstants._AWAYGOAL.equals(bettype.getId())) {
			return fixture.getFinalScore().getAwayGoals() > 0;

		} else if (BettypeConstants._HOMEGOAL.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals() > 0;

		} else if (BettypeConstants._GOAL.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals() > 0
					&& fixture.getFinalScore().getAwayGoals() > 0;
		} else if (BettypeConstants._NOGOAL.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals() == 0
					|| fixture.getFinalScore().getAwayGoals() == 0;
		} else if (BettypeConstants._OVER05.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					+ fixture.getFinalScore().getAwayGoals() > 0;
		} else if (BettypeConstants._OVER15.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					+ fixture.getFinalScore().getAwayGoals() > 1;
		} else if (BettypeConstants._OVER25.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					+ fixture.getFinalScore().getAwayGoals() > 2;
		} else if (BettypeConstants._OVER35.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					+ fixture.getFinalScore().getAwayGoals() > 3;
		} else if (BettypeConstants._OVER45.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					+ fixture.getFinalScore().getAwayGoals() > 4;
		} else if (BettypeConstants._OVER55.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					+ fixture.getFinalScore().getAwayGoals() > 5;
		} else if (BettypeConstants._UNDER05.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					+ fixture.getFinalScore().getAwayGoals() < 1;
		} else if (BettypeConstants._UNDER15.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					+ fixture.getFinalScore().getAwayGoals() < 2;
		} else if (BettypeConstants._UNDER25.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					+ fixture.getFinalScore().getAwayGoals() < 3;
		} else if (BettypeConstants._UNDER35.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					+ fixture.getFinalScore().getAwayGoals() < 4;
		} else if (BettypeConstants._UNDER45.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					+ fixture.getFinalScore().getAwayGoals() < 5;
		} else if (BettypeConstants._UNDER55.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					+ fixture.getFinalScore().getAwayGoals() < 6;
		} 
		
		else if (BettypeConstants._HND_001_H2_1.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					- fixture.getFinalScore().getAwayGoals() > 2;
		}
		
		else if (BettypeConstants._HND_002_H2_X.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					- fixture.getFinalScore().getAwayGoals() == 2;
		}
		
		else if (BettypeConstants._HND_003_H2_2.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					- fixture.getFinalScore().getAwayGoals() < 2;
		}
		
		else if (BettypeConstants._HND_004_H1_1.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					- fixture.getFinalScore().getAwayGoals() > 1;
		}
		
		else if (BettypeConstants._HND_005_H1_X.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					- fixture.getFinalScore().getAwayGoals() == 1;
		}
		else if (BettypeConstants._HND_006_H1_2.equals(bettype.getId())) {
			return fixture.getFinalScore().getHomeGoals()
					- fixture.getFinalScore().getAwayGoals() < 1;
		}
		
		else if (BettypeConstants._HND_007_A1_1.equals(bettype.getId())) {
			return 
				fixture.getFinalScore().getAwayGoals()  - fixture.getFinalScore().getHomeGoals()< 1;
		}
		
		else if (BettypeConstants._HND_008_A1_X.equals(bettype.getId())) {
			return fixture.getFinalScore().getAwayGoals() - fixture.getFinalScore().getHomeGoals()
					== 1;
		}
		
		else if (BettypeConstants._HND_009_A1_2.equals(bettype.getId())) {
			return fixture.getFinalScore().getAwayGoals() - fixture.getFinalScore().getHomeGoals()
			> 1;
		}
		
		else if (BettypeConstants._HND_010_A2_1.equals(bettype.getId())) {
			return fixture.getFinalScore().getAwayGoals() - fixture.getFinalScore().getHomeGoals()
					 < 2;
		}
		
		else if (BettypeConstants._HND_011_A2_X.equals(bettype.getId())) {
			return fixture.getFinalScore().getAwayGoals() - fixture.getFinalScore().getHomeGoals() == 2;
		}
		else if (BettypeConstants._HND_012_A2_2.equals(bettype.getId())) {
			return fixture.getFinalScore().getAwayGoals()- fixture.getFinalScore().getHomeGoals()  > 2;
		}
		
		
		return false;
	}
}
