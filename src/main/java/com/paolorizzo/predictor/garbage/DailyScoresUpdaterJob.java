package com.paolorizzo.predictor.jobs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.paolorizzo.predictor.business.FinalScoreBusiness;
import com.paolorizzo.predictor.business.FixtureBusiness;
import com.paolorizzo.predictor.business.FootballDataClient;
import com.paolorizzo.predictor.client.model.v2.FixtureDetail_API;
import com.paolorizzo.predictor.constant.FixtureStatus;
import com.paolorizzo.predictor.hibernate.model.Bettype;
import com.paolorizzo.predictor.hibernate.model.FinalScore;
import com.paolorizzo.predictor.hibernate.model.Fixture;
import com.paolorizzo.predictor.spring.AppContext;

public class DailyScoresUpdaterJob implements Job {

	@Autowired
	FixtureBusiness fixtureBusinessBean;

	@Autowired
	FootballDataClient footballDataClient;

	@Autowired
	FinalScoreBusiness finalScoreBusiness;

	Logger logger = LogManager.getLogger("root");

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		fixtureBusinessBean = AppContext.getApplicationContext().getBean(
				"fixtureBusinessBean", FixtureBusiness.class);

		footballDataClient = AppContext.getApplicationContext().getBean(
				"footballDataClientBean", FootballDataClient.class);

		finalScoreBusiness = AppContext.getApplicationContext().getBean(
				"finalScoreBusinessBean", FinalScoreBusiness.class);

		Calendar today = new GregorianCalendar();
		today.set(Calendar.HOUR, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.add(Calendar.DAY_OF_MONTH, -1);

		Calendar tomorrow = new GregorianCalendar();
		tomorrow.set(Calendar.HOUR, 0);
		tomorrow.set(Calendar.MINUTE, 0);
		tomorrow.set(Calendar.SECOND, 0);
		// tomorrow.add(Calendar.DAY_OF_MONTH, 0);

		List<Fixture> fixtures = fixtureBusinessBean.findByStatusAndDate(
				FixtureStatus._TIMED, today.getTimeInMillis(),
				tomorrow.getTimeInMillis());

		for (Fixture fixture : fixtures) {
			FixtureDetail_API fixture_API = footballDataClient
					.getFixture(fixture.getUrl());

			if (FixtureStatus._FINISHED.equals(fixture_API.getFixture()
					.getStatus()) ||
					FixtureStatus._TIMED.equals(fixture_API.getFixture()
							.getStatus())) {

				FinalScore finalScore = finalScoreBusiness.getByScore(
						Integer.parseInt(fixture_API.getFixture().getResult()
								.getGoalsHomeTeam()),
						Integer.parseInt(fixture_API.getFixture().getResult()
								.getGoalsAwayTeam()));
				fixture.setFinalScore(finalScore);
				List<Bettype> bettypes = new ArrayList<Bettype>();
				bettypes.addAll(finalScore.getBettypes());
				fixture.setBettypes(bettypes);
				fixture.setStatus(fixture_API.getFixture().getStatus());

				fixtureBusinessBean.update(fixture);

			}

		}

	}
}
