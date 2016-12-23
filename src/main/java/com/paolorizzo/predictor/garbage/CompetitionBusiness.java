package com.paolorizzo.predictor.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.paolorizzo.predictor.dao.facade.CompetitionDao;
import com.paolorizzo.predictor.hibernate.model.Competition;

public class CompetitionBusiness {

	@Autowired
	private CompetitionDao competitionDao;

	public CompetitionBusiness() {
		// TODO Auto-generated constructor stub
	}

	public CompetitionDao getCompetitionDao() {
		return competitionDao;
	}

	public void setCompetitionDao(CompetitionDao competitionDao) {
		this.competitionDao = competitionDao;
	}

	public CompetitionBusiness(CompetitionDao competitionDao) {
		super();
		this.competitionDao = competitionDao;
	}

	public boolean isEmpty() {
		return competitionDao.list().size() == 0;
	}

	public List<Competition> list() {
		List<Competition> competitions = competitionDao.list();
		return competitions;
	}

}
