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
import com.paolorizzo.predictor.services.request.GetOddsByFixtureIdRequest;
import com.paolorizzo.predictor.services.response.GetOddsByFixtureIdResponse;
import com.paolorizzo.predictor.spring.AppContext;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Odd;
import com.paolorizzo.xmlsoccer.business.OddBusiness;
import com.paolorizzo.xmlsoccer.data.converter.OddDataConverter;

@Path("/OddService")
@RequestScoped
public class OddService {

	static Logger logger = LogManager.getLogger(OddService.class.getName());

	OddBusiness oddBusiness = AppContext.getApplicationContext().getBean(
			"oddBusinessBean", OddBusiness.class);

	@POST
	@Path("/getOddsByFixtureId/")
	@Consumes("application/json")
	public Response getOddsByFixtureId(String input) {
		Gson gson = new Gson();
		GetOddsByFixtureIdRequest getOddsByFixtureIdRequest = gson.fromJson(
				input, GetOddsByFixtureIdRequest.class);

		GetOddsByFixtureIdResponse getOddsByFixtureIdResponse = new GetOddsByFixtureIdResponse();
		try {
			logger.debug("getFixturesByLeagueAndSeason request");

			List<XmlSoccer_Odd> odds = oddBusiness
					.getOddsByFixtureId(getOddsByFixtureIdRequest.getId());

			getOddsByFixtureIdResponse.setOdds(OddDataConverter.convert(odds));

			String ret = gson.toJson(getOddsByFixtureIdResponse).toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}

}
