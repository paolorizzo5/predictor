package com.paolorizzo.xmlsoccer.data.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.paolorizzo.predictor.dto.MasanielloRoundDto;
import com.paolorizzo.predictor.hibernate.model.MasanielloRound;

public class MasanielloRoundDataConverter {

	public static List<MasanielloRoundDto> convert(List<MasanielloRound> masanielloRounds) {
		List<MasanielloRoundDto> list = new ArrayList<MasanielloRoundDto>();
		for (MasanielloRound round : masanielloRounds) {
			MasanielloRoundDto dto = new MasanielloRoundDto();
			dto.setFinalAmount(round.getFinalAmount());
			dto.setInitialAmount(round.getInitialAmount());
			dto.setMatches(round.getMatches());
			dto.setRoundId(round.getRoundId());
			dto.setSuccess(round.getSuccess());
			dto.setPercentageInvested(round.getPercentageInvested());
			dto.setFixtures(DirettaFixtureConverter.convert(round.getDirettaFixtures()));
			list.add(dto);
		}
		
		Collections.sort(list);
		return list;
	}

}
