package com.paolorizzo.predictor.dao.hibernate.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.ProspectDao;
import com.paolorizzo.predictor.hibernate.model.Prospect;
import com.paolorizzo.predictor.hibernate.model.ProspectElement;

public class ProspectDaoImpl extends HibernateDaoSupport implements ProspectDao {
 
	@Override
	public void insert(Prospect prospect) {
		getHibernateTemplate().save(prospect);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prospect> list() {
		return (List<Prospect>) getHibernateTemplate().find(
				"from Prospect");
	}

	@Override
	public void delete(Prospect prospect) {
		getHibernateTemplate().delete(prospect);
	}

	@Override
	public void update(Prospect prospect) {
		getHibernateTemplate().update(prospect);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Prospect getByName(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				Prospect.class).add(Restrictions.eq("name", name));

		List<?> prospects = getHibernateTemplate().findByCriteria(
				criteria);
		return ((List<Prospect>) prospects).get(0);
	}

	@Override
	public Prospect get(String accountName, String email) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				Prospect.class).add(Restrictions.eq("account.name", accountName)).add(Restrictions.eq("user.email", email));
		
		List<?> prospects = getHibernateTemplate().findByCriteria(
				criteria);
		if(prospects == null){
			return null;
		}
		return ((List<Prospect>) prospects).get(0);
		

	}


}
