package com.paolorizzo.predictor.services.response;

import java.io.Serializable;

import com.paolorizzo.predictor.dto.MasanielloDto;

public class CreateMasanielloResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3803015815936897709L;
	private MasanielloDto masanielloDto;

	public MasanielloDto getMasanielloDto() {
		return masanielloDto;
	}

	public void setMasanielloDto(MasanielloDto masanielloDto) {
		this.masanielloDto = masanielloDto;
	}
	
	

}
