package com.paolorizzo.xmlsoccer.dao.facade;

import java.util.Date;
import java.util.List;

import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Fixture;

public interface FixtureDao {

	void insert(XmlSoccer_Fixture xmlSoccer_Fixture);

	List<XmlSoccer_Fixture> list();

	void delete(XmlSoccer_Fixture xmlSoccer_Fixture);

	void update(XmlSoccer_Fixture xmlSoccer_Fixture);

	XmlSoccer_Fixture getById(String id);

	List<XmlSoccer_Fixture> getFixturesByLeagueAndSeason(String league,
			String season);

	List<XmlSoccer_Fixture> getFixturesByDates(Date startDate, Date endDate);

}
