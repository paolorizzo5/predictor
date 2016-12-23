package com.paolorizzo.xmlsoccer.dao.hibernate.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Fixture;
import com.paolorizzo.xmlsoccer.dao.facade.FixtureDao;

public class FixtureDaoImpl extends HibernateDaoSupport implements FixtureDao {

	Logger logger = LogManager.getLogger("root");

	@Override
	public void insert(XmlSoccer_Fixture fixture) {
		try {
			getHibernateTemplate().save(fixture);
		} catch (Exception exception) {
			logger.error("Error inserting fixture", exception);
		}

	}

	@Override
	public List<XmlSoccer_Fixture> list() {
		return (List<XmlSoccer_Fixture>) getHibernateTemplate().find(
				"from XmlSoccer_Fixture");
	}

	@Override
	public void delete(XmlSoccer_Fixture xmlSoccer_Fixture) {
		getHibernateTemplate().delete(xmlSoccer_Fixture);
	}

	@Override
	public void update(XmlSoccer_Fixture xmlSoccer_Fixture) {
		getHibernateTemplate().update(xmlSoccer_Fixture);
	}

	@Override
	public XmlSoccer_Fixture getById(String id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				XmlSoccer_Fixture.class).add(Restrictions.eq("id", id));

		return ((List<XmlSoccer_Fixture>) getHibernateTemplate()
				.findByCriteria(criteria)).get(0);
	}

	@Override
	public List<XmlSoccer_Fixture> getFixturesByLeagueAndSeason(String league,
			String season) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(XmlSoccer_Fixture.class)
				// .add(Restrictions.eq("season", season))
				.add(Restrictions.gt("date", new Date()))
				.add(Restrictions.eq("league.id", Integer.parseInt(league)));

		return ((List<XmlSoccer_Fixture>) getHibernateTemplate()
				.findByCriteria(criteria));
	}

	@Override
	public List<XmlSoccer_Fixture> getFixturesByDates(Date startDate,
			Date endDate) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(XmlSoccer_Fixture.class)
				// .add(Restrictions.eq("season", season))
				.add(Restrictions.gt("date", startDate))
				.add(Restrictions.lt("date", endDate));

		return ((List<XmlSoccer_Fixture>) getHibernateTemplate()
				.findByCriteria(criteria));
	}

}
