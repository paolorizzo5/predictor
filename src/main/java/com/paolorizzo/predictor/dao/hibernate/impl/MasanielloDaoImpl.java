package com.paolorizzo.predictor.dao.hibernate.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.MasanielloDao;
import com.paolorizzo.predictor.hibernate.model.Account;
import com.paolorizzo.predictor.hibernate.model.Masaniello;

public class MasanielloDaoImpl extends HibernateDaoSupport implements MasanielloDao {

	@Override
	public void insert(Masaniello masaniello) {
		getHibernateTemplate().save(masaniello);
	}

	@Override
	public List<Masaniello> list() {
		return (List<Masaniello>) getHibernateTemplate().find(
				"from Masaniello");
	}

	@Override
	public void delete(Masaniello masaniello) {
		getHibernateTemplate().delete(masaniello);
	}

	@Override
	public void update(Masaniello masaniello) {
		getHibernateTemplate().update(masaniello);

	}

}
