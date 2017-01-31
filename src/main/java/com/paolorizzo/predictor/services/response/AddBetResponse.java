package com.paolorizzo.predictor.services.response;

import java.io.Serializable;

import com.paolorizzo.predictor.dto.AccountDto;

public class AddBetResponse extends SimpleAccountResponse implements Serializable{
	
	public AddBetResponse(Boolean result, AccountDto account) {
		super(result, account);
		// TODO Auto-generated constructor stub
	}

	public AddBetResponse() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2570284642003988850L;
	

}
