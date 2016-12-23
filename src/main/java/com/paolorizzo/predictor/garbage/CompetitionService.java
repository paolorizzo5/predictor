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
import com.paolorizzo.predictor.business.CompetitionBusiness;
import com.paolorizzo.predictor.converters.CompetitionConverter;
import com.paolorizzo.predictor.hibernate.model.Competition;
import com.paolorizzo.predictor.services.request.ListLeagueRequest;
import com.paolorizzo.predictor.services.response.ListLeagueResponse;
import com.paolorizzo.predictor.spring.AppContext;

@Path("/CompetitionService")
@RequestScoped
public class CompetitionService {

	static Logger logger = LogManager.getLogger(CompetitionService.class
			.getName());

	CompetitionBusiness competitionBusiness = AppContext
			.getApplicationContext().getBean("competitionBusinessBean",
					CompetitionBusiness.class);

	@POST
	@Path("/list/")
	@Consumes("application/json")
	public Response list(String input) {
		Gson gson = new Gson();
		ListLeagueRequest listCompetitionRequest = gson.fromJson(input,
				ListLeagueRequest.class);

		ListLeagueResponse listCompetitionResponse = new ListLeagueResponse();
		try {
			logger.debug("list request ");
			List<Competition> competitions = competitionBusiness.list();
			listCompetitionResponse.setCompetitionDtos(CompetitionConverter
					.convertMinimal(competitions));

			String ret = gson.toJson(listCompetitionResponse).toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}
}
