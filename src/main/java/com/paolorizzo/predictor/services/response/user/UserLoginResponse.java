package com.paolorizzo.predictor.services.response.user;

import com.paolorizzo.predictor.services.response.user.dto.UserDto;

public class UserLoginResponse {

	private UserDto userDto;

	public UserLoginResponse() {
		// TODO Auto-generated constructor stub
	}

	public UserLoginResponse(UserDto userDto) {
		super();
		this.userDto = userDto;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

}
