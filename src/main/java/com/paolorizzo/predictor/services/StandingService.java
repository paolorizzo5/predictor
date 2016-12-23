package com.paolorizzo.predictor.services;

import java.util.Collections;
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
import com.paolorizzo.predictor.dto.StandingDto;
import com.paolorizzo.predictor.services.request.GetStandingByLeagueAndSeasonRequest;
import com.paolorizzo.predictor.services.response.GetStandingByLeagueAndSeasonResponse;
import com.paolorizzo.predictor.spring.AppContext;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Standing;
import com.paolorizzo.xmlsoccer.business.StandingBusiness;
import com.paolorizzo.xmlsoccer.data.converter.StandingDataConverter;

@Path("/StandingService")
@RequestScoped
public class StandingService {

	static Logger logger = LogManager
			.getLogger(StandingService.class.getName());

	StandingBusiness standingBusiness = AppContext.getApplicationContext()
			.getBean("standingBusinessBean", StandingBusiness.class);

	@POST
	@Path("/getStandingByLeagueAndSeason/")
	@Consumes("application/json")
	public Response getStandingByLeagueAndSeason(String input) {
		Gson gson = new Gson();
		GetStandingByLeagueAndSeasonRequest getStandingByLeagueAndSeasonRequest = gson
				.fromJson(input, GetStandingByLeagueAndSeasonRequest.class);

		GetStandingByLeagueAndSeasonResponse getStandingByLeagueAndSeasonResponse = new GetStandingByLeagueAndSeasonResponse();
		try {
			logger.debug("getStandingByLeagueAndSeason request");

			List<XmlSoccer_Standing> standings = standingBusiness
					.getStandingByLeagueAndSeason(
							getStandingByLeagueAndSeasonRequest.getLeague(),
							getStandingByLeagueAndSeasonRequest.getSeason());

			List<StandingDto> standingDtos = StandingDataConverter
					.convert(standings);

			Collections.sort(standingDtos);
			getStandingByLeagueAndSeasonResponse.setStandings(standingDtos);

			String ret = gson.toJson(getStandingByLeagueAndSeasonResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}
	//
	// @POST
	// @Path("/get-competition-finished-fixtures/")
	// @Consumes("application/json")
	// public Response getCompetitionFinishedFixtures(String input) {
	// Gson gson = new Gson();
	// CompetitionTimedFixturesRequest competitionTimedFixturesRequest = gson
	// .fromJson(input, CompetitionTimedFixturesRequest.class);
	//
	// CompetitionTimedFixturesResponse competitionTimedFixturesResponse = new
	// CompetitionTimedFixturesResponse();
	// try {
	// logger.debug("list request ");
	// List<Fixture> fixtures = fixtureBusiness
	// .findByStatusAndCompetition(FixtureStatus._FINISHED,
	// competitionTimedFixturesRequest.getId(), true, 20);
	//
	// competitionTimedFixturesResponse.setFixtureDtos(FixtureConverter
	// .convert(fixtures));
	//
	// String ret = gson.toJson(competitionTimedFixturesResponse)
	// .toString();
	// return Response.status(Status.OK).entity(ret).build();
	// } catch (Exception e) {
	// logger.error("list error occurred: ", e);
	// return Response.status(Status.OK).entity(null).build();
	// }
	//
	// }

}
