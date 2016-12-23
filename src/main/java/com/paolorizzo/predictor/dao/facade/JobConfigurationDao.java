package com.paolorizzo.predictor.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.hibernate.model.JobConfiguration;

public interface JobConfigurationDao {

	void insert(JobConfiguration jobConfiguration);

	List<JobConfiguration> list();

	void delete(JobConfiguration jobConfiguration);

	void update(JobConfiguration jobConfiguration);

	JobConfiguration getByName(String name);

	JobConfiguration getByClass(String name);

}
