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
import com.paolorizzo.predictor.dto.FixtureDto;
import com.paolorizzo.predictor.services.request.GetFixturesByLeagueAndSeasonRequest;
import com.paolorizzo.predictor.services.response.GetFixturesByLeagueAndSeasonResponse;
import com.paolorizzo.predictor.spring.AppContext;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Fixture;
import com.paolorizzo.xmlsoccer.business.FixtureBusiness;
import com.paolorizzo.xmlsoccer.data.converter.FixtureDataConverter;

@Path("/FixtureService")
@RequestScoped
public class FixtureService {

	static Logger logger = LogManager.getLogger(FixtureService.class.getName());

	FixtureBusiness fixtureBusiness = AppContext.getApplicationContext()
			.getBean("fixtureBusinessBean", FixtureBusiness.class);

	@POST
	@Path("/getFixturesByLeagueAndSeason/")
	@Consumes("application/json")
	public Response getFixturesByLeagueAndSeason(String input) {
		Gson gson = new Gson();
		GetFixturesByLeagueAndSeasonRequest getFixturesByLeagueAndSeasonRequest = gson
				.fromJson(input, GetFixturesByLeagueAndSeasonRequest.class);

		GetFixturesByLeagueAndSeasonResponse getFixturesByLeagueAndSeasonResponse = new GetFixturesByLeagueAndSeasonResponse();
		try {
			logger.debug("getFixturesByLeagueAndSeason request");

			List<XmlSoccer_Fixture> fixtures = fixtureBusiness
					.getFixturesByLeagueAndSeason(
							getFixturesByLeagueAndSeasonRequest.getLeague(),
							getFixturesByLeagueAndSeasonRequest.getSeason());

			List<FixtureDto> dtos = FixtureDataConverter.convert(fixtures);
			Collections.sort(dtos);
			getFixturesByLeagueAndSeasonResponse.setFixtures(dtos);

			String ret = gson.toJson(getFixturesByLeagueAndSeasonResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}
}
