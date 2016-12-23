package com.paolorizzo.predictor.services.request;

public class CompetitionTimedFixturesRequest {

	private String id;
	private String uniquecallid;

	public CompetitionTimedFixturesRequest() {
		// TODO Auto-generated constructor stub
	}

	public CompetitionTimedFixturesRequest(String id, String uniquecallid) {
		super();
		this.id = id;
		this.uniquecallid = uniquecallid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUniquecallid() {
		return uniquecallid;
	}

	public void setUniquecallid(String uniquecallid) {
		this.uniquecallid = uniquecallid;
	}

}
