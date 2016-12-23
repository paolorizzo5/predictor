package com.paolorizzo.predictor.dao.hibernate.impl;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.BettypeDao;
import com.paolorizzo.predictor.hibernate.model.Bettype;

public class BettypeDaoImpl extends HibernateDaoSupport implements BettypeDao {

	@Override
	public void insert(Bettype bettype) {
		getHibernateTemplate().save(bettype);
	}

	@Override
	public List<Bettype> list() {
		return (List<Bettype>) getHibernateTemplate().find("from Bettype");
	}

	@Override
	public void delete(Bettype bettype) {
		getHibernateTemplate().delete(bettype);

	}

	@Override
	public void update(Bettype bettype) {
		getHibernateTemplate().update(bettype);

	}

	@Override
	public Bettype getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
