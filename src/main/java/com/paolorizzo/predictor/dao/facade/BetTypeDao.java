package com.paolorizzo.predictor.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.hibernate.model.BetType;

public interface BetTypeDao {
	
	void insert(BetType betType);

	List<BetType> list();

	void delete(BetType betType);

	void update(BetType betType);


}
