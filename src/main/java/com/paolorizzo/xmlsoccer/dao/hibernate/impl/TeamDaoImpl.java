package com.paolorizzo.xmlsoccer.dao.hibernate.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Team;
import com.paolorizzo.xmlsoccer.dao.facade.TeamDao;

public class TeamDaoImpl extends HibernateDaoSupport implements TeamDao {

	@Override
	public void insert(XmlSoccer_Team xmlSoccer_Team) {
		getHibernateTemplate().save(xmlSoccer_Team);

	}

	@Override
	public List<XmlSoccer_Team> list() {
		return (List<XmlSoccer_Team>) getHibernateTemplate().find(
				"from XmlSoccer_Team");
	}

	@Override
	public void delete(XmlSoccer_Team xmlSoccer_Team) {
		getHibernateTemplate().delete(xmlSoccer_Team);
	}

	@Override
	public void update(XmlSoccer_Team xmlSoccer_Team) {
		getHibernateTemplate().update(xmlSoccer_Team);
	}

	@Override
	public XmlSoccer_Team getById(String id) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(XmlSoccer_Team.class).add(Restrictions.eq("id", id));

		return ((List<XmlSoccer_Team>) getHibernateTemplate().findByCriteria(
				criteria)).get(0);
	}

}
