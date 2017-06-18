package com.paolorizzo.xmlsoccer.data.converter;

import java.util.ArrayList;
import java.util.List;

import com.paolorizzo.predictor.dto.MasanielloPlanDto;
import com.paolorizzo.predictor.hibernate.model.MasanielloPlan;

public class MasanielloPlanDataConverter {


	public static MasanielloPlanDto convert(MasanielloPlan masanielloPlan) {
		MasanielloPlanDto masanielloPlanDto = new MasanielloPlanDto();
		masanielloPlanDto.setMasanielloDtos(MasanielloDataConverter.convert(masanielloPlan.getMasaniellos(),false));
		masanielloPlanDto.setPlanFilterDtos(PlanFilterDataConverter.convert(masanielloPlan.getPlanFilters()));
		
		masanielloPlanDto.setName(masanielloPlan.getName());
		masanielloPlanDto.setUserDto(UserDataConverter.convert(masanielloPlan.getUser()));
		return masanielloPlanDto;
		
	}

	public static List<MasanielloPlanDto> convert(List<MasanielloPlan> plans) {
		List<MasanielloPlanDto> list = new ArrayList<MasanielloPlanDto>();
		
		for (MasanielloPlan masanielloPlan : plans) {
			list.add(convert(masanielloPlan));
		}	
		return list;
	}

}
