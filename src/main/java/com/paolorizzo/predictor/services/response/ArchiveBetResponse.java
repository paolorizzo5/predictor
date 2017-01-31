package com.paolorizzo.predictor.services.response;

import java.io.Serializable;

import com.paolorizzo.predictor.dto.AccountDto;

public class ArchiveBetResponse  extends SimpleAccountResponse implements Serializable{
	
	public ArchiveBetResponse(Boolean result, AccountDto account) {
		super(result, account);
		// TODO Auto-generated constructor stub
	}

	public ArchiveBetResponse() {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1122399670817222003L;


}
