package com.paolorizzo.xmlsoccer.business;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.utils.SimpleUtils;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Standing;
import com.paolorizzo.xmlsoccer.dao.facade.StandingDao;

public class StandingBusiness {

	static Logger logger = LogManager.getLogger(StandingBusiness.class
			.getName());

	@Autowired
	private StandingDao standingDao;
	
	private SimpleUtils simpleUtils;

	public StandingBusiness(StandingDao standingDao,SimpleUtils simpleUtils) {
		super();
		this.standingDao = standingDao;
		this.simpleUtils = simpleUtils;
		
	}

	@Transactional(readOnly = false)
	public void insertAll(List<XmlSoccer_Standing> standinges) {
		for (XmlSoccer_Standing standing : standinges) {
			standingDao.insert(standing);
		}

	}

	public boolean isEmpty() {
		return standingDao.list().size() == 0;
	}

	@Transactional(readOnly = false)
	public void insert(XmlSoccer_Standing standing) {
		try {
			standingDao.insert(standing);
		} catch (Exception exception) {
			logger.error("error inserting standing", exception);
		}

	}

	public List<XmlSoccer_Standing> getStandingByLeagueAndSeason(String league,
			String season) {

		season = simpleUtils.checkSeason(season);

		return standingDao.getStandingByLeagueAndSeason(league, season);
	}

	@Transactional(readOnly = false)
	public void deleteByLeagueAndSeason(String league, String season) {

		season = simpleUtils.checkSeason(season);

		List<XmlSoccer_Standing> standingsToDelete = getStandingByLeagueAndSeason(
				league, season);
		for (XmlSoccer_Standing xmlSoccer_Standing : standingsToDelete) {
			standingDao.delete(xmlSoccer_Standing);
		}

	}

	public SimpleUtils getSimpleUtils() {
		return simpleUtils;
	}

	public void setSimpleUtils(SimpleUtils simpleUtils) {
		this.simpleUtils = simpleUtils;
	}

}
