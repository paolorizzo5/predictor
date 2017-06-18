package com.paolorizzo.predictor.services.response;

import java.util.List;
import java.util.Set;

import com.paolorizzo.predictor.dto.MasanielloRoundDto;

public class GetMasanielloRoundsResponse {
	
	private List<MasanielloRoundDto> rounds;

	public List<MasanielloRoundDto> getRounds() {
		return rounds;
	}

	public void setRounds(List<MasanielloRoundDto> rounds) {
		this.rounds = rounds;
	}
	
	

}
