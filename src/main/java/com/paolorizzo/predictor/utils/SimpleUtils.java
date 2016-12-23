package com.paolorizzo.predictor.utils;

import java.util.Random;

public class SimpleUtils {

	public static String generateString() {
		int length = 6;
		String characters = "0123456789QWERTYUIOPLKJHGFDSAZXCVBNM";
		Random rng = new Random();
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}

		return new String(text);
	}

	public static Integer getNullOrValue(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception exception) {
			return null;
		}
	}

}
