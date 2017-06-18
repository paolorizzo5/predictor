package com.paolorizzo.xmlsoccer.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pabloo99.xmlsoccer.api.dto.GetLiveScoreResultDto;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import com.paolorizzo.predictor.business.WebServiceApiDetailBusiness;
import com.paolorizzo.predictor.constants.WebServiceApiDetailConstants;
import com.paolorizzo.predictor.dto.WebServiceApiDetailDto;
import com.paolorizzo.predictor.utils.SimpleUtils;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Fixture;
import com.paolorizzo.xmlsoccer.XmlSoccerClient;
import com.paolorizzo.xmlsoccer.dao.facade.FixtureDao;

public class FixtureBusiness {

	Logger logger = LogManager.getLogger("root");
	
	@Autowired
	private FixtureDao fixtureDao;

	@Autowired
	private WebServiceApiDetailBusiness webServiceApiDetailBusiness;

	@Autowired
	private XmlSoccerServiceImpl xmlSoccerServiceBean;
	
	@Autowired
	private SimpleUtils simpleUtils;

	public FixtureBusiness(FixtureDao fixtureDao, WebServiceApiDetailBusiness webServiceApiDetailBusiness,
			XmlSoccerServiceImpl xmlSoccerServiceBean,SimpleUtils simpleUtils) {
		super();
		this.fixtureDao = fixtureDao;
		this.webServiceApiDetailBusiness = webServiceApiDetailBusiness;  
		this.xmlSoccerServiceBean = xmlSoccerServiceBean;
		this.simpleUtils = simpleUtils;
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

			season = simpleUtils.checkSeason(season);

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
		WebServiceApiDetailDto  webServiceApiDetailDto = webServiceApiDetailBusiness.canMakeCall(WebServiceApiDetailConstants.GET_LIVESCORE);
		
		if(webServiceApiDetailDto.getCanMakeCall()){
			return xmlSoccerServiceBean.getLiveScore();
		}else{
			logger.debug("impossibile effettuare la chiamata a " + WebServiceApiDetailConstants.GET_LIVESCORE);
			return new ArrayList<GetLiveScoreResultDto>();
		}
		
	}

	public SimpleUtils getSimpleUtils() {
		return simpleUtils;
	}

	public void setSimpleUtils(SimpleUtils simpleUtils) {
		this.simpleUtils = simpleUtils;
	}
	
	

}
