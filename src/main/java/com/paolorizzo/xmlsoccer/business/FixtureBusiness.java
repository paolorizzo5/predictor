package com.paolorizzo.xmlsoccer.business;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pabloo99.xmlsoccer.api.dto.GetLiveScoreResultDto;
import com.paolorizzo.predictor.utils.SimpleUtils;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Fixture;
import com.paolorizzo.xmlsoccer.XmlSoccerClient;
import com.paolorizzo.xmlsoccer.dao.facade.FixtureDao;

public class FixtureBusiness {

	static Logger logger = LogManager
			.getLogger(FixtureBusiness.class.getName());

	@Autowired
	private FixtureDao fixtureDao;

	@Autowired
	private XmlSoccerClient xmlSoccerClient;

	public FixtureBusiness(FixtureDao fixtureDao) {
		super();
		this.fixtureDao = fixtureDao;
	}

	@Transactional(readOnly = false)
	public void insertAll(List<XmlSoccer_Fixture> fixturees) {
		for (XmlSoccer_Fixture fixture : fixturees) {
			fixtureDao.insert(fixture);
		}

	}

	public boolean isEmpty() {
		return fixtureDao.list().size() == 0;
	}

	@Transactional(readOnly = false)
	public void insert(XmlSoccer_Fixture fixture) {
		try {
			fixtureDao.insert(fixture);
		} catch (Exception exception) {
			logger.error("error inserting fixture", exception);
		}

	}

	public List<XmlSoccer_Fixture> getFixturesByLeagueAndSeason(String league,
			String season) {
		try {

			season = SimpleUtils.checkSeason(season);

			return fixtureDao.getFixturesByLeagueAndSeason(league, season);
		} catch (Exception exception) {
			logger.error("error getFixturesByLeagueAndSeason", exception);
			return null;
		}

	}

	public List<XmlSoccer_Fixture> getJustPlayedFixtures(Date startDate,
			Date endDate) {
		return fixtureDao.getFixturesByDates(startDate, endDate);
	}

	@Transactional(readOnly = false)
	public void delete(XmlSoccer_Fixture xmlSoccer_Fixture) {
		fixtureDao.delete(xmlSoccer_Fixture);
	}

	public List<XmlSoccer_Fixture> getNext7DaysFixtures() {
		Date startDate = new Date();
		Calendar c = new GregorianCalendar();
		c.add(Calendar.DAY_OF_MONTH, 7);
		return fixtureDao.getFixturesByDates(startDate, c.getTime());
	}

	public List<XmlSoccer_Fixture> getDailyFixtures() {
		Date startDate = new Date();
		Calendar c = new GregorianCalendar();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.add(Calendar.DAY_OF_MONTH, 1);

		return fixtureDao.getFixturesByDates(startDate, c.getTime());
	}

	public List<GetLiveScoreResultDto> getLivescores() {
		return xmlSoccerClient.getLivescores();
	}

}
