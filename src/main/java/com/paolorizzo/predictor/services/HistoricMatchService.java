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
import com.paolorizzo.predictor.dto.FixturePreviewDto;
import com.paolorizzo.predictor.dto.HistoricMatchDto;
import com.paolorizzo.predictor.services.request.GetFixturePreviewRequest;
import com.paolorizzo.predictor.services.request.GetHistoricMatchesByLeagueAndSeasonRequest;
import com.paolorizzo.predictor.services.response.GetFixturePreviewResponse;
import com.paolorizzo.predictor.services.response.GetHistoricMatchesByLeagueAndSeasonResponse;
import com.paolorizzo.predictor.spring.AppContext;
import com.paolorizzo.xmlsoccer.business.HistoricMatchBusiness;
import com.paolorizzo.xmlsoccer.data.converter.HistoricMatchDataConverter;

@Path("/HistoricMatchService")
@RequestScoped
public class HistoricMatchService {

	static Logger logger = LogManager.getLogger(HistoricMatchService.class
			.getName());

	HistoricMatchBusiness historicMatchBusiness = AppContext
			.getApplicationContext().getBean("historicMatchBusinessBean",
					HistoricMatchBusiness.class);

	@POST
	@Path("/getHistoricMatchesByLeagueAndSeason/")
	@Consumes("application/json")
	public Response getHistoricMatchesByLeagueAndSeason(String input) {
		Gson gson = new Gson();
		GetHistoricMatchesByLeagueAndSeasonRequest getHistoricMatchesByLeagueAndSeasonRequest = gson
				.fromJson(input,
						GetHistoricMatchesByLeagueAndSeasonRequest.class);

		GetHistoricMatchesByLeagueAndSeasonResponse getHistoricMatchesByLeagueAndSeasonResponse = new GetHistoricMatchesByLeagueAndSeasonResponse();
		try {
			logger.debug("getFixturesByLeagueAndSeason request");

			List<HistoricMatchDto> historicMatchDtos = historicMatchBusiness
					.getHistoricMatchesByLeagueAndSeason(
							getHistoricMatchesByLeagueAndSeasonRequest
									.getLeague(),
							getHistoricMatchesByLeagueAndSeasonRequest
									.getSeason());

			Collections.reverse(historicMatchDtos);
			getHistoricMatchesByLeagueAndSeasonResponse
					.setHistoricMatches(historicMatchDtos);

			String ret = gson.toJson(
					getHistoricMatchesByLeagueAndSeasonResponse).toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}

	@POST
	@Path("/getFixturePreview/")
	@Consumes("application/json")
	public Response getFixturePreview(String input) {
		Gson gson = new Gson();
		GetFixturePreviewRequest getFixturePreviewRequest = gson.fromJson(
				input, GetFixturePreviewRequest.class);

		GetFixturePreviewResponse getFixturePreviewResponse = new GetFixturePreviewResponse();
		try {
			logger.debug("getFixturesByLeagueAndSeason request");

			FixturePreviewDto fixturePreviewDto = historicMatchBusiness
					.getFixturePreview(
							getFixturePreviewRequest.getHomeTeamId(),
							getFixturePreviewRequest.getAwayTeamId());

			Collections.sort(fixturePreviewDto.getPreviousMatches());
			getFixturePreviewResponse.setFixturePreviewDto(fixturePreviewDto);
			getFixturePreviewResponse
					.setLast5Home(HistoricMatchDataConverter
							.convert(historicMatchBusiness
									.getLast5(getFixturePreviewRequest
											.getHomeTeamId())));
			getFixturePreviewResponse
					.setLast5Away(HistoricMatchDataConverter
							.convert(historicMatchBusiness
									.getLast5(getFixturePreviewRequest
											.getAwayTeamId())));

			getFixturePreviewResponse
					.setFixturePreviewThisSeason(historicMatchBusiness
							.getSeasonStats(
									getFixturePreviewRequest.getHomeTeamId(),
									getFixturePreviewRequest.getAwayTeamId(),
									getFixturePreviewRequest.getLeague(),
									getFixturePreviewRequest.getSeason()));

			String ret = gson.toJson(getFixturePreviewResponse).toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}

}
