package com.paolorizzo.predictor.dao.hibernate.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.JobConfigurationDao;
import com.paolorizzo.predictor.hibernate.model.JobConfiguration;

public class JobConfigurationDaoImpl extends HibernateDaoSupport implements
		JobConfigurationDao {

	@Override
	public void insert(JobConfiguration jobConfiguration) {
		getHibernateTemplate().save(jobConfiguration);

	}

	@Override
	public List<JobConfiguration> list() {
		return (List<JobConfiguration>) getHibernateTemplate().find(
				"from JobConfiguration");
	}

	@Override
	public void delete(JobConfiguration jobConfiguration) {
		getHibernateTemplate().delete(jobConfiguration);

	}

	@Override
	public void update(JobConfiguration jobConfiguration) {
		getHibernateTemplate().update(jobConfiguration);

	}

	@Override
	public JobConfiguration getByName(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				JobConfiguration.class).add(Restrictions.eq("name", name));

		List<?> jobConfigurations = getHibernateTemplate().findByCriteria(
				criteria);
		return ((List<JobConfiguration>) jobConfigurations).get(0);
	}

	@Override
	public JobConfiguration getByClass(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				JobConfiguration.class).add(Restrictions.eq("className", name));

		List<?> jobConfigurations = getHibernateTemplate().findByCriteria(
				criteria);
		return ((List<JobConfiguration>) jobConfigurations).get(0);
	}

}
