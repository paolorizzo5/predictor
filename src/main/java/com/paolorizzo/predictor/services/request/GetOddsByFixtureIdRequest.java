package com.paolorizzo.predictor.services.request;

public class GetOddsByFixtureIdRequest  extends SimpleRequest{


	private String id;

	public GetOddsByFixtureIdRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
