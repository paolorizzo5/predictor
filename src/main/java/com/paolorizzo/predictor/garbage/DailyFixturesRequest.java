package com.paolorizzo.predictor.services.request;

public class DailyFixturesRequest {

	private String uniquecallid;

	public DailyFixturesRequest() {
		// TODO Auto-generated constructor stub
	}

	public DailyFixturesRequest(String uniquecallid) {
		super();
		this.uniquecallid = uniquecallid;
	}

	public String getUniquecallid() {
		return uniquecallid;
	}

	public void setUniquecallid(String uniquecallid) {
		this.uniquecallid = uniquecallid;
	}

}
