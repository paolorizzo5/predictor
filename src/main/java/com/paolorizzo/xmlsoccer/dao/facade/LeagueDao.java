package com.paolorizzo.xmlsoccer.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_League;

public interface LeagueDao {
	
	void insert(XmlSoccer_League league);

	List<XmlSoccer_League> list();

	void delete(XmlSoccer_League league);

	void update(XmlSoccer_League league);

	XmlSoccer_League getById(String id);


}
