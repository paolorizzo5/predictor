package com.paolorizzo.predictor.business;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.dao.facade.FixtureDao;
import com.paolorizzo.predictor.enums.HomeAwayEnum;
import com.paolorizzo.predictor.hibernate.model.Competition;
import com.paolorizzo.predictor.hibernate.model.Fixture;
import com.paolorizzo.predictor.hibernate.model.Team;

public class FixtureBusiness {

	@Autowired
	private FixtureDao fixtureDao;

	public FixtureBusiness() {
		// TODO Auto-generated constructor stub
	}

	public FixtureBusiness(FixtureDao fixtureDao) {
		super();
		this.fixtureDao = fixtureDao;
	}

	public FixtureDao getFixtureDao() {
		return fixtureDao;
	}

	public void setFixtureDao(FixtureDao fixtureDao) {
		this.fixtureDao = fixtureDao;
	}

	public List<Fixture> findByStatusAndDate(String status, long today,
			long tomorrow) {
		return fixtureDao.findByStatusAndDate(status, today, tomorrow);

	}

	@Transactional(readOnly = false)
	public void update(Fixture fixture) {
		fixtureDao.update(fixture);

	}

	public List<Fixture> list() {
		return fixtureDao.list();
	}

	public List<Fixture> getByTeam(Team team, Competition competition,
			HomeAwayEnum atHome) {
		return fixtureDao.getByTeam(team, competition, atHome);

	}

	public List<Fixture> findByStatusAndCompetition(String status, String id,Boolean hasLimit,Integer numRecords) {
		// TODO Auto-generated method stub
		return fixtureDao.findByStatusAndCompetition(status, id,hasLimit,numRecords);
	}

	public List<Fixture> findDaily() {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		Calendar calendarEnd = new GregorianCalendar();
		calendarEnd.set(Calendar.HOUR_OF_DAY, 0);
		calendarEnd.set(Calendar.MINUTE, 0);
		calendarEnd.set(Calendar.SECOND, 0);
		
		calendarEnd.add(Calendar.DAY_OF_MONTH, 1);
		
		return fixtureDao.findDaily(calendar.getTimeInMillis(),calendarEnd.getTimeInMillis());
	}
}
