package com.paolorizzo.xmlsoccer.data.converter;

import java.util.ArrayList;
import java.util.List;

import com.paolorizzo.predictor.dto.BetDto;
import com.paolorizzo.predictor.hibernate.model.Bet;

public class BetDataConverter {

	public static List<BetDto> convert(List<Bet> bets) {
		List<BetDto> list = new ArrayList<BetDto>();
		
		for (Bet bet : bets) {
			BetDto dto = new BetDto();
			dto.setAmount(bet.getAmount());
			dto.setBetStatus(bet.getBetStatus());
			dto.setBettypeDescription(bet.getBettypeDescription());
			dto.setEventDescription(bet.getEventDescription());
			dto.setInsertDate(bet.getInsertDate().getTime());
			dto.setMoltiplicator(bet.getMoltiplicator());
			list.add(dto);
		}
		return list;
	}

}
