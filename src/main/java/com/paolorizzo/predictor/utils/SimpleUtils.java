package com.paolorizzo.predictor.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import com.github.pabloo99.xmlsoccer.model.enums.Seasons;
import com.paolorizzo.predictor.hibernate.model.DirettaFixture;

public class SimpleUtils {

	public String generateString() {
		int length = 6;
		String characters = "0123456789QWERTYUIOPLKJHGFDSAZXCVBNM";
		Random rng = new Random();
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}

		return new String(text);
	}

	public Integer getNullOrValue(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception exception) {
			return null;
		}
	}

	public String checkSeason(String season) {
		if ("LAST".equals(season)) {
			for (Seasons seasons : Seasons.values()) {
				season = seasons.getParam();
			}
		}
		return season;
	}

	public Date tomorrow(Date startDate) {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(startDate);
		c.add(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
		
	}

	public static Date nextDate(Date startDate, Integer stepFrequency) {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(startDate);
		c.add(Calendar.DAY_OF_MONTH, stepFrequency);
		return c.getTime();
	}

	public static List<DirettaFixture> shuffleList(List<DirettaFixture> direttaFixtures) {
		int count = direttaFixtures.size();
		List<DirettaFixture> list = new ArrayList<DirettaFixture>();
		
		Random random = new Random();
		for (int i = 0;i<count;i++){
			int index = random.nextInt(direttaFixtures.size());
			list.add(direttaFixtures.get(index));
			direttaFixtures.remove(index);
		}
		
		return list;
	}
	
	public static byte[] removeWhiteSpaces(byte[] bytes) {
		List<Byte> list = new ArrayList<Byte>();
		for (byte b : bytes) {
			if (b != -96 && b != 0){
				list.add(b);
			}
		}
		byte[] ret = new byte[list.size()];
		for (int i = 0;i<list.size();i++){
			ret[i] = list.get(i);
		}
		return ret;
	}

}
