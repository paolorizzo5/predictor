package com.paolorizzo.predictor.services.response;

import java.util.List;

import com.github.pabloo99.xmlsoccer.api.dto.GetLiveScoreResultDto;

public class GetLivescoresResponse {

	private List<GetLiveScoreResultDto> livescores;

	public List<GetLiveScoreResultDto> getLivescores() {
		return livescores;
	}

	public void setLivescores(List<GetLiveScoreResultDto> livescores) {
		this.livescores = livescores;
	}

}
