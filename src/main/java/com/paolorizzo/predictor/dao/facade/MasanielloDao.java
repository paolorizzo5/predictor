package com.paolorizzo.predictor.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.hibernate.model.Masaniello;

public interface MasanielloDao {

	void insert(Masaniello masaniello);

	List<Masaniello> list();

	void delete(Masaniello masaniello);

	void update(Masaniello masaniello);

	Masaniello find(String name, String email);

}
