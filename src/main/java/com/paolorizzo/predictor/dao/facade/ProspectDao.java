package com.paolorizzo.predictor.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.hibernate.model.Prospect;
import com.paolorizzo.predictor.hibernate.model.ProspectElement;

public interface ProspectDao {
	
	void insert(Prospect prospect);

	List<Prospect> list();

	void delete(Prospect prospect);

	void update(Prospect prospect);

	Prospect getByName(String name);

	Prospect get(String accountName, String email);

}
