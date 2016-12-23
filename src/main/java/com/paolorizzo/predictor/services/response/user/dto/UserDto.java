package com.paolorizzo.predictor.services.response.user.dto;

import java.io.Serializable;

import com.paolorizzo.predictor.hibernate.model.User;

public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4465963414487195199L;

	private String email;

	private String password;

	public UserDto() {
		// TODO Auto-generated constructor stub
	}

	public UserDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;

	}

	public UserDto(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static UserDto fromUser(User user) {
		UserDto userDto = new UserDto();
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		return userDto;
	}

}
