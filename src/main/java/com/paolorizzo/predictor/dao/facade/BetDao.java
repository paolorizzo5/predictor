package com.paolorizzo.predictor.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.hibernate.model.Bet;

public interface BetDao {

	void insert(Bet bet);

	List<Bet> list();

	void delete(Bet bet);

	void update(Bet bet);

	List<Bet> list(String email);

}
