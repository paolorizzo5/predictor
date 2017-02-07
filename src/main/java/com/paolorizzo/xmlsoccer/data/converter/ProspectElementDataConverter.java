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
			
			for (int i = 0;i<prospectElements.size(); i++){
				ProspectElementDto dto = new ProspectElementDto();
				dto.setEndDate(prospectElements.get(i).getEndDate().getTime());
				dto.setExpectedGoal(prospectElements.get(i).getExpectedGoal());
				dto.setIncremental(prospectElements.get(i).getIncremental());
				dto.setStartDate(prospectElements.get(i).getStartDate().getTime());
				dto.setLiveAmount(prospectElements.get(i).getLiveAmount());
				dto.setPreviousExpectedGoal(previousExpectedGoal);
				previousExpectedGoal = dto.getExpectedGoal();
				if(i < prospectElements.size() -1){
					dto.setCurrent(prospectElements.get(i).getLiveAmount() != null && prospectElements.get(i+1).getLiveAmount() == null);
				}else{
					dto.setCurrent(prospectElements.get(i).getLiveAmount() != null);
				}
				list.add(dto);
			}
		}
		
		
		return list;
	}

}
