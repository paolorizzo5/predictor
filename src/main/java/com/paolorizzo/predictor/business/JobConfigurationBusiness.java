package com.paolorizzo.predictor.business;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.dao.facade.JobConfigurationDao;
import com.paolorizzo.predictor.hibernate.model.JobConfiguration;

public class JobConfigurationBusiness {

	static Logger logger = LogManager.getLogger(JobConfigurationBusiness.class
			.getName());

	@Autowired
	private JobConfigurationDao jobConfigurationDao;

	public JobConfigurationBusiness() {
		// TODO Auto-generated constructor stub
	}

	public JobConfigurationBusiness(JobConfigurationDao jobConfigurationDao) {
		super();
		this.jobConfigurationDao = jobConfigurationDao;
	}

	public JobConfigurationDao getJobConfigurationDao() {
		return jobConfigurationDao;
	}

	public void setJobConfigurationDao(JobConfigurationDao jobConfigurationDao) {
		this.jobConfigurationDao = jobConfigurationDao;
	}

	public boolean isEmpty() {
		return jobConfigurationDao.list().size() == 0;
	}

	@Transactional(readOnly = false)
	public void insertAll(List<JobConfiguration> jobConfigurations) {
		for (JobConfiguration jobConfiguration : jobConfigurations) {
			jobConfigurationDao.insert(jobConfiguration);
		}
	}

	public List<JobConfiguration> list() {
		return jobConfigurationDao.list();
	}

	public JobConfiguration getByClass(String name) {
		return jobConfigurationDao.getByClass(name);
	}

	@Transactional(readOnly = false)
	public void update(JobConfiguration jobConfiguration) {
		jobConfigurationDao.update(jobConfiguration);
	}

	@Transactional(readOnly = false)
	public void clear() {
		for (JobConfiguration jobConfiguration : list()) {
			jobConfigurationDao.delete(jobConfiguration);
		}
	}
}
