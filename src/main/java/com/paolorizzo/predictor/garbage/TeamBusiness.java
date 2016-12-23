package com.paolorizzo.predictor.business;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.dao.facade.TeamDao;
import com.paolorizzo.predictor.hibernate.model.Team;

public class TeamBusiness {

	@Autowired
	private TeamDao teamDao;

	public TeamBusiness() {
		// TODO Auto-generated constructor stub
	}

	public TeamBusiness(TeamDao teamDao) {
		super();
		this.teamDao = teamDao;
	}

	public TeamDao getTeamDao() {
		return teamDao;
	}

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}

	@Transactional(readOnly = false)
	public void update(Team team) {
		teamDao.update(team);

	}

	public List<Team> getTeams(String homeTeamName, String awayTeamName,
			String id) {
		Team homeTeam = teamDao.find(homeTeamName, id);
		Team awayTeam = teamDao.find(awayTeamName, id);
		return Arrays.asList(homeTeam, awayTeam);
	}

}
