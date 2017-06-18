package com.paolorizzo.predictor.services.response;

import java.util.List;

import com.paolorizzo.predictor.dto.MasanielloPlanDto;

public class ListMasanielloPlanResponse {
	
	private List<MasanielloPlanDto> plans;

	public List<MasanielloPlanDto> getPlans() {
		return plans;
	}

	public void setPlans(List<MasanielloPlanDto> plans) {
		this.plans = plans;
	}

}
