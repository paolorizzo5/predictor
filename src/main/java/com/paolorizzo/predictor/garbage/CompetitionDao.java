package com.paolorizzo.predictor.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.hibernate.model.Competition;

public interface CompetitionDao {

	void insert(Competition competition);

	List<Competition> list();

	void delete(Competition competition);

	void update(Competition competition);

	Competition getById(String id);

}
