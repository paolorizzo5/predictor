package com.paolorizzo.xmlsoccer.data.converter;

import java.util.ArrayList;
import java.util.List;

import com.paolorizzo.predictor.dto.MasanielloDto;
import com.paolorizzo.predictor.dto.MasanielloPlanDto;
import com.paolorizzo.predictor.hibernate.model.Masaniello;
import com.paolorizzo.predictor.hibernate.model.MasanielloRound;

public class MasanielloDataConverter {

	public static MasanielloDto convert(Masaniello masaniello, boolean getRounds) {
		MasanielloDto masanielloDto = new MasanielloDto();
		
		masanielloDto.setAmount(masaniello.getAmount());
		masanielloDto.setEventToWin(masaniello.getEventToWin());
		masanielloDto.setMasanielloRoundDtos(MasanielloRoundDataConverter.convert(masaniello.getMasanielloRounds()));
		masanielloDto.setName(masaniello.getName());
		masanielloDto.setPercentage(masaniello.getPercentage());
		if(getRounds){
			masanielloDto.setRounds(masaniello.getRounds());
		}
		masanielloDto.setAverageQuote(masaniello.getAverageQuote());
		
		
		if(masaniello.getMasanielloRounds() != null){
			for (MasanielloRound round : masaniello.getMasanielloRounds()) {
				masanielloDto.setFinalAmount(round.getFinalAmount());
			}
			
			if(masanielloDto.getFinalAmount().compareTo(masanielloDto.getAmount()) > 0){
				masanielloDto.setPanelClass("panel-greensea");
			}else{
				masanielloDto.setPanelClass("panel-danger");
			}
			
		}
		masanielloDto.setMasanielloPlanDto(new MasanielloPlanDto(masaniello.getMasanielloPlan().getName()));
		masanielloDto.setMasanielloRoundDtos(null);
		masanielloDto.setShowDetail(false);
		masanielloDto.setId(masaniello.getId());
		return masanielloDto;
	}

	public static List<MasanielloDto> convert(List<Masaniello> masaniellos, boolean getRounds) {
		
		List<MasanielloDto> list = new ArrayList<MasanielloDto>();
		for (Masaniello masaniello : masaniellos) {
			list.add(convert(masaniello,getRounds));		
		}
		
		return list;
	}

}
