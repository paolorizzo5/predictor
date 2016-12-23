package com.paolorizzo.xmlsoccer.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Standing;

public interface StandingDao {

	void insert(XmlSoccer_Standing xmlSoccer_Standing);

	List<XmlSoccer_Standing> list();

	void delete(XmlSoccer_Standing xmlSoccer_Standing);

	void update(XmlSoccer_Standing xmlSoccer_Standing);

	XmlSoccer_Standing getById(String id);

	List<XmlSoccer_Standing> getStandingByLeagueAndSeason(String league,
			String season);

}
