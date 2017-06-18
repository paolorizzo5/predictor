package com.paolorizzo.predictor.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;


public class DateUtils {
	
	public DateUtils() {
		// TODO Auto-generated constructor stub
	}
	

	public static final SimpleDateFormat simpleDateFormat_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");

	public Date truncDate(Date date) {
		if(date != null){
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			
		}
		return date;
	}
	
	public Date truncTomorrowDate(Date date) {
		if(date != null){
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			return calendar.getTime();
		}else{
			return date;
		}
		
	}

	public Date truncYesterdayDate(Date date) {
		if(date != null){
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			return calendar.getTime();
		}else{
			return date;
		}
}
}
