package com.paolorizzo.predictor.services.response;

import java.io.Serializable;

import com.paolorizzo.predictor.dto.AccountDto;

public class DepositAccountResponse  extends SimpleAccountResponse implements Serializable{
	
	public DepositAccountResponse(boolean result, AccountDto account) {
		super(result,account);
	}

	public DepositAccountResponse() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5992241768637978488L;
	
	
	
	
	

}
