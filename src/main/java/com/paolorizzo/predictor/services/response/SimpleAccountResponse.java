package com.paolorizzo.predictor.services.response;

import java.io.Serializable;

import com.paolorizzo.predictor.dto.AccountDto;

public class SimpleAccountResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7913498058329800619L;
	private Boolean result;
	private AccountDto account;
	
	
	
	public SimpleAccountResponse(Boolean result, AccountDto account) {
		super();
		this.result = result;
		this.account = account;
	}
	public SimpleAccountResponse() {
		
	}
	
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public AccountDto getAccount() {
		return account;
	}
	public void setAccount(AccountDto account) {
		this.account = account;
	}

}
