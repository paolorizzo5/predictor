package com.paolorizzo.xmlsoccer.dao.hibernate.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_TeamProgressionStats;
import com.paolorizzo.xmlsoccer.dao.facade.TeamProgressionStatsDao;

public class TeamProgressionStatsDaoImpl extends HibernateDaoSupport implements TeamProgressionStatsDao {

	@Override
	public void insert(XmlSoccer_TeamProgressionStats xmlSoccer_TeamProgressionStats) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XmlSoccer_TeamProgressionStats> list() {
		return (List<XmlSoccer_TeamProgressionStats>) getHibernateTemplate().find(
				"from XmlSoccer_TeamProgressionStats");
		}

	@Override
	public void delete(XmlSoccer_TeamProgressionStats xmlSoccer_TeamProgressionStats) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(XmlSoccer_TeamProgressionStats xmlSoccer_TeamProgressionStats) {
		// TODO Auto-generated method stub

	}

	@Override
	public XmlSoccer_TeamProgressionStats getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
