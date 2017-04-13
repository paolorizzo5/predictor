package com.paolorizzo.predictor.dao.hibernate.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.DirettaFixtureDao;
import com.paolorizzo.predictor.hibernate.model.DirettaFixture;
import com.paolorizzo.predictor.hibernate.model.JobConfiguration;

public class DirettaFixtureDaoImpl extends HibernateDaoSupport implements DirettaFixtureDao {

	@Override
	public void insert(DirettaFixture direttaFixture) {
		getHibernateTemplate().save(direttaFixture);
	}

	@Override
	public List<DirettaFixture> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(DirettaFixture direttaFixture) {
		getHibernateTemplate().delete(direttaFixture);
	}

	@Override
	public void update(DirettaFixture direttaFixture) {
		getHibernateTemplate().update(direttaFixture);
	}

	@Override
	public List<DirettaFixture> list(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DirettaFixture get(String name, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCompetitions() {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				DirettaFixture.class);
		criteria.setProjection(Projections.distinct(Projections.property("currentCompetition")));
		
		List<?> direttaFixtures = getHibernateTemplate().findByCriteria(
				criteria);
		
		return (List<String>) direttaFixtures;
	}

	@Override
	public List<DirettaFixture> getDirettaFixtures(String competition,String homeTeam,String awayTeam, BigDecimal quota1From, BigDecimal quota1To,
			BigDecimal quotaXFrom, BigDecimal quotaXTo, BigDecimal quota2From, BigDecimal quota2To) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				DirettaFixture.class);
		
				if(competition != null && competition.length() > 0){
					criteria = criteria.add(Restrictions.eq("currentCompetition", competition));
				}
				if(quota1From != null){
					criteria = criteria.add(Restrictions.ge("quota1", quota1From));
				}
				if(quota1To != null){
					criteria = criteria.add(Restrictions.le("quota1", quota1To));
				}
				if(quotaXFrom != null){
					criteria = criteria.add(Restrictions.ge("quotaX", quotaXFrom));
				}
				if(quotaXTo != null){
					criteria = criteria.add(Restrictions.le("quotaX", quotaXTo));
				}
				if(quota2From != null){
					criteria = criteria.add(Restrictions.ge("quota2", quota2From));
				}
				if(quota2To != null){
					criteria = criteria.add(Restrictions.le("quota2", quota2To));
				}
				if(homeTeam != null){
					criteria = criteria.add(Restrictions.eq("homeTeam", homeTeam));
				}
				if(awayTeam != null){
					criteria = criteria.add(Restrictions.eq("awayTeam", awayTeam));
				}
				
				criteria = criteria.addOrder(Order.desc("date"));
				List<?> direttaFixtures = getHibernateTemplate().findByCriteria(
						criteria);
				
				
				
				return (List<DirettaFixture>) direttaFixtures;
	}

}
