package com.paolorizzo.xmlsoccer.data.converter;

import java.math.BigDecimal;

import com.paolorizzo.predictor.dto.ProspectDto;
import com.paolorizzo.predictor.dto.ProspectElementDto;
import com.paolorizzo.predictor.hibernate.model.Prospect;

public class ProspectDataConverter {

	public static ProspectDto convert(Prospect prospect,BigDecimal liveAmount) {
		ProspectDto dto = new ProspectDto();
		dto.setDailyPercentageExpected(prospect.getDailyPercentageExpected());
		dto.setDuration(prospect.getDuration());
		dto.setInitialAmount(prospect.getInitialAmount());
		dto.setInsertDate(prospect.getInsertDate());
		dto.setName(prospect.getName());
		dto.setNextGoal(null);
		dto.setProspectElements(ProspectElementDataConverter.convert(prospect.getProspectElements()));
		
		if(liveAmount == null){
			dto.setNextGoal(prospect.getProspectElements().get(0).getExpectedGoal());
			dto.setNextGoalExpiration(prospect.getProspectElements().get(0).getEndDate().getTime());
		}else{
			for (ProspectElementDto prospectElementDto : dto.getProspectElements()) {
				if(liveAmount.compareTo(prospectElementDto.getExpectedGoal()) < 0 && dto.getNextGoal() == null){
					dto.setNextGoal(prospectElementDto.getExpectedGoal());
					dto.setNextGoalExpiration(prospectElementDto.getEndDate());
				}
			}	
		}
		
		
		
		
		return dto;
	}

}
