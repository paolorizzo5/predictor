package com.paolorizzo.xmlsoccer.business;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_League;
import com.paolorizzo.xmlsoccer.dao.facade.LeagueDao;

public class LeagueBusiness {

	static Logger logger = LogManager.getLogger(LeagueBusiness.class.getName());

	@Autowired
	private LeagueDao leagueDao;

	public LeagueBusiness(LeagueDao leagueDao) {
		super();
		this.leagueDao = leagueDao;
	}

	@Transactional(readOnly = false)
	public void insertAll(List<XmlSoccer_League> leagues) {
		for (XmlSoccer_League league : leagues) {
			leagueDao.insert(league);
		}

	}

	public boolean isEmpty() {
		return leagueDao.list().size() == 0;
	}

	@Transactional(readOnly = false)
	public void insert(XmlSoccer_League league) {
		leagueDao.insert(league);

	}

	public List<XmlSoccer_League> list() {
		return leagueDao.list();
	}

}
