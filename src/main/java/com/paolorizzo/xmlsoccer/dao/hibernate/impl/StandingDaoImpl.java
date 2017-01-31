package com.paolorizzo.xmlsoccer.dao.hibernate.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Standing;
import com.paolorizzo.xmlsoccer.dao.facade.StandingDao;

public class StandingDaoImpl extends HibernateDaoSupport implements StandingDao {

	Logger logger = LogManager.getLogger("root");

	@Override
	public void insert(XmlSoccer_Standing standing) {
		try {
			getHibernateTemplate().save(standing);
		} catch (Exception exception) {
			logger.error("Error inserting standing", exception);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XmlSoccer_Standing> list() {
		return (List<XmlSoccer_Standing>) getHibernateTemplate().find(
				"from XmlSoccer_Standing");
	}

	@Override
	public void delete(XmlSoccer_Standing xmlSoccer_Standing) {
		getHibernateTemplate().delete(xmlSoccer_Standing);
	}

	@Override
	public void update(XmlSoccer_Standing xmlSoccer_Standing) {
		getHibernateTemplate().update(xmlSoccer_Standing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public XmlSoccer_Standing getById(String id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				XmlSoccer_Standing.class).add(Restrictions.eq("id", id));

		return ((List<XmlSoccer_Standing>) getHibernateTemplate()
				.findByCriteria(criteria)).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XmlSoccer_Standing> getStandingByLeagueAndSeason(String league,
			String season) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(XmlSoccer_Standing.class)
				.add(Restrictions.eq("season", season))
				.add(Restrictions.eq("league.id", Integer.parseInt(league)));

		return ((List<XmlSoccer_Standing>) getHibernateTemplate()
				.findByCriteria(criteria));
	}

}
