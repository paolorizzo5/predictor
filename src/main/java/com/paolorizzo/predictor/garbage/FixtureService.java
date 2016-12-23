package com.paolorizzo.predictor.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.paolorizzo.predictor.business.FixtureBusiness;
import com.paolorizzo.predictor.business.TeamBusiness;
import com.paolorizzo.predictor.constant.FixtureStatus;
import com.paolorizzo.predictor.constants.BettypeConstants;
import com.paolorizzo.predictor.converters.FixtureConverter;
import com.paolorizzo.predictor.converters.TeamConverter;
import com.paolorizzo.predictor.enums.HomeAwayEnum;
import com.paolorizzo.predictor.hibernate.model.Fixture;
import com.paolorizzo.predictor.hibernate.model.Team;
import com.paolorizzo.predictor.hibernate.model.TeamStats;
import com.paolorizzo.predictor.services.request.CompetitionTimedFixturesRequest;
import com.paolorizzo.predictor.services.request.ViewOddsRequest;
import com.paolorizzo.predictor.services.response.dto.DataCollectorDto;
import com.paolorizzo.predictor.services.response.dto.FullStatisticDto;
import com.paolorizzo.predictor.services.response.dto.TeamDto;
import com.paolorizzo.predictor.services.response.dto.TeamStatsDto;
import com.paolorizzo.predictor.services.response.fixture.CompetitionTimedFixturesResponse;
import com.paolorizzo.predictor.services.response.fixture.ViewOddsResponse;
import com.paolorizzo.predictor.spring.AppContext;

@Path("/FixtureService")
@RequestScoped
public class FixtureService {

	static Logger logger = LogManager.getLogger(FixtureService.class.getName());

	TeamBusiness teamBusiness = AppContext.getApplicationContext().getBean(
			"teamBusinessBean", TeamBusiness.class);

	FixtureBusiness fixtureBusiness = AppContext.getApplicationContext()
			.getBean("fixtureBusinessBean", FixtureBusiness.class);

	@POST
	@Path("/view-odds/")
	@Consumes("application/json")
	public Response viewOdds(String input) {

		Gson gson = new Gson();
		ViewOddsRequest viewOddsRequest = gson.fromJson(input,
				ViewOddsRequest.class);
		ViewOddsResponse viewOddsResponse = new ViewOddsResponse();

		try {
			logger.debug("viewOdds request " + input);

			List<Team> output = teamBusiness.getTeams(
					viewOddsRequest.getHomeTeam(),
					viewOddsRequest.getAwayTeam(), viewOddsRequest.getId());
			
			List<TeamDto> teams = TeamConverter.convert(output);
			

			
			TeamStatsDto homeTeamStatsDto = new TeamStatsDto();
			TeamStatsDto awayTeamStatsDto = new TeamStatsDto();
			
			for (TeamStats teamStats : output.get(0).getTeamStats()) {
					pickProperty(homeTeamStatsDto,teamStats);
					
			}
			
			for (TeamStats teamStats : output.get(1).getTeamStats()) {
				pickProperty(awayTeamStatsDto,teamStats);
			}
			
			teams.get(0).setTeamStatsDto(homeTeamStatsDto);
			teams.get(1).setTeamStatsDto(awayTeamStatsDto);
			
			
			viewOddsResponse.setHomeTeam(teams.get(0));
			viewOddsResponse.setAwayTeam(teams.get(1));
			viewOddsResponse.setCompetitionId(output.get(0).getCompetition().getId());

			String ret = gson.toJson(viewOddsResponse).toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}

	private void pickProperty(TeamStatsDto homeTeamStatsDto, TeamStats teamStats) {
		if (HomeAwayEnum.HOME.getText().equals(teamStats.getAtHome())) {
			setDetailedProperty(homeTeamStatsDto.getHome(),teamStats);
		
		}else if (HomeAwayEnum.AWAY.getText().equals(
				teamStats.getAtHome())) {
			setDetailedProperty(homeTeamStatsDto.getAway(),teamStats);
		}else {
			setDetailedProperty(homeTeamStatsDto.getOverall(),teamStats);
		}
			
	}

	private void setDetailedProperty(FullStatisticDto fullStatisticDto, TeamStats teamStats) {
		if(BettypeConstants._1.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_1(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._X.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_x(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._2.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_2(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._AWAYGOAL.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_awayGoal(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._HOMEGOAL.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_homeGoal(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._GOAL.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_goal(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._NOGOAL.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_nogoal(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._HND_001_H2_1.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_handicapHome2_1(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._HND_002_H2_X.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_handicapHome2_x(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._HND_003_H2_2.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_handicapHome2_2(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._HND_004_H1_1.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_handicapHome1_1(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._HND_005_H1_X.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_handicapHome1_x(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._HND_006_H1_2.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_handicapHome1_2(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._HND_007_A1_1.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_handicapAway1_1(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._HND_008_A1_X.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_handicapAway1_x(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._HND_009_A1_2.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_handicapAway1_2(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._HND_010_A2_1.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_handicapAway2_1(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._HND_011_A2_X.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_handicapAway2_x(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._HND_012_A2_2.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_handicapAway2_2(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._OVER05.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_over05(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._OVER15.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_over15(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._OVER25.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_over25(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._OVER35.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_over35(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._OVER45.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_over45(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._OVER55.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_over55(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._UNDER05.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_under05(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._UNDER15.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_under15(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._UNDER25.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_under25(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._UNDER35.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_under35(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._UNDER45.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_under45(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}else if(BettypeConstants._UNDER55.equals(teamStats.getBettype().getId())){
			fullStatisticDto.set_under55(new DataCollectorDto(teamStats.getSuccessFulCases(), teamStats.getTotalCases()));
		}
	}
	
	@POST
	@Path("/get-daily-fixtures/")
	@Consumes("application/json")
	public Response getDailyFixtures(String input) {
		Gson gson = new Gson();
		CompetitionTimedFixturesRequest competitionTimedFixturesRequest = gson
				.fromJson(input, CompetitionTimedFixturesRequest.class);

		CompetitionTimedFixturesResponse competitionTimedFixturesResponse = new CompetitionTimedFixturesResponse();
		try {
			logger.debug("list request ");
			List<Fixture> fixtures = fixtureBusiness
					.findDaily();

			competitionTimedFixturesResponse.setFixtureDtos(FixtureConverter
					.convert(fixtures));

			String ret = gson.toJson(competitionTimedFixturesResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}

	@POST
	@Path("/get-competition-timed-fixtures/")
	@Consumes("application/json")
	public Response getCompetitionTimedFixtures(String input) {
		Gson gson = new Gson();
		CompetitionTimedFixturesRequest competitionTimedFixturesRequest = gson
				.fromJson(input, CompetitionTimedFixturesRequest.class);

		CompetitionTimedFixturesResponse competitionTimedFixturesResponse = new CompetitionTimedFixturesResponse();
		try {
			logger.debug("list request ");
			List<Fixture> fixtures = fixtureBusiness
					.findByStatusAndCompetition(FixtureStatus._TIMED,
							competitionTimedFixturesRequest.getId(),false,0);

			competitionTimedFixturesResponse.setFixtureDtos(FixtureConverter
					.convert(fixtures));

			String ret = gson.toJson(competitionTimedFixturesResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}
	
	@POST
	@Path("/get-competition-finished-fixtures/")
	@Consumes("application/json")
	public Response getCompetitionFinishedFixtures(String input) {
		Gson gson = new Gson();
		CompetitionTimedFixturesRequest competitionTimedFixturesRequest = gson
				.fromJson(input, CompetitionTimedFixturesRequest.class);

		CompetitionTimedFixturesResponse competitionTimedFixturesResponse = new CompetitionTimedFixturesResponse();
		try {
			logger.debug("list request ");
			List<Fixture> fixtures = fixtureBusiness
					.findByStatusAndCompetition(FixtureStatus._FINISHED,
							competitionTimedFixturesRequest.getId(),true,20);

			competitionTimedFixturesResponse.setFixtureDtos(FixtureConverter
					.convert(fixtures));

			String ret = gson.toJson(competitionTimedFixturesResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}

}
