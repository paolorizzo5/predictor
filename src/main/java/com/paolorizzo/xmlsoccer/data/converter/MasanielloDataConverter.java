package com.paolorizzo.xmlsoccer.data.converter;

import com.paolorizzo.predictor.dto.MasanielloDto;
import com.paolorizzo.predictor.hibernate.model.Masaniello;

public class MasanielloDataConverter {

	public static MasanielloDto convert(Masaniello masaniello) {
		MasanielloDto masanielloDto = new MasanielloDto();
		masanielloDto.setUserDto(UserDataConverter.convert(masaniello.getUser()));
		masanielloDto.setAmount(masaniello.getAmount());
		masanielloDto.setEventToWin(masaniello.getEventToWin());
		masanielloDto.setMasanielloRoundDtos(MasanielloRoundDataConverter.convert(masaniello.getMasanielloRounds()));
		masanielloDto.setName(masaniello.getName());
		masanielloDto.setPercentage(masaniello.getPercentage());
		masanielloDto.setRounds(masaniello.getRounds());
		masanielloDto.setAverageQuote(masaniello.getAverageQuote());
		return masanielloDto;
	}

}
