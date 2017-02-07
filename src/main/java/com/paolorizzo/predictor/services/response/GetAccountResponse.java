package com.paolorizzo.predictor.services.response;

import java.io.Serializable;

import com.paolorizzo.predictor.dto.AccountDto;

public class GetAccountResponse  extends SimpleAccountResponse implements Serializable{
	
	public GetAccountResponse(Boolean result, AccountDto account) {
		super(result, account);
		// TODO Auto-generated constructor stub
	}

	public GetAccountResponse() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3891607871375742039L;

}
