package com.paolorizzo.predictor.dao.hibernate.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.TennisFixtureDao;
import com.paolorizzo.predictor.hibernate.model.TennisFixture;

public class TennisFixtureDaoImpl extends HibernateDaoSupport implements TennisFixtureDao{

	@Override
	public void insert(TennisFixture tennisFixture) {
		getHibernateTemplate().save(tennisFixture);
	}
	
	

}
