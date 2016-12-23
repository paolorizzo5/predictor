package com.paolorizzo.predictor.services.request;

public class GetOddsByFixtureIdRequest {

	private String uniquecallid;

	private String id;

	public GetOddsByFixtureIdRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getUniquecallid() {
		return uniquecallid;
	}

	public void setUniquecallid(String uniquecallid) {
		this.uniquecallid = uniquecallid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
