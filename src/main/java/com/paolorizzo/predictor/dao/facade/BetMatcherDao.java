package com.paolorizzo.predictor.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.hibernate.model.BetMatcher;

public interface BetMatcherDao {
	
	void insert(BetMatcher betMatcher);

	List<BetMatcher> list();

	void delete(BetMatcher betMatcher);

	void update(BetMatcher betMatcher);

	BetMatcher get(Integer homeGoals, Integer awayGoals, String eventType);


}
