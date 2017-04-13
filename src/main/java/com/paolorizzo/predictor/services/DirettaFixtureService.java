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

import com.github.pabloo99.xmlsoccer.api.dto.GetLiveScoreResultDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paolorizzo.predictor.business.DirettaFixtureBusiness;
import com.paolorizzo.predictor.dto.FixtureDto;
import com.paolorizzo.predictor.hibernate.model.DirettaFixture;
import com.paolorizzo.predictor.hibernate.model.Masaniello;
import com.paolorizzo.predictor.services.request.CreateMasanielloRequest;
import com.paolorizzo.predictor.services.request.GetDailyFixturesRequest;
import com.paolorizzo.predictor.services.request.GetDirettaCompetitionsRequest;
import com.paolorizzo.predictor.services.request.GetDirettaFixturesRequest;
import com.paolorizzo.predictor.services.request.GetFixturesByLeagueAndSeasonRequest;
import com.paolorizzo.predictor.services.request.GetLivescoresRequest;
import com.paolorizzo.predictor.services.response.CreateMasanielloResponse;
import com.paolorizzo.predictor.services.response.GetDailyFixturesResponse;
import com.paolorizzo.predictor.services.response.GetDirettaCompetitionsResponse;
import com.paolorizzo.predictor.services.response.GetDirettaFixturesResponse;
import com.paolorizzo.predictor.services.response.GetFixturesByLeagueAndSeasonResponse;
import com.paolorizzo.predictor.services.response.GetLivescoresResponse;
import com.paolorizzo.predictor.spring.AppContext;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Fixture;
import com.paolorizzo.xmlsoccer.business.FixtureBusiness;
import com.paolorizzo.xmlsoccer.data.converter.FixtureDataConverter;
import com.paolorizzo.xmlsoccer.data.converter.MasanielloDataConverter;

@Path("/DirettaFixtureService")
@RequestScoped
public class DirettaFixtureService {

	static Logger logger = LogManager.getLogger("root");

	DirettaFixtureBusiness direttaFixtureBusiness = AppContext.getApplicationContext()
			.getBean("direttaFixtureBusinessBean", DirettaFixtureBusiness.class);

	@POST
	@Path("/getCompetitions/")
	@Consumes("application/json")
	public Response getCompetitions(String input) {
		Gson gson = new Gson();
		GetDirettaCompetitionsRequest getDirettaCompetitionsRequest  = gson
				.fromJson(input, GetDirettaCompetitionsRequest.class);

		GetDirettaCompetitionsResponse getDirettaCompetitionsResponse = new GetDirettaCompetitionsResponse();
		try {
			logger.debug("getDirettaCompetitions request");

			List<String> competitions = direttaFixtureBusiness.getCompetitions();
			getDirettaCompetitionsResponse.setCompetitions(competitions);
			

			String ret = gson.toJson(getDirettaCompetitionsResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}
	
	@POST
	@Path("/getDirettaFixtures/")
	@Consumes("application/json")
	public Response getDirettaFixtures(String input) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		GetDirettaFixturesRequest getDirettaFixturesRequest  = gson
				.fromJson(input, GetDirettaFixturesRequest.class);

		GetDirettaFixturesResponse getDirettaFixturesResponse = new GetDirettaFixturesResponse();
		try {
			logger.debug("getDirettaCompetitions request");

			List<DirettaFixture> direttaFixtures = direttaFixtureBusiness.getDirettaFixtures(
					getDirettaFixturesRequest.getCompetition(),
					getDirettaFixturesRequest.getHomeTeam(),
					getDirettaFixturesRequest.getAwayTeam(),
					getDirettaFixturesRequest.getQuota1From(),
					getDirettaFixturesRequest.getQuota1To(),
					getDirettaFixturesRequest.getQuotaXFrom(),
					getDirettaFixturesRequest.getQuotaXTo(),
					getDirettaFixturesRequest.getQuota2From(),
					getDirettaFixturesRequest.getQuota2To());
			
			getDirettaFixturesResponse = direttaFixtureBusiness.getStats(direttaFixtures);
			
			String ret = gson.toJson(getDirettaFixturesResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}
	
	@POST
	@Path("/createMasaniello/")
	@Consumes("application/json")
	public Response createMasaniello(String input) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		CreateMasanielloRequest createMasanielloRequest  = gson
				.fromJson(input, CreateMasanielloRequest.class);

		CreateMasanielloResponse createMasanielloResponse = new CreateMasanielloResponse();
		try {
			logger.debug("getDirettaCompetitions request");

			Masaniello masaniello = direttaFixtureBusiness.createMasaniello(
					createMasanielloRequest.getCompetition(),
					createMasanielloRequest.getHomeTeam(),
					createMasanielloRequest.getAwayTeam(),
					createMasanielloRequest.getQuota1From(),
					createMasanielloRequest.getQuota1To(),
					createMasanielloRequest.getQuotaXFrom(),
					createMasanielloRequest.getQuotaXTo(),
					createMasanielloRequest.getQuota2From(),
					createMasanielloRequest.getQuota2To(),
					createMasanielloRequest.getMasanielloUserEmail(),
					createMasanielloRequest.getMasanielloAmount(),
					createMasanielloRequest.getMasanielloEventToWin(),
					createMasanielloRequest.getMasanielloEventType(),
					createMasanielloRequest.getMasanielloName(),
					createMasanielloRequest.getMasanielloAverageQuote(),
					createMasanielloRequest.getMasanielloPercentage(),
					createMasanielloRequest.getMasanielloRounds(),
					createMasanielloRequest.getPatrimonyPercentage()
					);
			
			createMasanielloResponse.setMasanielloDto(MasanielloDataConverter.convert(masaniello));
			String ret = gson.toJson(createMasanielloResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}

}
