package com.paolorizzo.predictor.services.request;

public class GetLivescoresRequest {

	private String uniquecallid;

	public GetLivescoresRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getUniquecallid() {
		return uniquecallid;
	}

	public void setUniquecallid(String uniquecallid) {
		this.uniquecallid = uniquecallid;
	}

	public GetLivescoresRequest(String uniquecallid) {
		super();
		this.uniquecallid = uniquecallid;
	}

}
