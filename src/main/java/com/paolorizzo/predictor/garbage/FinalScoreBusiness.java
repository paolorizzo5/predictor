package com.paolorizzo.predictor.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.dao.facade.FinalScoreDao;
import com.paolorizzo.predictor.hibernate.model.FinalScore;

public class FinalScoreBusiness {

	@Autowired
	private FinalScoreDao finalScoreDao;

	public FinalScoreBusiness() {
		// TODO Auto-generated constructor stub
	}

	public FinalScoreBusiness(FinalScoreDao finalScoreDao) {
		super();
		this.finalScoreDao = finalScoreDao;
	}

	public FinalScoreDao getFinalScoreDao() {
		return finalScoreDao;
	}

	public void setFinalScoreDao(FinalScoreDao finalScoreDao) {
		this.finalScoreDao = finalScoreDao;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return finalScoreDao.list().size() == 0;
	}

	@Transactional(readOnly = false)
	public void insert(int homeGoals, int awayGoals) {
		FinalScore finalScore = new FinalScore(homeGoals, awayGoals);
		finalScoreDao.insert(finalScore);
	}

	public List<FinalScore> list() {
		return finalScoreDao.list();
	}

	public FinalScore getByScore(int fullTimeHomeGoals, int fullTimeAwayGoals) {
		return finalScoreDao.getByScore(fullTimeHomeGoals, fullTimeAwayGoals);
	}

}
