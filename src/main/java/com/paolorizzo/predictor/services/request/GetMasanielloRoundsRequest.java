package com.paolorizzo.predictor.services.request;

public class GetMasanielloRoundsRequest extends SimpleRequest {
	
	private String email;
	
	private String name;
	
	private Integer id;
	
	private String planName;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
