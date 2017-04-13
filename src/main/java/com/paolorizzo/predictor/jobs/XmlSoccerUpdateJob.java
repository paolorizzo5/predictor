package com.paolorizzo.predictor.jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.github.pabloo99.xmlsoccer.api.dto.GetAllOddsResultDto;
import com.github.pabloo99.xmlsoccer.api.dto.GetLeagueStandingsResultDto;
import com.paolorizzo.predictor.business.JobConfigurationBusiness;
import com.paolorizzo.predictor.dto.LeagueSeasonDto;
import com.paolorizzo.predictor.hibernate.model.JobConfiguration;
import com.paolorizzo.predictor.spring.AppContext;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Fixture;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_HistoricMatch;
import com.paolorizzo.xmlsoccer.XmlSoccerClient;
import com.paolorizzo.xmlsoccer.business.FixtureBusiness;
import com.paolorizzo.xmlsoccer.business.HistoricMatchBusiness;
import com.paolorizzo.xmlsoccer.business.OddBusiness;
import com.paolorizzo.xmlsoccer.business.StandingBusiness;
import com.paolorizzo.xmlsoccer.business.TeamBusiness;
import com.paolorizzo.xmlsoccer.data.converter.OddDataConverter;
import com.paolorizzo.xmlsoccer.data.converter.StandingDataConverter;

public class XmlSoccerUpdateJob implements Job {

	private FixtureBusiness fixtureBusiness;

	private HistoricMatchBusiness historicMatchBusiness;

	private XmlSoccerClient xmlSoccerClient;

	private JobConfigurationBusiness jobConfigurationBusiness;

	private StandingBusiness standingBusiness;

	private OddBusiness oddBusiness;

	private TeamBusiness teamBusiness;

	Logger logger = LogManager.getLogger("root");

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		jobConfigurationBusiness = AppContext.getApplicationContext().getBean(
				"jobConfigurationBusinessBean", JobConfigurationBusiness.class);

		fixtureBusiness = AppContext.getApplicationContext().getBean(
				"fixtureBusinessBean", FixtureBusiness.class);

		historicMatchBusiness = AppContext.getApplicationContext().getBean(
				"historicMatchBusinessBean", HistoricMatchBusiness.class);

		standingBusiness = AppContext.getApplicationContext().getBean(
				"standingBusinessBean", StandingBusiness.class);

		oddBusiness = AppContext.getApplicationContext().getBean(
				"oddBusinessBean", OddBusiness.class);
		
		teamBusiness = AppContext.getApplicationContext().getBean(
				"teamBusinessBean", TeamBusiness.class);

		xmlSoccerClient = AppContext.getApplicationContext().getBean(
				"xmlSoccerClientBean", XmlSoccerClient.class);

		// 1) travaso da fixture a historic
		updateFromFixturesToHistoricMatches();
		updateOdds();

	}

	private void updateOdds() {
		List<XmlSoccer_Fixture> fixtures = fixtureBusiness
				.getNext7DaysFixtures();

		for (XmlSoccer_Fixture xmlSoccer_Fixture : fixtures) {

			// oddBusiness.deleteById("" + xmlSoccer_Fixture.getId());

			List<GetAllOddsResultDto> getAllOddsResultDtos = xmlSoccerClient
					.getAllOddsByFixtureMatchId(xmlSoccer_Fixture.getId());

			for (GetAllOddsResultDto getAllOddsResultDto : getAllOddsResultDtos) {
				try {
					oddBusiness.insert(OddDataConverter
							.convert(getAllOddsResultDto));
				} catch (Exception exception) {

				}
			}

		}

	}

	private void updateFromFixturesToHistoricMatches() {

		Date thisExecutionDate = new Date();
		JobConfiguration jobConfiguration = jobConfigurationBusiness
				.getByClass(this.getClass().getName());

		List<XmlSoccer_Fixture> fixtures = fixtureBusiness
				.getJustPlayedFixtures(jobConfiguration.getLastExecution(),
						thisExecutionDate);

		List<LeagueSeasonDto> leagueSeasonDtos = new ArrayList<LeagueSeasonDto>();

		for (XmlSoccer_Fixture xmlSoccer_Fixture : fixtures) {
			XmlSoccer_HistoricMatch historicMatch = xmlSoccerClient
					.getHistoricMatchById(xmlSoccer_Fixture.getId(),
							xmlSoccer_Fixture.getLeague().getId(),
							xmlSoccer_Fixture.getSeason());

			if (historicMatch != null) {
				try {
					historicMatchBusiness.insert(historicMatch);
					teamBusiness.updateTeamProgressionStats(historicMatch.getHomeTeam());
					teamBusiness.updateTeamProgressionStats(historicMatch.getAwayTeam());
					try {
						leagueSeasonDtos.add(new LeagueSeasonDto(
								xmlSoccer_Fixture.getLeague().getId() + "",
								xmlSoccer_Fixture.getSeason()));

					} catch (Exception exception) {
					}

				} catch (Exception exception) {
					logger.warn("warning inserting in history.");
				}

				// fixtureBusiness.delete(xmlSoccer_Fixture);
			}

		}

		for (LeagueSeasonDto leagueSeasonDto : leagueSeasonDtos) {
			updateStandings(leagueSeasonDto.getLeague(),
					leagueSeasonDto.getSeason());
		}

		jobConfiguration.setLastExecution(thisExecutionDate);
		jobConfigurationBusiness.update(jobConfiguration);
	}

	private void updateStandings(String league, String season) {
		standingBusiness.deleteByLeagueAndSeason(league, season);

		List<GetLeagueStandingsResultDto> getLeagueStandingsResultDtos = xmlSoccerClient
				.getLeagueStandingsBySeason(league, season);

		for (GetLeagueStandingsResultDto getLeagueStandingsResultDto : getLeagueStandingsResultDtos) {
			standingBusiness.insert(StandingDataConverter.convert(
					getLeagueStandingsResultDto, league, season));

		}
	}
}
