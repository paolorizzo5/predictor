package com.paolorizzo.predictor.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paolorizzo.predictor.dto.WebServiceApiDetailDto;

public class ThreadUtils {

	static Logger logger = LogManager.getLogger("root");

	
	public static void getSomeSleep(WebServiceApiDetailDto dto) {
		try {
			logger.debug("get some sleep" + dto.getName() + " for " + dto.getCallFrequency()  / 1000 + " seconds");
			Thread.sleep(dto.getCallFrequency());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
