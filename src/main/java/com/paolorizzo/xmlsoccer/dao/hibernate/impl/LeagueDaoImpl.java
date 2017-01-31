package com.paolorizzo.xmlsoccer.dao.hibernate.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_League;
import com.paolorizzo.xmlsoccer.dao.facade.LeagueDao;

public class LeagueDaoImpl extends HibernateDaoSupport implements LeagueDao {

	@Override
	public void insert(XmlSoccer_League league) {
		getHibernateTemplate().save(league);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XmlSoccer_League> list() {
		return (List<XmlSoccer_League>) getHibernateTemplate().find(
				"from XmlSoccer_League");
	}

	@Override
	public void delete(XmlSoccer_League league) {
		getHibernateTemplate().delete(league);
	}

	@Override
	public void update(XmlSoccer_League league) {
		getHibernateTemplate().update(league);
	}

	@SuppressWarnings("unchecked")
	@Override
	public XmlSoccer_League getById(String id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				XmlSoccer_League.class).add(Restrictions.eq("id", id));

		return ((List<XmlSoccer_League>) getHibernateTemplate().findByCriteria(
				criteria)).get(0);
	}

}
