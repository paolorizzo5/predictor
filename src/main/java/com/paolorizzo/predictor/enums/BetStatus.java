package com.paolorizzo.predictor.enums;

public enum BetStatus {

	PLACED("PLACED"), WINNING("WINNING"),LOSING("LOSING"), ARCHIVED_WIN("ARCHIVED_WIN"),ARCHIVED_LOST("ARCHIVED_LOST");

	private final String text;

	public String getText() {
		return text;
	}

	private BetStatus(String text) {
		this.text = text;
	}
	
}
