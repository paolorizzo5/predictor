package com.paolorizzo.predictor.dao.hibernate.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.BetMatcherDao;
import com.paolorizzo.predictor.hibernate.model.Account;
import com.paolorizzo.predictor.hibernate.model.BetMatcher;

public class BetMatcherDaoImpl extends HibernateDaoSupport implements BetMatcherDao{

	@Override
	public void insert(BetMatcher betMatcher) {
		getHibernateTemplate().save(betMatcher);
	}

	@Override
	public List<BetMatcher> list() {
		return (List<BetMatcher>) getHibernateTemplate().find(
				"from BetMatcher");
	}

	@Override
	public void delete(BetMatcher betMatcher) {
		getHibernateTemplate().delete(betMatcher);
	}

	@Override
	public void update(BetMatcher betMatcher) {
		getHibernateTemplate().update(betMatcher);
		
	}

	@Override
	public BetMatcher get(Integer homeGoals,Integer awayGoals, String eventType) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				BetMatcher.class)
				.add(Restrictions.eq("homeGoals", homeGoals))
				.add(Restrictions.eq("awayGoals", awayGoals))
				.add(Restrictions.eq("eventType", eventType))
				;
		List<BetMatcher> betMatchers = (List<BetMatcher>) getHibernateTemplate().findByCriteria(
				criteria);
		if (betMatchers.size() == 0)
			return null;
		else
			return ((List<BetMatcher>) betMatchers).get(0);
	}

	

}
