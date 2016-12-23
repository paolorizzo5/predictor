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
import com.paolorizzo.predictor.services.request.ListLeagueRequest;
import com.paolorizzo.predictor.services.response.ListLeagueResponse;
import com.paolorizzo.predictor.spring.AppContext;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_League;
import com.paolorizzo.xmlsoccer.business.LeagueBusiness;
import com.paolorizzo.xmlsoccer.data.converter.LeagueDataConverter;

@Path("/LeagueService")
@RequestScoped
public class LeagueService {

	static Logger logger = LogManager.getLogger(LeagueService.class.getName());

	LeagueBusiness leagueBusiness = AppContext.getApplicationContext().getBean(
			"leagueBusinessBean", LeagueBusiness.class);

	@POST
	@Path("/list/")
	@Consumes("application/json")
	public Response list(String input) {
		Gson gson = new Gson();
		ListLeagueRequest listLeagueRequest = gson.fromJson(input,
				ListLeagueRequest.class);

		ListLeagueResponse listLeagueResponse = new ListLeagueResponse();
		try {
			logger.debug("list request ");
			List<XmlSoccer_League> leagues = leagueBusiness.list();
			listLeagueResponse.setLeagues(LeagueDataConverter.convert(leagues));

			String ret = gson.toJson(listLeagueResponse).toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}

}
