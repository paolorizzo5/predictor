package com.paolorizzo.predictor.services;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.paolorizzo.predictor.business.ProspectBusiness;
import com.paolorizzo.predictor.hibernate.model.Account;
import com.paolorizzo.predictor.hibernate.model.Prospect;
import com.paolorizzo.predictor.services.request.AddProspectRequest;
import com.paolorizzo.predictor.services.request.GetProspectRequest;
import com.paolorizzo.predictor.services.request.PopProspectElementRequest;
import com.paolorizzo.predictor.services.request.PushProspectElementRequest;
import com.paolorizzo.predictor.services.response.AddProspectResponse;
import com.paolorizzo.predictor.services.response.GetProspectResponse;
import com.paolorizzo.predictor.services.response.PopProspectElementResponse;
import com.paolorizzo.predictor.services.response.PushProspectElementResponse;
import com.paolorizzo.predictor.spring.AppContext;
import com.paolorizzo.xmlsoccer.data.converter.AccountDataConverter;
import com.paolorizzo.xmlsoccer.data.converter.ProspectDataConverter;

@Path("/ProspectService")
@RequestScoped
public class ProspectService {

	static Logger logger = LogManager.getLogger(ProspectService.class.getName());

	ProspectBusiness prospectBusiness = AppContext.getApplicationContext()
			.getBean("prospectBusinessBean", ProspectBusiness.class);

	@POST
	@Path("/add/")
	@Consumes("application/json")
	public Response addProspect(String input) {
		Gson gson = new Gson();
		AddProspectRequest addProspectRequest = gson
				.fromJson(input, AddProspectRequest.class);

		AddProspectResponse addProspectResponse = new AddProspectResponse();
		try {
			logger.debug("addProspect request");

			Prospect prospect = prospectBusiness.add(addProspectRequest.getName(),addProspectRequest.getAccountName(),addProspectRequest.getDailyPercentageExpected(),addProspectRequest.getDuration(),addProspectRequest.getEmail(),addProspectRequest.getInitialAmount());
			if(prospect != null){
				addProspectResponse.setProspect(ProspectDataConverter.convert(prospect));
				addProspectResponse.setResult(true);
			}else{
				addProspectResponse.setResult(false);
			}
			
			String ret = gson.toJson(addProspectResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}
	}
	
	@POST
	@Path("/get/")
	@Consumes("application/json")
	public Response getProspect(String input) {
		Gson gson = new Gson();
		GetProspectRequest getProspectRequest = gson
				.fromJson(input, GetProspectRequest.class);

		GetProspectResponse getProspectResponse = new GetProspectResponse();
		try {
			logger.debug("getProspect request");
			Prospect prospect = prospectBusiness.get(getProspectRequest.getAccountName(),getProspectRequest.getEmail());

			getProspectResponse.setProspect(ProspectDataConverter.convert(prospect));
			
			String ret = gson.toJson(getProspectResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}
	}
	
	@POST
	@Path("/pushProspectElement/")
	@Consumes("application/json")
	public Response pushProspectElement(String input) {
		Gson gson = new Gson();
		PushProspectElementRequest pushProspectElementRequest = gson
				.fromJson(input, PushProspectElementRequest.class);

		PushProspectElementResponse pushProspectElementResponse  = new PushProspectElementResponse();
		try {
			logger.debug("makBetRequest");

			Prospect prospect = prospectBusiness.pushProspectElement(pushProspectElementRequest.getAccountName(),pushProspectElementRequest.getEmail(),pushProspectElementRequest.getIncremental(),pushProspectElementRequest.getProspectName());
			
			if(prospect != null){
				pushProspectElementResponse.setProspect(ProspectDataConverter.convert(prospect));
				pushProspectElementResponse.setResult(true);
			}else{
				pushProspectElementResponse.setResult(false);
			}
			
			String ret = gson.toJson(pushProspectElementResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}
	}
	
	@POST
	@Path("/popProspectElement/")
	@Consumes("application/json")
	public Response popProspectElement(String input) {
		Gson gson = new Gson();
		PopProspectElementRequest popProspectElementRequest = gson
				.fromJson(input, PopProspectElementRequest.class);

		PopProspectElementResponse popProspectElementResponse = new PopProspectElementResponse();
		try {
			logger.debug("makBetRequest");

			Prospect prospect = prospectBusiness.popProspectElement(popProspectElementRequest.getAccountName(),popProspectElementRequest.getEmail(),popProspectElementRequest.getIncremental(),popProspectElementRequest.getProspectName());
			
			if(prospect != null){
				popProspectElementResponse.setProspect(ProspectDataConverter.convert(prospect));
				popProspectElementResponse.setResult(true);
			}else{
				popProspectElementResponse.setResult(false);
			}
			
			String ret = gson.toJson(popProspectElementResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}
	}
	
}
