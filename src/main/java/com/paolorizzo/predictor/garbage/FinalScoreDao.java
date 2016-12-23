package com.paolorizzo.predictor.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.hibernate.model.FinalScore;

public interface FinalScoreDao {

	void insert(FinalScore finalScore);

	List<FinalScore> list();

	FinalScore getByScore(Integer fullTimeHomeGoals, Integer fullTimeAwayGoals);

}
