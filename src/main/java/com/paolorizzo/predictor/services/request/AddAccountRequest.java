package com.paolorizzo.predictor.services.request;

public class AddAccountRequest extends SimpleRequest {
	
	private String name;
	
	private String description;
	
	private String email;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
