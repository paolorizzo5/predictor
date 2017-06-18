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
import com.google.gson.GsonBuilder;
import com.paolorizzo.predictor.business.DirettaFixtureBusiness;
import com.paolorizzo.predictor.business.MasanielloAdvancedBusiness;
import com.paolorizzo.predictor.business.MasanielloBaseBusiness;
import com.paolorizzo.predictor.business.MasanielloBusiness;
import com.paolorizzo.predictor.business.MasanielloPlanBusiness;
import com.paolorizzo.predictor.business.UserBusiness;
import com.paolorizzo.predictor.hibernate.model.DirettaFixture;
import com.paolorizzo.predictor.hibernate.model.Masaniello;
import com.paolorizzo.predictor.hibernate.model.MasanielloPlan;
import com.paolorizzo.predictor.hibernate.model.User;
import com.paolorizzo.predictor.services.request.CreateMasanielloRequest;
import com.paolorizzo.predictor.services.request.DeleteMasanielloPlanRequest;
import com.paolorizzo.predictor.services.request.GetDirettaCompetitionsRequest;
import com.paolorizzo.predictor.services.request.GetDirettaFixturesRequest;
import com.paolorizzo.predictor.services.request.GetMasanielloRoundsRequest;
import com.paolorizzo.predictor.services.request.GetPlanDetailRequest;
import com.paolorizzo.predictor.services.request.ListMasanielloPlanRequest;
import com.paolorizzo.predictor.services.response.CreateMasanielloResponse;
import com.paolorizzo.predictor.services.response.CreatePlanResponse;
import com.paolorizzo.predictor.services.response.DeleteMasanielloPlanResponse;
import com.paolorizzo.predictor.services.response.GetDirettaCompetitionsResponse;
import com.paolorizzo.predictor.services.response.GetDirettaFixturesResponse;
import com.paolorizzo.predictor.services.response.GetMasanielloRoundsResponse;
import com.paolorizzo.predictor.services.response.GetPlanDetailResponse;
import com.paolorizzo.predictor.services.response.ListMasanielloPlanResponse;
import com.paolorizzo.predictor.services.response.ListMasanielloPlansNamesResponse;
import com.paolorizzo.predictor.spring.AppContext;
import com.paolorizzo.xmlsoccer.data.converter.MasanielloPlanDataConverter;
import com.paolorizzo.xmlsoccer.data.converter.MasanielloRoundDataConverter;

@Path("/DirettaFixtureService")
@RequestScoped
public class DirettaFixtureService {

	static Logger logger = LogManager.getLogger("root");

	DirettaFixtureBusiness direttaFixtureBusiness = AppContext.getApplicationContext()
			.getBean("direttaFixtureBusinessBean", DirettaFixtureBusiness.class);
	
	MasanielloPlanBusiness masanielloPlanBusiness = AppContext.getApplicationContext()
			.getBean("masanielloPlanBusinessBean", MasanielloPlanBusiness.class);
	
	MasanielloBusiness masanielloBusiness = AppContext.getApplicationContext()
			.getBean("masanielloBusinessBean", MasanielloBusiness.class);
	
	
	UserBusiness userBusiness = AppContext.getApplicationContext()
			.getBean("userBusinessBean", UserBusiness.class);
	
	MasanielloBaseBusiness masanielloBaseBusiness = AppContext.getApplicationContext()
			.getBean("masanielloBaseBusinessBean", MasanielloBaseBusiness.class);
	
	
	MasanielloAdvancedBusiness masanielloAdvancedBusiness = AppContext.getApplicationContext()
			.getBean("masanielloAdvancedBusinessBean", MasanielloAdvancedBusiness.class);
	

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
					getDirettaFixturesRequest.getQuota2To(),
					getDirettaFixturesRequest.getDateFrom(),
					getDirettaFixturesRequest.getDateTo()
					);
			
			getDirettaFixturesResponse = direttaFixtureBusiness.getStats(direttaFixtures);
			if(!getDirettaFixturesRequest.getFullSearch()){
				getDirettaFixturesResponse.setDirettaFixtures(null);
			}
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

			//List<PlanFilter> planFilters = gson.fromJson(createMasanielloRequest.getFilters(), new TypeToken<List<PlanFilter>>(){}.getType());
			
			//MasanielloPlan masanielloPlan = masanielloPlanBusiness.getPlan(createMasanielloRequest.getMasanielloUserEmail(), createMasanielloRequest.getMasanielloName());
			
			Boolean result = masanielloBaseBusiness.createMasaniello(
					createMasanielloRequest.getFiltersBase(),
					createMasanielloRequest.getMasanielloUserEmail(),
					createMasanielloRequest.getMasanielloAmount(),
					createMasanielloRequest.getMasanielloEventToWin(),
					createMasanielloRequest.getMasanielloName(),
					createMasanielloRequest.getMasanielloAverageQuote(),
					createMasanielloRequest.getMasanielloAdditionalQuote(),
					createMasanielloRequest.getMasanielloPercentage(),
					createMasanielloRequest.getMasanielloRounds(),
					createMasanielloRequest.getPatrimonyPercentage(),
					createMasanielloRequest.getId()
					);
			
			createMasanielloResponse.setResult(result);
			String ret = gson.toJson(createMasanielloResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}
	
	@POST
	@Path("/createMasanielloAdvanced/")
	@Consumes("application/json")
	public Response createMasanielloAdvanced(String input) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		CreateMasanielloRequest createMasanielloRequest  = gson
				.fromJson(input, CreateMasanielloRequest.class);

		CreateMasanielloResponse createMasanielloResponse = new CreateMasanielloResponse();
		try {
			logger.debug("getDirettaCompetitions request");	
			Boolean result = masanielloAdvancedBusiness.createMasanielloAdvanced(
					createMasanielloRequest.getFiltersAdvanced(),
					createMasanielloRequest.getMasanielloUserEmail(),
					createMasanielloRequest.getMasanielloAmount(),
					createMasanielloRequest.getMasanielloName(),
					createMasanielloRequest.getMasanielloRounds(),
					createMasanielloRequest.getPatrimonyPercentage(),
					createMasanielloRequest.getLowerByWin(),
					createMasanielloRequest.getRaiseByLoss(),
					createMasanielloRequest.getId()
					);
			
			createMasanielloResponse.setResult(result);
			String ret = gson.toJson(createMasanielloResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}
	
	@POST
	@Path("/createPlan/")
	@Consumes("application/json")
	public Response createPlan(String input) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		CreateMasanielloRequest createMasanielloRequest  = gson
				.fromJson(input, CreateMasanielloRequest.class);

		
		//List<PlanFilter> planFilters = gson.fromJson(createMasanielloRequest.getFilters(), new TypeToken<List<PlanFilter>>(){}.getType());
		
		
		CreatePlanResponse createPlanResponse  = new CreatePlanResponse();
		try {
			logger.debug("getDirettaCompetitions request");

			MasanielloPlan masanielloPlan = direttaFixtureBusiness.createPlan(
					createMasanielloRequest.getFiltersBase(),
					createMasanielloRequest.getMasanielloUserEmail(),
					createMasanielloRequest.getMasanielloAmount(),
					createMasanielloRequest.getMasanielloEventToWin(),
					createMasanielloRequest.getMasanielloName(),
					createMasanielloRequest.getMasanielloAverageQuote(),
					createMasanielloRequest.getMasanielloAdditionalQuote(),
					createMasanielloRequest.getMasanielloPercentage(),
					createMasanielloRequest.getMasanielloRounds(),
					createMasanielloRequest.getPatrimonyPercentage()
					);
			
			createPlanResponse.setResult(masanielloPlan != null);
			String ret = gson.toJson(createPlanResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}
	
	@POST
	@Path("/createPlanAdvanced/")
	@Consumes("application/json")
	public Response createPlanAdvanced(String input) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		CreateMasanielloRequest createMasanielloRequest  = gson
				.fromJson(input, CreateMasanielloRequest.class);

		
		//List<PlanFilter> planFilters = gson.fromJson(createMasanielloRequest.getFilters(), new TypeToken<List<PlanFilter>>(){}.getType());
		
		
		CreatePlanResponse createPlanResponse  = new CreatePlanResponse();
		try {
			logger.debug("getDirettaCompetitions request");

			MasanielloPlan masanielloPlan = direttaFixtureBusiness.createPlan(
					createMasanielloRequest.getFiltersAdvanced(),
					createMasanielloRequest.getMasanielloUserEmail(),
					createMasanielloRequest.getMasanielloAmount(),
					createMasanielloRequest.getMasanielloName(),
					createMasanielloRequest.getMasanielloRounds(),
					createMasanielloRequest.getPatrimonyPercentage()
					);
			
			createPlanResponse.setResult(masanielloPlan != null);
			String ret = gson.toJson(createPlanResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}
	
	@POST
	@Path("/getMasanielloPlans/")
	@Consumes("application/json")
	public Response getMasanielloPlans(String input) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		ListMasanielloPlanRequest listMasanielloPlanRequest   = gson
				.fromJson(input, ListMasanielloPlanRequest.class);

		ListMasanielloPlanResponse listMasanielloPlanResponse = new ListMasanielloPlanResponse();
		try {
			logger.debug("ListMasanielloPlanResponse request");

			List<MasanielloPlan> plans = masanielloPlanBusiness.list(listMasanielloPlanRequest.getEmail());
			listMasanielloPlanResponse.setPlans(MasanielloPlanDataConverter.convert(plans));
			String ret = gson.toJson(listMasanielloPlanResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}
	
	
	@POST
	@Path("/getPlanDetail/")
	@Consumes("application/json")
	public Response getPlanDetail(String input) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		GetPlanDetailRequest getPlanDetailRequest   = gson
				.fromJson(input, GetPlanDetailRequest.class);

		GetPlanDetailResponse getPlanDetailResponse = new GetPlanDetailResponse();
		try {
			logger.debug("ListMasanielloPlanResponse request");

			MasanielloPlan plan = masanielloPlanBusiness.getPlan(getPlanDetailRequest.getEmail(),getPlanDetailRequest.getName());
			getPlanDetailResponse.setPlan(MasanielloPlanDataConverter.convert(plan));
			String ret = gson.toJson(getPlanDetailResponse).toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}
	
	@POST
	@Path("/getPlansNames/")
	@Consumes("application/json")
	public Response getPlansNames(String input) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		ListMasanielloPlanRequest listMasanielloPlanRequest   = gson
				.fromJson(input, ListMasanielloPlanRequest.class);

		ListMasanielloPlansNamesResponse listMasanielloPlansNamesResponse = new ListMasanielloPlansNamesResponse();
		try {
			logger.debug("ListMasanielloPlanResponse request");

			listMasanielloPlansNamesResponse.setPlanNames(masanielloPlanBusiness.getPlansNames(listMasanielloPlanRequest.getEmail()));
			String ret = gson.toJson(listMasanielloPlansNamesResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}
	
	@POST
	@Path("/deleteMasanielloPlan/")
	@Consumes("application/json")
	public Response deleteMasanielloPlan(String input) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		DeleteMasanielloPlanRequest deleteMasanielloPlanRequest   = gson
				.fromJson(input, DeleteMasanielloPlanRequest.class);

		DeleteMasanielloPlanResponse deleteMasanielloPlanResponse = new DeleteMasanielloPlanResponse();
		try {
			logger.debug("ListMasanielloPlanResponse request");

			Boolean success = userBusiness.deleteMasanielloPlan(deleteMasanielloPlanRequest.getEmail(),deleteMasanielloPlanRequest.getName());
			
			//Boolean success = masanielloPlanBusiness.delete(deleteMasanielloPlanRequest.getEmail(),deleteMasanielloPlanRequest.getName());
			deleteMasanielloPlanResponse.setSuccess(success);
			String ret = gson.toJson(deleteMasanielloPlanResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}
	
	@POST
	@Path("/getMasanielloRounds/")
	@Consumes("application/json")
	public Response getMasanielloRounds(String input) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		GetMasanielloRoundsRequest getMasanielloRoundsRequest   = gson
				.fromJson(input, GetMasanielloRoundsRequest.class);

		GetMasanielloRoundsResponse getMasanielloRoundsResponse = new GetMasanielloRoundsResponse();
		try {
			logger.debug("ListMasanielloPlanResponse request");
			User user = userBusiness.findByEmail(getMasanielloRoundsRequest.getEmail());
			for (MasanielloPlan masanielloPlan : user.getMasanielloPlans()) {
				if(masanielloPlan.getName().equals(getMasanielloRoundsRequest.getPlanName())){
					for (Masaniello masaniello : masanielloPlan.getMasaniellos()) {
						if(masaniello.getName().equals(getMasanielloRoundsRequest.getName()) && masaniello.getId() == getMasanielloRoundsRequest.getId()){
							getMasanielloRoundsResponse.setRounds(MasanielloRoundDataConverter.convert(masaniello.getMasanielloRounds()));
							String ret = gson.toJson(getMasanielloRoundsResponse)
									.toString();
							return Response.status(Status.OK).entity(ret).build();
						}
					}
				}
			}
			String ret = gson.toJson(getMasanielloRoundsResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}

	}

}
