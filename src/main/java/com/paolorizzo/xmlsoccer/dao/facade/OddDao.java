package com.paolorizzo.xmlsoccer.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Odd;

public interface OddDao {

	void insert(XmlSoccer_Odd xmlSoccer_Odd);

	List<XmlSoccer_Odd> list();

	void delete(XmlSoccer_Odd xmlSoccer_Odd);

	void update(XmlSoccer_Odd xmlSoccer_Odd);

	XmlSoccer_Odd getById(String id);

	List<XmlSoccer_Odd> getOddsByFixtureId(String id);

}
