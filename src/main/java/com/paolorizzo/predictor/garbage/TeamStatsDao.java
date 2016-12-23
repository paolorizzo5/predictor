package com.paolorizzo.predictor.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.hibernate.model.TeamStats;

public interface TeamStatsDao {

	void insert(TeamStats teamStats);

	List<TeamStats> list();

	void delete(TeamStats teamStats);

	void update(TeamStats teamStats);

	void clear();

}
