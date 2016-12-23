package com.paolorizzo.predictor.services.response.fixture;

import com.paolorizzo.predictor.services.response.dto.TeamDto;

public class ViewOddsResponse {

	private TeamDto homeTeam;
	private TeamDto awayTeam;
	private String competitionId;

	public ViewOddsResponse() {
		// TODO Auto-generated constructor stub
	}

	

	public ViewOddsResponse(TeamDto homeTeam, TeamDto awayTeam,
			String competitionId) {
		super();
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.competitionId = competitionId;
	}



	public TeamDto getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(TeamDto homeTeam) {
		this.homeTeam = homeTeam;
	}

	public TeamDto getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(TeamDto awayTeam) {
		this.awayTeam = awayTeam;
	}



	public String getCompetitionId() {
		return competitionId;
	}



	public void setCompetitionId(String competitionId) {
		this.competitionId = competitionId;
	}
	
	

	
}
