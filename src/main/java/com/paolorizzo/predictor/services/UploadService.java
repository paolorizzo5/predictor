package com.paolorizzo.predictor.services;

import java.io.IOException;
import java.io.InputStream;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.paolorizzo.predictor.business.DirettaFixtureBusiness;
import com.paolorizzo.predictor.spring.AppContext;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
@Path("/UploadService")
@RequestScoped
public class UploadService {

	static Logger logger = LogManager.getLogger(UploadService.class.getName());

	DirettaFixtureBusiness direttaFixtureBusiness = AppContext.getApplicationContext().getBean(
			"direttaFixtureBusinessBean", DirettaFixtureBusiness.class);

	@POST
	@Path("/uploadFixtures")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFixtures(@FormDataParam("file") InputStream inputStream,
		    @FormDataParam("file") FormDataContentDisposition fileDetails) {
	       
		@SuppressWarnings("unused")
		Gson gson = new Gson();
		direttaFixtureBusiness.addFromFile(inputStream,fileDetails);
		try {
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.OK).entity("MOCK").build();
	}
}
