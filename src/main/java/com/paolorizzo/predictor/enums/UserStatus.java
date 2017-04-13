package com.paolorizzo.predictor.enums;

public enum UserStatus {

	STANDBY("STANDBY"), ON("ON"),OFF("OFF");

	private final String text;

	public String getText() {
		return text;
	}

	private UserStatus(String text) {
		this.text = text;
	}
	
}
