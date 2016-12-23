package com.paolorizzo.predictor.services.request;

public class ListLeagueRequest {

	private String uniquecallid;

	public ListLeagueRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getUniquecallid() {
		return uniquecallid;
	}

	public void setUniquecallid(String uniquecallid) {
		this.uniquecallid = uniquecallid;
	}

	public ListLeagueRequest(String uniquecallid) {
		super();
		this.uniquecallid = uniquecallid;
	}

}
