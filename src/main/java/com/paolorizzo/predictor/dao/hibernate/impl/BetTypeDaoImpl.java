package com.paolorizzo.predictor.dao.hibernate.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.BetTypeDao;
import com.paolorizzo.predictor.hibernate.model.BetType;

public class BetTypeDaoImpl extends HibernateDaoSupport implements BetTypeDao {

	@Override
	public void insert(BetType betType) {
		getHibernateTemplate().save(betType);
	}

	@Override
	public List<BetType> list() {
		return (List<BetType>) getHibernateTemplate().find(
				"from BetType");
	}

	@Override
	public void delete(BetType betType) {
		getHibernateTemplate().delete(betType);
	}

	@Override
	public void update(BetType betType) {
		getHibernateTemplate().update(betType);
	}

}
