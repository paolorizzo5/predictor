package com.paolorizzo.predictor.services;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
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
import com.paolorizzo.predictor.services.response.user.UserLoginResponse;
import com.paolorizzo.predictor.spring.AppContext;

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
	@GET
	@Path("/login/{email}&{password}&{uniquecallid}")
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

	/**
	 * rest service per gestire la registrazione utente
	 * 
	 * @param username
	 * @return
	 */
	@GET
	@Path("/signup/{email}&{uniquecallid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response signup(@PathParam("email") String email) {
		User user = null;
		try {
			logger.debug("register request by email: " + email);
			user = userBusiness.signup(email);
		} catch (Exception e) {
			logger.error("register error occurred: ", e);
		}
		Gson gson = new Gson();
		String ret = gson.toJson(user).toString();
		return Response.status(Status.OK).entity(ret).build();
	}

}
