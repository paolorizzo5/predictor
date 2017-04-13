package com.paolorizzo.predictor.dao.facade;

import java.math.BigDecimal;
import java.util.List;

import com.paolorizzo.predictor.hibernate.model.DirettaFixture;

public interface DirettaFixtureDao {
	
	void insert(DirettaFixture direttaFixture);

	List<DirettaFixture> list();

	void delete(DirettaFixture direttaFixture);

	void update(DirettaFixture direttaFixture);

	List<DirettaFixture> list(String email);

	DirettaFixture get(String name, String email);

	List<String> getCompetitions();
	
	List<DirettaFixture> getDirettaFixtures(String competition, String homeTeam, String awayTeam, BigDecimal quota1From,
			BigDecimal quota1To, BigDecimal quotaXFrom, BigDecimal quotaXTo, BigDecimal quota2From,
			BigDecimal quota2To);

}
