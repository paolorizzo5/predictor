package com.paolorizzo.predictor.services.response.dto;


public class TeamStatsDto {
	
	
	private FullStatisticDto home;
	private FullStatisticDto away;
	private FullStatisticDto overall;


	public TeamStatsDto() {
		home = new FullStatisticDto();
		away = new FullStatisticDto();
		overall = new FullStatisticDto();
	}
	
	


	public TeamStatsDto(FullStatisticDto home, FullStatisticDto away,
			FullStatisticDto overall) {
		super();
		this.home = home;
		this.away = away;
		this.overall = overall;
	}




	public FullStatisticDto getHome() {
		return home;
	}


	public void setHome(FullStatisticDto home) {
		this.home = home;
	}


	public FullStatisticDto getAway() {
		return away;
	}


	public void setAway(FullStatisticDto away) {
		this.away = away;
	}


	public FullStatisticDto getOverall() {
		return overall;
	}


	public void setOverall(FullStatisticDto overall) {
		this.overall = overall;
	}

	
	

	
	
	

}
