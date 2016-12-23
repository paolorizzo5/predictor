package com.paolorizzo.predictor.dao.facade;

import com.paolorizzo.predictor.hibernate.model.Team;

public interface TeamDao {

	void insert(Team team);

	Team getByCode(String code);

	void update(Team team);

	Team find(String homeTeam, String id);

}
