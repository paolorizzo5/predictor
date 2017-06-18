package com.paolorizzo.predictor.services.response;

import java.io.Serializable;

import com.paolorizzo.predictor.dto.MasanielloDto;
import com.paolorizzo.predictor.dto.MasanielloPlanDto;

public class CreateMasanielloResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3803015815936897709L;


	private Boolean result;


	public Boolean getResult() {
		return result;
	}


	public void setResult(Boolean result) {
		this.result = result;
	}
	
	

	

}
