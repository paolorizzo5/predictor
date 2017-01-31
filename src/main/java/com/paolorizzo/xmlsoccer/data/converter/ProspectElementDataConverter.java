package com.paolorizzo.xmlsoccer.data.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.paolorizzo.predictor.dto.ProspectElementDto;
import com.paolorizzo.predictor.hibernate.model.ProspectElement;

public class ProspectElementDataConverter {

	public static List<ProspectElementDto> convert(List<ProspectElement> prospectElements) {
		List<ProspectElementDto> list = new ArrayList<ProspectElementDto>();
		BigDecimal previousExpectedGoal = new BigDecimal(0);
		if(prospectElements != null){
			for (ProspectElement prospectElement : prospectElements) {
				ProspectElementDto dto = new ProspectElementDto();
				dto.setEndDate(prospectElement.getEndDate().getTime());
				dto.setExpectedGoal(prospectElement.getExpectedGoal());
				dto.setIncremental(prospectElement.getIncremental());
				dto.setStartDate(prospectElement.getStartDate().getTime());
				dto.setLiveAmount(prospectElement.getLiveAmount());
				dto.setPreviousExpectedGoal(previousExpectedGoal);
				previousExpectedGoal = dto.getExpectedGoal();
				list.add(dto);
			}	
		}
		
		
		return list;
	}

}
