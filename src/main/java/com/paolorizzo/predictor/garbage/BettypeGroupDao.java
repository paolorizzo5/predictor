package com.paolorizzo.predictor.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.hibernate.model.Bettype;
import com.paolorizzo.predictor.hibernate.model.BettypeGroup;

public interface BettypeGroupDao {

	void insert(BettypeGroup bettypeGroup);

	List<BettypeGroup> list();

	void delete(BettypeGroup bettypeGroup);

	void update(BettypeGroup bettypeGroup);

	BettypeGroup getById(String id);

}
