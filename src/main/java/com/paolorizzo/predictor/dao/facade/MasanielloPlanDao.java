package com.paolorizzo.predictor.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.hibernate.model.MasanielloPlan;

public interface MasanielloPlanDao {
	
	void insert(MasanielloPlan masanielloPlan);

	List<MasanielloPlan> list();

	void delete(MasanielloPlan masanielloPlan);

	void update(MasanielloPlan masanielloPlan);

	List<MasanielloPlan> list(String email);

	MasanielloPlan getPlan(String email, String name);

	void merge(MasanielloPlan masanielloPlan);

}
