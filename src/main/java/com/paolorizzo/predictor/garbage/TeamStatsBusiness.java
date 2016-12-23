package com.paolorizzo.predictor.business;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.dao.facade.TeamStatsDao;
import com.paolorizzo.predictor.hibernate.model.TeamStats;
import com.paolorizzo.predictor.services.FixtureService;

public class TeamStatsBusiness {

	static Logger logger = LogManager.getLogger(TeamStatsBusiness.class.getName());
	
	@Autowired
	private TeamStatsDao teamStatsDao;

	public TeamStatsBusiness() {
		// TODO Auto-generated constructor stub
	}

	public TeamStatsBusiness(TeamStatsDao teamStatsDao) {
		super();
		this.teamStatsDao = teamStatsDao;
	}

	public TeamStatsDao getTeamStatsDao() {
		return teamStatsDao;
	}

	public void setTeamStatsDao(TeamStatsDao teamStatsDao) {
		this.teamStatsDao = teamStatsDao;
	}

	@Transactional(readOnly = false)
	public void insert(TeamStats teamStats) {
		teamStatsDao.insert(teamStats);

	}

	@Transactional(readOnly = false)
	public void insertAll(List<TeamStats> teamStats) {

		for (TeamStats teamStat : teamStats) {
			try{
				teamStatsDao.insert(teamStat);
			}catch (Exception exception){
				logger.error("error in TeamStats.insert " + teamStat.toString(),exception);
			}
		}
			

	}

	@Transactional(readOnly = false)
	public void clear() {
		teamStatsDao.clear();

	}

	public boolean isEmpty() {
		
		return teamStatsDao.list().size() == 0;
	}

}
