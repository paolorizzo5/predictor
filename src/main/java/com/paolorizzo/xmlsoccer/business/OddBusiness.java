package com.paolorizzo.xmlsoccer.business;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Odd;
import com.paolorizzo.xmlsoccer.dao.facade.OddDao;

public class OddBusiness {

	static Logger logger = LogManager.getLogger(OddBusiness.class.getName());

	@Autowired
	private OddDao oddDao;

	public OddBusiness(OddDao oddDao) {
		super();
		this.oddDao = oddDao;
	}

	@Transactional(readOnly = false)
	public void insertAll(List<XmlSoccer_Odd> oddes) {
		for (XmlSoccer_Odd odd : oddes) {
			oddDao.insert(odd);
		}
	}

	public boolean isEmpty() {
		return oddDao.list().size() == 0;
	}

	@Transactional(readOnly = false)
	public void insert(XmlSoccer_Odd odd) {
		oddDao.insert(odd);

	}

	public List<XmlSoccer_Odd> getOddsByFixtureId(String id) {
		return oddDao.getOddsByFixtureId(id);
	}

	@Transactional(readOnly = false)
	public void deleteById(String id) {
		List<XmlSoccer_Odd> odds = getOddsByFixtureId(id);
		for (XmlSoccer_Odd xmlSoccer_Odd : odds) {
			oddDao.delete(xmlSoccer_Odd);
		}
	}

}
