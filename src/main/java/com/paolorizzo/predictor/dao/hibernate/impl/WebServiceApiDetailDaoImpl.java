package com.paolorizzo.predictor.dao.hibernate.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.WebServiceApiDetailDao;
import com.paolorizzo.predictor.hibernate.model.Account;
import com.paolorizzo.predictor.hibernate.model.WebServiceApiDetail;

public class WebServiceApiDetailDaoImpl extends HibernateDaoSupport implements WebServiceApiDetailDao {

	@Override
	public void insert(WebServiceApiDetail webServiceApiDetail) {
		getHibernateTemplate().save(webServiceApiDetail);
		
	}

	@Override
	public List<WebServiceApiDetail> list() {
		return (List<WebServiceApiDetail>) getHibernateTemplate().find(
				"from WebServiceApiDetail");
	}

	@Override
	public void delete(WebServiceApiDetail webServiceApiDetail) {
		getHibernateTemplate().delete(webServiceApiDetail);
		
	}

	@Override
	public void update(WebServiceApiDetail webServiceApiDetail) {
		getHibernateTemplate().update(webServiceApiDetail);
		
	}

	@Override
	public WebServiceApiDetail get(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				WebServiceApiDetail.class).add(Restrictions.eq("name", name));
		try{
			WebServiceApiDetail webServiceApiDetail = (WebServiceApiDetail) (getHibernateTemplate().findByCriteria(
					criteria)).get(0);
			return webServiceApiDetail;
		}catch(Exception exception){
			return null;
		}
		
	}

}
