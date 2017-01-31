package com.paolorizzo.predictor.services.response;

import java.io.Serializable;

import com.paolorizzo.predictor.dto.ProspectDto;

public class AddProspectResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2347589683799200398L;

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
