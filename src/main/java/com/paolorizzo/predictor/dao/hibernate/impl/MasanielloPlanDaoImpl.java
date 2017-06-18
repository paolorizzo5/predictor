package com.paolorizzo.predictor.dao.hibernate.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.MasanielloPlanDao;
import com.paolorizzo.predictor.hibernate.model.MasanielloPlan;

public class MasanielloPlanDaoImpl extends HibernateDaoSupport implements MasanielloPlanDao {

	@Override
	public void insert(MasanielloPlan masanielloPlan) {
		getHibernateTemplate().save(masanielloPlan);
	}

	@Override
	public List<MasanielloPlan> list() {
		return (List<MasanielloPlan>) getHibernateTemplate().find(
				"from MasanielloPlan");
	}

	@Override
	public void delete(MasanielloPlan masanielloPlan) {
		getHibernateTemplate().delete(masanielloPlan);
	}

	@Override
	public void update(MasanielloPlan masanielloPlan) {
		getHibernateTemplate().update(masanielloPlan);
	}

	@Override
	public List<MasanielloPlan> list(String email) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				MasanielloPlan.class).add(Restrictions.eq("user.email", email));

		List<?> plans = getHibernateTemplate().findByCriteria(
				criteria);
		return ((List<MasanielloPlan>) plans);
	}

	@Override
	public MasanielloPlan getPlan(String email, String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				MasanielloPlan.class)
				.add(Restrictions.eq("user.email", email))
				.add(Restrictions.eq("name", name));

		List<?> plans = getHibernateTemplate().findByCriteria(
				criteria);
		return ((List<MasanielloPlan>) plans).get(0);
	}

	@Override
	public void merge(MasanielloPlan masanielloPlan) {
		getHibernateTemplate().saveOrUpdate(masanielloPlan);
		
	}

}
