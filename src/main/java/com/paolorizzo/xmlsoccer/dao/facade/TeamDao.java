package com.paolorizzo.xmlsoccer.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Team;

public interface TeamDao {
	
	void insert(XmlSoccer_Team xmlSoccer_Team);

	List<XmlSoccer_Team> list();

	void delete(XmlSoccer_Team xmlSoccer_Team);

	void update(XmlSoccer_Team xmlSoccer_Team);

	XmlSoccer_Team getById(String id);


}
