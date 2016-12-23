package com.paolorizzo.predictor.dao.hibernate.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.CompetitionDao;
import com.paolorizzo.predictor.hibernate.model.Competition;

public class CompetitionDaoImpl extends HibernateDaoSupport implements
		CompetitionDao {

	@Override
	public void insert(Competition competition) {
		getHibernateTemplate().save(competition);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Competition> list() {
		return (List<Competition>) getHibernateTemplate().find(
				"from Competition");
	}

	@Override
	public void delete(Competition competition) {
		getHibernateTemplate().delete(competition);
	}

	@Override
	public void update(Competition competition) {
		getHibernateTemplate().update(competition);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Competition getById(String id) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(Competition.class).add(Restrictions.eq("id", id));

		return ((List<Competition>) getHibernateTemplate().findByCriteria(
				criteria)).get(0);
	}

}
