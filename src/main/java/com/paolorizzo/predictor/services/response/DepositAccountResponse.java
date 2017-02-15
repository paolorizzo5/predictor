package com.paolorizzo.predictor.services.response;

import java.io.Serializable;

import com.paolorizzo.predictor.dto.AccountDto;
import com.paolorizzo.predictor.services.response.user.dto.UserDto;

public class DepositAccountResponse  extends SimpleAccountResponse implements Serializable{
	
	private UserDto user;
	
	public DepositAccountResponse(boolean result, AccountDto account,UserDto user) {
		super(result,account);
		this.setUser(user);
	}

	public DepositAccountResponse() {
		super();
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5992241768637978488L;
	
	
	
	
	

}
