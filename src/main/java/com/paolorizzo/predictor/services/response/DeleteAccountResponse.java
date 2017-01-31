package com.paolorizzo.predictor.services.response;

import java.io.Serializable;

import com.paolorizzo.predictor.dto.AccountDto;


public class DeleteAccountResponse  extends SimpleAccountResponse implements Serializable{
	
	public DeleteAccountResponse(Boolean result, AccountDto account) {
		super(result, account);
		// TODO Auto-generated constructor stub
	}

	public DeleteAccountResponse() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3153770793918265360L;
	

}
