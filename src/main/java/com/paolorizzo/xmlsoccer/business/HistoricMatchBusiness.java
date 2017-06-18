package com.paolorizzo.xmlsoccer.business;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.dto.FixturePreviewDto;
import com.paolorizzo.predictor.dto.HistoricMatchDto;
import com.paolorizzo.predictor.utils.SimpleUtils;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_HistoricMatch;
import com.paolorizzo.xmlsoccer.dao.facade.HistoricMatchDao;
import com.paolorizzo.xmlsoccer.data.converter.HistoricMatchDataConverter;

public class HistoricMatchBusiness {

	static Logger logger = LogManager.getLogger(HistoricMatchBusiness.class
			.getName());

	@Autowired
	private HistoricMatchDao historicMatchDao;
	
	@Autowired
	private SimpleUtils simpleUtils;
	

	public HistoricMatchBusiness(HistoricMatchDao historicMatchDao,SimpleUtils simpleUtils) {
		super();
		this.historicMatchDao = historicMatchDao;
		this.simpleUtils = simpleUtils;
	}

	@Transactional(readOnly = false)
	public void insertAll(List<XmlSoccer_HistoricMatch> historicMatches) {
		for (XmlSoccer_HistoricMatch historicMatch : historicMatches) {
			historicMatchDao.insert(historicMatch);
		}

	}

	public boolean isEmpty() {
		return historicMatchDao.list().size() == 0;
	}

	@Transactional(readOnly = false)
	public void insert(XmlSoccer_HistoricMatch historicMatch) {
		historicMatchDao.insert(historicMatch);
	}

	public List<HistoricMatchDto> getHistoricMatchesByLeagueAndSeason(
			String league, String season) {

		season = simpleUtils.checkSeason(season);

		List<XmlSoccer_HistoricMatch> historicMatches = historicMatchDao
				.getHistoricMatchesByLeagueAndSeason(league, season);

		List<HistoricMatchDto> list = HistoricMatchDataConverter
				.convert(historicMatches);

		Collections.sort(list);
		return list;
	}

	public FixturePreviewDto getFixturePreview(String homeTeamId,
			String awayTeamId) {

		List<XmlSoccer_HistoricMatch> historicMatches = historicMatchDao
				.getHistoricMatchesByTeams(homeTeamId, awayTeamId);

		return setFixturePreviewByHistoricMatches(historicMatches);

	}

	private FixturePreviewDto setFixturePreviewByHistoricMatches(
			List<XmlSoccer_HistoricMatch> historicMatches) {

		FixturePreviewDto fixturePreviewDto = new FixturePreviewDto();

		fixturePreviewDto.setGamesPlayed(historicMatches.size());

		// gestione delle statistiche
		for (XmlSoccer_HistoricMatch xmlSoccer_HistoricMatch : historicMatches) {
			if (xmlSoccer_HistoricMatch.getHomeGoals() > xmlSoccer_HistoricMatch
					.getAwayGoals()) {
				fixturePreviewDto
						.setHomeWins(fixturePreviewDto.getHomeWins() + 1);
			} else if (xmlSoccer_HistoricMatch.getHomeGoals() == xmlSoccer_HistoricMatch
					.getAwayGoals()) {
				fixturePreviewDto.setDraws(fixturePreviewDto.getDraws() + 1);
			} else {
				fixturePreviewDto
						.setAwayWins(fixturePreviewDto.getAwayWins() + 1);
			}

			switch (xmlSoccer_HistoricMatch.getHomeGoals()
					+ xmlSoccer_HistoricMatch.getAwayGoals()) {
			case 0:
				fixturePreviewDto
						.setUnder05(fixturePreviewDto.getUnder05() + 1);
				fixturePreviewDto
						.setUnder15(fixturePreviewDto.getUnder15() + 1);
				fixturePreviewDto
						.setUnder25(fixturePreviewDto.getUnder25() + 1);
				fixturePreviewDto
						.setUnder35(fixturePreviewDto.getUnder35() + 1);
				fixturePreviewDto
						.setUnder45(fixturePreviewDto.getUnder45() + 1);
				fixturePreviewDto
						.setUnder55(fixturePreviewDto.getUnder55() + 1);

				break;
			case 1:
				fixturePreviewDto.setOver05(fixturePreviewDto.getOver05() + 1);
				fixturePreviewDto
						.setUnder15(fixturePreviewDto.getUnder15() + 1);
				fixturePreviewDto
						.setUnder25(fixturePreviewDto.getUnder25() + 1);
				fixturePreviewDto
						.setUnder35(fixturePreviewDto.getUnder35() + 1);
				fixturePreviewDto
						.setUnder45(fixturePreviewDto.getUnder45() + 1);
				fixturePreviewDto
						.setUnder55(fixturePreviewDto.getUnder55() + 1);
				break;
			case 2:
				fixturePreviewDto.setOver05(fixturePreviewDto.getOver05() + 1);
				fixturePreviewDto.setOver15(fixturePreviewDto.getOver15() + 1);
				fixturePreviewDto
						.setUnder25(fixturePreviewDto.getUnder25() + 1);
				fixturePreviewDto
						.setUnder35(fixturePreviewDto.getUnder35() + 1);
				fixturePreviewDto
						.setUnder45(fixturePreviewDto.getUnder45() + 1);
				fixturePreviewDto
						.setUnder55(fixturePreviewDto.getUnder55() + 1);
				break;
			case 3:
				fixturePreviewDto.setOver05(fixturePreviewDto.getOver05() + 1);
				fixturePreviewDto.setOver15(fixturePreviewDto.getOver15() + 1);
				fixturePreviewDto.setOver25(fixturePreviewDto.getOver25() + 1);
				fixturePreviewDto
						.setUnder35(fixturePreviewDto.getUnder35() + 1);
				fixturePreviewDto
						.setUnder45(fixturePreviewDto.getUnder45() + 1);
				fixturePreviewDto
						.setUnder55(fixturePreviewDto.getUnder55() + 1);
				break;
			case 4:
				fixturePreviewDto.setOver05(fixturePreviewDto.getOver05() + 1);
				fixturePreviewDto.setOver15(fixturePreviewDto.getOver15() + 1);
				fixturePreviewDto.setOver25(fixturePreviewDto.getOver25() + 1);
				fixturePreviewDto.setOver35(fixturePreviewDto.getOver35() + 1);
				fixturePreviewDto
						.setUnder45(fixturePreviewDto.getUnder45() + 1);
				fixturePreviewDto
						.setUnder55(fixturePreviewDto.getUnder55() + 1);
				break;
			case 5:
				fixturePreviewDto.setOver05(fixturePreviewDto.getOver05() + 1);
				fixturePreviewDto.setOver15(fixturePreviewDto.getOver15() + 1);
				fixturePreviewDto.setOver25(fixturePreviewDto.getOver25() + 1);
				fixturePreviewDto.setOver35(fixturePreviewDto.getOver35() + 1);
				fixturePreviewDto.setOver45(fixturePreviewDto.getOver45() + 1);
				fixturePreviewDto
						.setUnder55(fixturePreviewDto.getUnder55() + 1);
				break;
			default:
				fixturePreviewDto.setOver05(fixturePreviewDto.getOver05() + 1);
				fixturePreviewDto.setOver15(fixturePreviewDto.getOver15() + 1);
				fixturePreviewDto.setOver25(fixturePreviewDto.getOver25() + 1);
				fixturePreviewDto.setOver35(fixturePreviewDto.getOver35() + 1);
				fixturePreviewDto.setOver45(fixturePreviewDto.getOver45() + 1);
				fixturePreviewDto.setOver55(fixturePreviewDto.getOver55() + 1);
				break;
			}

			if (xmlSoccer_HistoricMatch.getHomeGoals() > 0
					&& xmlSoccer_HistoricMatch.getAwayGoals() > 0) {
				fixturePreviewDto.setGoal(fixturePreviewDto.getGoal() + 1);
			} else {
				fixturePreviewDto.setNoGoal(fixturePreviewDto.getNoGoal() + 1);
			}

			if (xmlSoccer_HistoricMatch.getHomeGoals() > 0) {
				fixturePreviewDto
						.setHomeGoal(fixturePreviewDto.getHomeGoal() + 1);
			}
			if (xmlSoccer_HistoricMatch.getAwayGoals() > 0) {
				fixturePreviewDto
						.setAwayGoal(fixturePreviewDto.getAwayGoal() + 1);
			}

			switch (xmlSoccer_HistoricMatch.getHomeGoals()
					- xmlSoccer_HistoricMatch.getAwayGoals()) {
			case 10:
			case 9:
			case 8:
			case 7:
			case 6:
			case 5:
			case 4:
			case 3:
				fixturePreviewDto.setHandicapHome2_1(fixturePreviewDto
						.getHandicapHome2_1() + 1);
				fixturePreviewDto.setHandicapHome1_1(fixturePreviewDto
						.getHandicapHome1_1() + 1);
				fixturePreviewDto.setHandicapAway1_1(fixturePreviewDto
						.getHandicapAway1_1() + 1);
				fixturePreviewDto.setHandicapAway2_1(fixturePreviewDto
						.getHandicapAway2_1() + 1);
				break;
			case 2:
				fixturePreviewDto.setHandicapHome2_X(fixturePreviewDto
						.getHandicapHome2_X() + 1);
				fixturePreviewDto.setHandicapHome1_1(fixturePreviewDto
						.getHandicapHome1_1() + 1);
				fixturePreviewDto.setHandicapAway1_1(fixturePreviewDto
						.getHandicapAway1_1() + 1);
				fixturePreviewDto.setHandicapAway2_1(fixturePreviewDto
						.getHandicapAway2_1() + 1);
				break;
			case 1:
				fixturePreviewDto.setHandicapHome2_2(fixturePreviewDto
						.getHandicapHome2_2() + 1);
				fixturePreviewDto.setHandicapHome1_X(fixturePreviewDto
						.getHandicapHome1_X() + 1);
				fixturePreviewDto.setHandicapAway1_1(fixturePreviewDto
						.getHandicapAway1_1() + 1);
				fixturePreviewDto.setHandicapAway2_1(fixturePreviewDto
						.getHandicapAway2_1() + 1);
				break;
			case 0:
				fixturePreviewDto.setHandicapHome2_2(fixturePreviewDto
						.getHandicapHome2_2() + 1);
				fixturePreviewDto.setHandicapHome1_2(fixturePreviewDto
						.getHandicapHome1_2() + 1);
				fixturePreviewDto.setHandicapAway1_1(fixturePreviewDto
						.getHandicapAway1_1() + 1);
				fixturePreviewDto.setHandicapAway2_1(fixturePreviewDto
						.getHandicapAway2_1() + 1);
				break;
			case -1:
				fixturePreviewDto.setHandicapHome2_2(fixturePreviewDto
						.getHandicapHome2_2() + 1);
				fixturePreviewDto.setHandicapHome1_2(fixturePreviewDto
						.getHandicapHome1_2() + 1);
				fixturePreviewDto.setHandicapAway1_X(fixturePreviewDto
						.getHandicapAway1_X() + 1);
				fixturePreviewDto.setHandicapAway2_1(fixturePreviewDto
						.getHandicapAway2_1() + 1);
				break;
			case -2:
				fixturePreviewDto.setHandicapHome2_2(fixturePreviewDto
						.getHandicapHome2_2() + 1);
				fixturePreviewDto.setHandicapHome1_2(fixturePreviewDto
						.getHandicapHome1_2() + 1);
				fixturePreviewDto.setHandicapAway1_2(fixturePreviewDto
						.getHandicapAway1_2() + 1);
				fixturePreviewDto.setHandicapAway2_X(fixturePreviewDto
						.getHandicapAway2_X() + 1);
				break;
			default:
				fixturePreviewDto.setHandicapHome2_2(fixturePreviewDto
						.getHandicapHome2_2() + 1);
				fixturePreviewDto.setHandicapHome1_2(fixturePreviewDto
						.getHandicapHome1_2() + 1);
				fixturePreviewDto.setHandicapAway1_2(fixturePreviewDto
						.getHandicapAway1_2() + 1);
				fixturePreviewDto.setHandicapAway2_2(fixturePreviewDto
						.getHandicapAway2_2() + 1);
				break;
			}

		}

		fixturePreviewDto.setPreviousMatches(HistoricMatchDataConverter
				.convert(historicMatches));

		Collections.sort(fixturePreviewDto.getPreviousMatches());

		return fixturePreviewDto;
	}

	public List<XmlSoccer_HistoricMatch> getLastMatches(String teamId,Integer number) {
		return historicMatchDao.getLastMatches(teamId,number);
	}

	public FixturePreviewDto getSeasonStats(String homeTeamId,
			String awayTeamId, String league, String season) {

		season = simpleUtils.checkSeason(season);

		return setFixturePreviewByHistoricMatches(historicMatchDao
				.getSeasonStats(homeTeamId, awayTeamId, league, season));

	}

	public SimpleUtils getSimpleUtils() {
		return simpleUtils;
	}

	public void setSimpleUtils(SimpleUtils simpleUtils) {
		this.simpleUtils = simpleUtils;
	}

}
