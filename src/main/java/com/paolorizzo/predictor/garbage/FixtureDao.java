package com.paolorizzo.predictor.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.enums.HomeAwayEnum;
import com.paolorizzo.predictor.hibernate.model.Competition;
import com.paolorizzo.predictor.hibernate.model.Fixture;
import com.paolorizzo.predictor.hibernate.model.Team;

public interface FixtureDao {

	void insert(Fixture fixture);

	List<Fixture> findByStatusAndDate(String status, long today, long tomorrow);

	void update(Fixture fixture);

	List<Fixture> list();

	List<Fixture> getByTeam(Team team, Competition competition,
			HomeAwayEnum atHome);

	List<Fixture> findByStatusAndCompetition(String status, String id,
			Boolean hasLimit, Integer numrecords);

	List<Fixture> findDaily(long start, long end);

}
