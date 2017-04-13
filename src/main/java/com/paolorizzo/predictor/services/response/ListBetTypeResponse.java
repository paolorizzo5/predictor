package com.paolorizzo.predictor.services.response;

import java.util.List;

import com.paolorizzo.predictor.dto.BetTypeDto;
public class ListBetTypeResponse {
	
	List<BetTypeDto> bettypes;

	public List<BetTypeDto> getBettypes() {
		return bettypes;
	}

	public void setBettypes(List<BetTypeDto> bettypes) {
		this.bettypes = bettypes;
	}
	
	

}
