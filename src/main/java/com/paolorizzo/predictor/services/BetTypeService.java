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
import com.paolorizzo.predictor.business.AccountBusiness;
import com.paolorizzo.predictor.business.BetTypeBusiness;
import com.paolorizzo.predictor.dto.AccountDto;
import com.paolorizzo.predictor.hibernate.model.Account;
import com.paolorizzo.predictor.hibernate.model.BetType;
import com.paolorizzo.predictor.services.request.AddAccountRequest;
import com.paolorizzo.predictor.services.request.AddBetRequest;
import com.paolorizzo.predictor.services.request.ArchiveBetRequest;
import com.paolorizzo.predictor.services.request.DeleteAccountRequest;
import com.paolorizzo.predictor.services.request.DepositAccountRequest;
import com.paolorizzo.predictor.services.request.GetAccountRequest;
import com.paolorizzo.predictor.services.request.ListAccountRequest;
import com.paolorizzo.predictor.services.request.ListBetTypeRequest;
import com.paolorizzo.predictor.services.request.MakBetRequest;
import com.paolorizzo.predictor.services.response.AddAccountResponse;
import com.paolorizzo.predictor.services.response.AddBetResponse;
import com.paolorizzo.predictor.services.response.ArchiveBetResponse;
import com.paolorizzo.predictor.services.response.DeleteAccountResponse;
import com.paolorizzo.predictor.services.response.DepositAccountResponse;
import com.paolorizzo.predictor.services.response.GetAccountResponse;
import com.paolorizzo.predictor.services.response.ListAccountResponse;
import com.paolorizzo.predictor.services.response.ListBetTypeResponse;
import com.paolorizzo.predictor.services.response.MarkBetResponse;
import com.paolorizzo.predictor.spring.AppContext;
import com.paolorizzo.xmlsoccer.data.converter.AccountDataConverter;
import com.paolorizzo.xmlsoccer.data.converter.BetTypeDataConverter;

@Path("/BetTypeService")
@RequestScoped
public class BetTypeService {

	static Logger logger = LogManager.getLogger(BetTypeService.class.getName());

	BetTypeBusiness betTypeBusiness = AppContext.getApplicationContext()
			.getBean("betTypeBusinessBean", BetTypeBusiness.class);

	@POST
	@Path("/list/")
	@Consumes("application/json")
	public Response list(String input) {
		Gson gson = new Gson();
		ListBetTypeRequest listBetTypeRequest  = gson
				.fromJson(input, ListBetTypeRequest.class);

		ListBetTypeResponse listBetTypeResponse = new ListBetTypeResponse();
		try {
			logger.debug("listBetType request");

			List<BetType> betTypes = betTypeBusiness.list();
			
			
			listBetTypeResponse.setBettypes(BetTypeDataConverter.convert(betTypes));
			String ret = gson.toJson(listBetTypeResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}
	}
	
	
	
}
