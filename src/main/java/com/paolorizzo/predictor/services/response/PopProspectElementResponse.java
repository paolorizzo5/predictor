package com.paolorizzo.predictor.services.response;

import com.paolorizzo.predictor.dto.ProspectDto;

public class PopProspectElementResponse {
	
private ProspectDto prospect;
	
	private Boolean result;

	public ProspectDto getProspect() {
		return prospect;
	}

	public void setProspect(ProspectDto prospect) {
		this.prospect = prospect;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

}
