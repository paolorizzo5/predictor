package com.paolorizzo.xmlsoccer.data.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.paolorizzo.predictor.dto.ProspectElementDto;
import com.paolorizzo.predictor.hibernate.model.ProspectElement;

public class ProspectElementDataConverter {

	public static List<ProspectElementDto> convert(List<ProspectElement> prospectElements) {
		List<ProspectElementDto> list = new ArrayList<ProspectElementDto>();
		BigDecimal previousExpectedGoal = new BigDecimal(0);
		
		if(prospectElements != null){
			
			for (int i = 0;i<prospectElements.size(); i++){
				ProspectElementDto dto = new ProspectElementDto();
				dto.setEndDate(prospectElements.get(i).getEndDate().getTime());
				dto.setExpectedGoal(prospectElements.get(i).getExpectedGoal());
				dto.setIncremental(prospectElements.get(i).getIncremental());
				dto.setStartDate(prospectElements.get(i).getStartDate().getTime());
				if(prospectElements.get(i).getTerminationDate() != null){
					dto.setTerminationDate(prospectElements.get(i).getTerminationDate().getTime());
				}
				dto.setPreviousExpectedGoal(previousExpectedGoal);
				previousExpectedGoal = dto.getExpectedGoal();
				dto.setCurrent(prospectElements.get(i).getStartDate().before(new Date()) &&prospectElements.get(i).getEndDate().after(new Date()));
				list.add(dto);
			}
		}
		
		
		return list;
	}

}
