package com.paolorizzo.xmlsoccer.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_TeamProgressionStats;

public interface TeamProgressionStatsDao {

	void insert(XmlSoccer_TeamProgressionStats xmlSoccer_TeamProgressionStats);

	List<XmlSoccer_TeamProgressionStats> list();

	void delete(XmlSoccer_TeamProgressionStats xmlSoccer_TeamProgressionStats);

	void update(XmlSoccer_TeamProgressionStats xmlSoccer_TeamProgressionStats);

	XmlSoccer_TeamProgressionStats getById(String id);

}
