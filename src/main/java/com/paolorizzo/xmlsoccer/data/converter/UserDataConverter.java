package com.paolorizzo.xmlsoccer.data.converter;

import com.paolorizzo.predictor.hibernate.model.User;
import com.paolorizzo.predictor.services.response.user.dto.UserDto;

public class UserDataConverter {

	public static UserDto convert(User user) {
		UserDto dto = new UserDto();
		dto.setEmail(user.getEmail());
		dto.setPortFolioAmount(user.getPortFolioAmount());
		return dto;
		
	}

}
