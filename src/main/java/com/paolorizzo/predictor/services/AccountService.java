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
import com.paolorizzo.predictor.business.AccountBusiness;
import com.paolorizzo.predictor.dto.AccountDto;
import com.paolorizzo.predictor.dto.FixtureDto;
import com.paolorizzo.predictor.hibernate.model.Account;
import com.paolorizzo.predictor.services.request.AddAccountRequest;
import com.paolorizzo.predictor.services.request.AddBetRequest;
import com.paolorizzo.predictor.services.request.ArchiveBetRequest;
import com.paolorizzo.predictor.services.request.DeleteAccountRequest;
import com.paolorizzo.predictor.services.request.DepositAccountRequest;
import com.paolorizzo.predictor.services.request.GetAccountRequest;
import com.paolorizzo.predictor.services.request.GetDailyFixturesRequest;
import com.paolorizzo.predictor.services.request.GetFixturesByLeagueAndSeasonRequest;
import com.paolorizzo.predictor.services.request.GetLivescoresRequest;
import com.paolorizzo.predictor.services.request.ListAccountRequest;
import com.paolorizzo.predictor.services.request.MakBetRequest;
import com.paolorizzo.predictor.services.request.PushProspectElementRequest;
import com.paolorizzo.predictor.services.response.AddAccountResponse;
import com.paolorizzo.predictor.services.response.AddBetResponse;
import com.paolorizzo.predictor.services.response.ArchiveBetResponse;
import com.paolorizzo.predictor.services.response.DeleteAccountResponse;
import com.paolorizzo.predictor.services.response.DepositAccountResponse;
import com.paolorizzo.predictor.services.response.GetAccountResponse;
import com.paolorizzo.predictor.services.response.GetDailyFixturesResponse;
import com.paolorizzo.predictor.services.response.GetFixturesByLeagueAndSeasonResponse;
import com.paolorizzo.predictor.services.response.GetLivescoresResponse;
import com.paolorizzo.predictor.services.response.ListAccountResponse;
import com.paolorizzo.predictor.services.response.MarkBetResponse;
import com.paolorizzo.predictor.services.response.PushProspectElementResponse;
import com.paolorizzo.predictor.spring.AppContext;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Fixture;
import com.paolorizzo.xmlsoccer.business.FixtureBusiness;
import com.paolorizzo.xmlsoccer.data.converter.AccountDataConverter;
import com.paolorizzo.xmlsoccer.data.converter.FixtureDataConverter;

@Path("/AccountService")
@RequestScoped
public class AccountService {

	static Logger logger = LogManager.getLogger(AccountService.class.getName());

	AccountBusiness accountBusiness = AppContext.getApplicationContext()
			.getBean("accountBusinessBean", AccountBusiness.class);

	@POST
	@Path("/add/")
	@Consumes("application/json")
	public Response addAccount(String input) {
		Gson gson = new Gson();
		AddAccountRequest addAccountRequest = gson
				.fromJson(input, AddAccountRequest.class);

		AddAccountResponse addAccountResponse = new AddAccountResponse();
		try {
			logger.debug("addAccount request");

			Account account = accountBusiness.add(addAccountRequest.getName(),addAccountRequest.getDescription(),addAccountRequest.getEmail());
			if(account != null){
				addAccountResponse.setAccount(AccountDataConverter.convert(account));
				addAccountResponse.setResult(true);
			}else{
				addAccountResponse.setResult(false);
			}
			
			String ret = gson.toJson(addAccountResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}
	}
	
	@POST
	@Path("/delete/")
	@Consumes("application/json")
	public Response deletAccount(String input) {
		Gson gson = new Gson();
		DeleteAccountRequest deleteAccountRequest = gson
				.fromJson(input, DeleteAccountRequest.class);

		DeleteAccountResponse deleteAccountResponse = new DeleteAccountResponse();
		try {
			logger.debug("deleteAccount request");

			Account account = accountBusiness.delete(deleteAccountRequest.getName(),deleteAccountRequest.getEmail());
			if(account != null){
				deleteAccountResponse.setAccount(AccountDataConverter.convert(account));
				deleteAccountResponse.setResult(true);
			}else{
				deleteAccountResponse.setResult(false);
			}
			
			String ret = gson.toJson(deleteAccountResponse)
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
	public Response getAccount(String input) {
		Gson gson = new Gson();
		GetAccountRequest getAccountRequest = gson
				.fromJson(input, GetAccountRequest.class);

		GetAccountResponse getAccountResponse  = new GetAccountResponse();
		try {
			logger.debug("deleteAccount request");

			Account account = accountBusiness.get(getAccountRequest.getName(),getAccountRequest.getEmail());
			if(account != null){
				getAccountResponse.setAccount(AccountDataConverter.convert(account));
				getAccountResponse.setResult(true);
			}else{
				getAccountResponse.setResult(false);
			}
			
			String ret = gson.toJson(getAccountResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}
	}
	
	@POST
	@Path("/list/")
	@Consumes("application/json")
	public Response list(String input) {
		Gson gson = new Gson();
		ListAccountRequest listAccountRequest = gson
				.fromJson(input, ListAccountRequest.class);

		ListAccountResponse listAccountResponse = new ListAccountResponse();
		try {
			logger.debug("listAccount request");

			List<Account> accounts = accountBusiness.list(listAccountRequest.getEmail());
			List<AccountDto> accountDtos = AccountDataConverter.convert(accounts);
			
			
			listAccountResponse.setAccounts(accountDtos);
			String ret = gson.toJson(listAccountResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}
	}
	
	@POST
	@Path("/deposit/")
	@Consumes("application/json")
	public Response deposit(String input) {
		Gson gson = new Gson();
		DepositAccountRequest depositAccountRequest = gson
				.fromJson(input, DepositAccountRequest.class);

		DepositAccountResponse depositAccountResponse = new DepositAccountResponse();
		try {
			logger.debug("DepositAccountRequest");
			depositAccountResponse = accountBusiness.deposit(depositAccountRequest.getAccountName(),depositAccountRequest.getAmount(),depositAccountRequest.getEmail());
			
			String ret = gson.toJson(depositAccountResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}
	}
	
	@POST
	@Path("/withdraw/")
	@Consumes("application/json")
	public Response withdraw(String input) {
		Gson gson = new Gson();
		DepositAccountRequest depositAccountRequest = gson
				.fromJson(input, DepositAccountRequest.class);

		DepositAccountResponse depositAccountResponse = new DepositAccountResponse();
		try {
			logger.debug("WithdrawAccountRequest");
			depositAccountResponse = accountBusiness.withdraw(depositAccountRequest.getAccountName(),depositAccountRequest.getAmount(),depositAccountRequest.getEmail());
			
			String ret = gson.toJson(depositAccountResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}
	}
	
	@POST
	@Path("/addBet/")
	@Consumes("application/json")
	public Response addBet(String input) {
		Gson gson = new Gson();
		AddBetRequest addBetRequest = gson
				.fromJson(input, AddBetRequest.class);

		AddBetResponse addBetResponse = new AddBetResponse();
		try {
			logger.debug("addBetrequest");

			Account account = accountBusiness.addBet(addBetRequest.getAccountName(),addBetRequest.getAmount(),addBetRequest.getBettypeDescription(),addBetRequest.getEmail(),addBetRequest.getEventDescription(),addBetRequest.getMoltiplicator());
			
			if(account != null){
				addBetResponse.setAccount(AccountDataConverter.convert(account));
				addBetResponse.setResult(true);
			}else{
				addBetResponse.setResult(false);
			}
			
			String ret = gson.toJson(addBetResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}
	}
	
	@POST
	@Path("/markBet/")
	@Consumes("application/json")
	public Response markBet(String input) {
		Gson gson = new Gson();
		MakBetRequest markBetRequest = gson
				.fromJson(input, MakBetRequest.class);

		MarkBetResponse markBetResponse = new MarkBetResponse();
		try {
			logger.debug("makBetRequest");

			Account account = accountBusiness.markBet(markBetRequest.getAccountName(),markBetRequest.getEmail(),markBetRequest.getInsertDate(),markBetRequest.getIsWinning());
			
			if(account != null){
				markBetResponse.setAccount(AccountDataConverter.convert(account));
				markBetResponse.setResult(true);
			}else{
				markBetResponse.setResult(false);
			}
			
			String ret = gson.toJson(markBetResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}
	}
	
	@POST
	@Path("/archiveBet/")
	@Consumes("application/json")
	public Response archiveBet(String input) {
		Gson gson = new Gson();
		ArchiveBetRequest archiveBetRequest = gson
				.fromJson(input, ArchiveBetRequest.class);

		ArchiveBetResponse archiveBetResponse = new ArchiveBetResponse();
		try {
			logger.debug("makBetRequest");

			Account account = accountBusiness.archiveBet(archiveBetRequest.getAccountName(),archiveBetRequest.getEmail(),archiveBetRequest.getInsertDate());
			
			if(account != null){
				archiveBetResponse.setAccount(AccountDataConverter.convert(account));
				archiveBetResponse.setResult(true);
			}else{
				archiveBetResponse.setResult(false);
			}
			
			String ret = gson.toJson(archiveBetResponse)
					.toString();
			return Response.status(Status.OK).entity(ret).build();
		} catch (Exception e) {
			logger.error("list error occurred: ", e);
			return Response.status(Status.OK).entity(null).build();
		}
	}
	
	
}
