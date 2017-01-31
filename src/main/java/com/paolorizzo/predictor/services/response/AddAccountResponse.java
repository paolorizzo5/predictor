package com.paolorizzo.predictor.services.response;

import java.io.Serializable;

import com.paolorizzo.predictor.dto.AccountDto;

public class AddAccountResponse extends SimpleAccountResponse implements Serializable{

	public AddAccountResponse(Boolean result, AccountDto account) {
		super(result, account);
		// TODO Auto-generated constructor stub
	}

	public AddAccountResponse() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6748371409318529739L;

	
}
