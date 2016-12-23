package com.paolorizzo.predictor.dao.hibernate.impl;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.BettypeGroupDao;
import com.paolorizzo.predictor.hibernate.model.BettypeGroup;

public class BettypeGroupDaoImpl extends HibernateDaoSupport implements BettypeGroupDao {

	@Override
	public void insert(BettypeGroup bettypeGroup) {
		getHibernateTemplate().save(bettypeGroup);
	}

	@Override
	public List<BettypeGroup> list() {
		return (List<BettypeGroup>) getHibernateTemplate().find("from BettypeGroup");
	}

	@Override
	public void delete(BettypeGroup bettypeGroup) {
		getHibernateTemplate().delete(bettypeGroup);

	}

	@Override
	public void update(BettypeGroup bettypeGroup) {
		getHibernateTemplate().update(bettypeGroup);

	}

	@Override
	public BettypeGroup getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
