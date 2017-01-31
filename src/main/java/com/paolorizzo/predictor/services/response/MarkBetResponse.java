package com.paolorizzo.predictor.services.response;

import java.io.Serializable;

import com.paolorizzo.predictor.dto.AccountDto;

public class MarkBetResponse  extends SimpleAccountResponse implements Serializable {

	public MarkBetResponse(Boolean result, AccountDto account) {
		super(result, account);
		// TODO Auto-generated constructor stub
	}

	public MarkBetResponse() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4526372739721712822L;
	

}
