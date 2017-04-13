package com.paolorizzo.predictor.jobs;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.paolorizzo.predictor.business.AccountBusiness;
import com.paolorizzo.predictor.dto.FixturePreviewDto;
import com.paolorizzo.predictor.hibernate.model.Account;
import com.paolorizzo.predictor.spring.AppContext;
import com.paolorizzo.predictor.utils.FixtureUtils;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Fixture;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_HistoricMatch;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Team;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_TeamProgressionStats;
import com.paolorizzo.xmlsoccer.business.FixtureBusiness;
import com.paolorizzo.xmlsoccer.business.HistoricMatchBusiness;
import com.paolorizzo.xmlsoccer.business.TeamBusiness;

public class MatchPreviewJob implements Job{
	
	private FixtureBusiness fixtureBusiness;
	
	private HistoricMatchBusiness historicMatchBusiness;
	
	private TeamBusiness teamBusiness;
	

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
//		fixtureBusiness = AppContext.getApplicationContext().getBean(
//				"fixtureBusinessBean", FixtureBusiness.class);
//		
//		historicMatchBusiness = AppContext.getApplicationContext().getBean(
//				"historicMatchBusinessBean", HistoricMatchBusiness.class);
//		
		
		teamBusiness = AppContext.getApplicationContext().getBean(
				"teamBusinessBean", TeamBusiness.class);
		
		
		List<XmlSoccer_Fixture> dailyFixtures = fixtureBusiness.getDailyFixtures();
		for (XmlSoccer_Fixture xmlSoccer_Fixture : dailyFixtures) {
			
			//inizializzo le variabili che determinano da quanto tempo non succede un determinato evento

			teamBusiness.updateTeamProgressionStats(xmlSoccer_Fixture.getHomeTeam());
			teamBusiness.updateTeamProgressionStats(xmlSoccer_Fixture.getAwayTeam());
			
		}
				
	}



	

}
