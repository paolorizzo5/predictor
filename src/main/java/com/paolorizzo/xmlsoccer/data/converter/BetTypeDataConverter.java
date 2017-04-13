package com.paolorizzo.xmlsoccer.data.converter;

import java.util.ArrayList;
import java.util.List;

import com.paolorizzo.predictor.dto.BetTypeDto;
import com.paolorizzo.predictor.hibernate.model.BetType;

public class BetTypeDataConverter {

	public static List<BetTypeDto> convert(List<BetType> betTypes) {
		List<BetTypeDto> list = new ArrayList<BetTypeDto>();
		
		for (BetType betType : betTypes) {
			BetTypeDto dto = new BetTypeDto();
			dto.setName(betType.getName());
			list.add(dto);
		}
		
		return list;
	}

}
