package com.paolorizzo.predictor.services.request;

public class GetDailyFixturesRequest {

	private String uniquecallid;

	public GetDailyFixturesRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getUniquecallid() {
		return uniquecallid;
	}

	public void setUniquecallid(String uniquecallid) {
		this.uniquecallid = uniquecallid;
	}

	public GetDailyFixturesRequest(String uniquecallid) {
		super();
		this.uniquecallid = uniquecallid;
	}

}
