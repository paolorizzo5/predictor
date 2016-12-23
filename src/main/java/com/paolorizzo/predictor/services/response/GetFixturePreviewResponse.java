package com.paolorizzo.predictor.services.response;

import java.util.List;

import com.paolorizzo.predictor.dto.FixturePreviewDto;
import com.paolorizzo.predictor.dto.HistoricMatchDto;
import com.paolorizzo.predictor.dto.TeamDto;

public class GetFixturePreviewResponse {

	private FixturePreviewDto fixturePreview;
	private List<HistoricMatchDto> last5Home;
	private List<HistoricMatchDto> last5Away;

	private FixturePreviewDto fixturePreviewThisSeason;

	private TeamDto homeTeam;
	private TeamDto awayTeam;

	public FixturePreviewDto getFixturePreviewDto() {
		return fixturePreview;
	}

	public void setFixturePreviewDto(FixturePreviewDto fixturePreview) {
		this.fixturePreview = fixturePreview;
	}

	public List<HistoricMatchDto> getLast5Away() {
		return last5Away;
	}

	public void setLast5Away(List<HistoricMatchDto> last5Away) {
		this.last5Away = last5Away;
	}

	public List<HistoricMatchDto> getLast5Home() {
		return last5Home;
	}

	public void setLast5Home(List<HistoricMatchDto> last5Home) {
		this.last5Home = last5Home;
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

	public FixturePreviewDto getFixturePreview() {
		return fixturePreview;
	}

	public void setFixturePreview(FixturePreviewDto fixturePreview) {
		this.fixturePreview = fixturePreview;
	}

	public FixturePreviewDto getFixturePreviewThisSeason() {
		return fixturePreviewThisSeason;
	}

	public void setFixturePreviewThisSeason(
			FixturePreviewDto fixturePreviewThisSeason) {
		this.fixturePreviewThisSeason = fixturePreviewThisSeason;
	}

}
