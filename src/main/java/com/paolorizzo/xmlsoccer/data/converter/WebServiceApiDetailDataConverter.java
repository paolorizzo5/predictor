package com.paolorizzo.xmlsoccer.data.converter;

import com.paolorizzo.predictor.dto.WebServiceApiDetailDto;
import com.paolorizzo.predictor.hibernate.model.WebServiceApiDetail;

public class WebServiceApiDetailDataConverter {

	public static WebServiceApiDetailDto convert(WebServiceApiDetail webServiceApiDetail) {
		WebServiceApiDetailDto dto = new WebServiceApiDetailDto();
		dto.setCallFrequency(webServiceApiDetail.getCallFrequency());
		dto.setLastExecutionDate(dto.getLastExecutionDate());
		dto.setCanMakeCall(System.currentTimeMillis() - webServiceApiDetail.getLastExecutionDate().getTime() > webServiceApiDetail.getCallFrequency());
		dto.setName(webServiceApiDetail.getName());
		dto.setNumberOfCalls(webServiceApiDetail.getNumberOfCalls());
		return dto;
	}

}
