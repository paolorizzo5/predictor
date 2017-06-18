package com.paolorizzo.predictor.dto;

import java.util.List;

import com.paolorizzo.predictor.services.request.PlanFilterDto;
import com.paolorizzo.predictor.services.response.user.dto.UserDto;

public class MasanielloPlanDto {

	private String name;
	
	private UserDto userDto;
	
	private List<MasanielloDto> masanielloDtos;
	
	private List<PlanFilterDto> planFilterDtos;

	public MasanielloPlanDto(String name) {
		this.name = name;
	}

	public MasanielloPlanDto() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public List<MasanielloDto> getMasanielloDtos() {
		return masanielloDtos;
	}

	public void setMasanielloDtos(List<MasanielloDto> masanielloDtos) {
		this.masanielloDtos = masanielloDtos;
	}

	public List<PlanFilterDto> getPlanFilterDtos() {
		return planFilterDtos;
	}

	public void setPlanFilterDtos(List<PlanFilterDto> planFilterDtos) {
		this.planFilterDtos = planFilterDtos;
	}
	
	
}
