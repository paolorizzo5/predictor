package com.paolorizzo.predictor.services;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.paolorizzo.predictor.business.UserBusiness;
import com.paolorizzo.predictor.hibernate.model.User;
import com.paolorizzo.predictor.services.request.AddAccountRequest;
import com.paolorizzo.predictor.services.request.UserLoginRequest;
import com.paolorizzo.predictor.services.request.UserSignupRequest;
import com.paolorizzo.predictor.services.response.AddAccountResponse;
import com.paolorizzo.predictor.services.response.UserSignupResponse;
import com.paolorizzo.predictor.services.response.user.UserLoginResponse;
import com.paolorizzo.predictor.spring.AppContext;
import com.paolorizzo.predictor.utils.MD5;

@Path("/UserService")
@RequestScoped
public class UserService {

	static Logger logger = LogManager.getLogger(UserService.class.getName());

	UserBusiness userBusiness = AppContext.getApplicationContext().getBean(
			"userBusinessBean", UserBusiness.class);

	/**
	 * rest service per gestire la login
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@POST
	@Path("/login/")
	@Consumes("application/json")
	public Response login(String input) {
		Gson gson = new Gson();
		
		UserLoginRequest userLoginRequest = gson
				.fromJson(input, UserLoginRequest.class);

		userLoginRequest.setPassword(MD5.getMD5(userLoginRequest.getPassword()));
		
		UserLoginResponse userLoginResponse = new UserLoginResponse();
		
		try {
			logger.debug("login request by username: " + userLoginRequest.getEmail());
			userLoginResponse = userBusiness.login(userLoginRequest.getEmail(), userLoginRequest.getPassword());
		} catch (Exception e) {
			logger.error("login error occurred: ", e);
		}
		
		String ret = gson.toJson(userLoginResponse).toString();
		return Response.status(Status.OK).entity(ret).build();
	}

	/**
	 * rest service per gestire la registrazione utente
	 * 
	 * @param username
	 * @return
	 */
	@POST
	@Path("/signup/")
	@Consumes("application/json")
	public Response signup(String input) {
		
		Gson gson = new Gson();
		
		UserSignupRequest userSignupRequest = gson
				.fromJson(input, UserSignupRequest.class);

		UserSignupResponse userSignupResponse = new UserSignupResponse();
		
		
		Boolean result;
		try {
			logger.debug("register request by email: " + userSignupRequest.getEmail());
			result = userBusiness.signup(userSignupRequest.getEmail());
			
			userSignupResponse.setResult(result);
		} catch (Exception e) {
			logger.error("register error occurred: ", e);
		}
		String ret = gson.toJson(userSignupResponse).toString();
		return Response.status(Status.OK).entity(ret).build();
	}
	
	@GET
	@Path("/completeRegistration/{email}&{password}&{uniquecallid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@PathParam("email") String email,
			@PathParam("password") String password) {
		UserLoginResponse loginResponse = null;
		try {
			logger.debug("login request by username: " + email);
			loginResponse = userBusiness.login(email, password);
		} catch (Exception e) {
			logger.error("login error occurred: ", e);
		}
		Gson gson = new Gson();
		String ret = gson.toJson(loginResponse).toString();
		return Response.status(Status.OK).entity(ret).build();
	}


}
