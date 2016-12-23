package com.paolorizzo.predictor.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.hibernate.model.Bettype;

public interface BettypeDao {

	void insert(Bettype bettype);

	List<Bettype> list();

	void delete(Bettype bettype);

	void update(Bettype bettype);

	Bettype getById(String id);

}
