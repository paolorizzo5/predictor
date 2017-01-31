package com.paolorizzo.xmlsoccer.data.converter;

import com.paolorizzo.predictor.dto.ProspectDto;
import com.paolorizzo.predictor.hibernate.model.Prospect;

public class ProspectDataConverter {

	public static ProspectDto convert(Prospect prospect) {
		ProspectDto dto = new ProspectDto();
		dto.setDailyPercentageExpected(prospect.getDailyPercentageExpected());
		dto.setDuration(prospect.getDuration());
		dto.setInitialAmount(prospect.getInitialAmount());
		dto.setInsertDate(prospect.getInsertDate());
		dto.setName(prospect.getName());
		
		dto.setProspectElements(ProspectElementDataConverter.convert(prospect.getProspectElements()));
		return dto;
	}

}
