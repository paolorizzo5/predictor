package com.paolorizzo.predictor.enums;

public enum HomeAwayEnum {

	HOME("HOME"), AWAY("AWAY"), ALL("ALL");

	private final String text;

	public String getText() {
		return text;
	}

	private HomeAwayEnum(String text) {
		this.text = text;
	}

}
