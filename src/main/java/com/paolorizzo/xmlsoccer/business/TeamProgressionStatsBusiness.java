package com.paolorizzo.xmlsoccer.business;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.utils.FixtureUtils;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_HistoricMatch;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Team;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_TeamProgressionStats;
import com.paolorizzo.xmlsoccer.dao.facade.TeamDao;
import com.paolorizzo.xmlsoccer.dao.facade.TeamProgressionStatsDao;

public class TeamProgressionStatsBusiness {
	
	static Logger logger = LogManager
			.getLogger(TeamProgressionStatsBusiness.class.getName());
	
	@Autowired
	private TeamProgressionStatsDao teamProgressionStatsDao;
	

	public TeamProgressionStatsBusiness(TeamProgressionStatsDao teamProgressionStatsDao) {
		super();
		this.setTeamProgressionStatsDao(teamProgressionStatsDao);
	}

	
	public boolean isEmpty() {
		return teamProgressionStatsDao.list().size() == 0;
	}

	public TeamProgressionStatsDao getTeamProgressionStatsDao() {
		return teamProgressionStatsDao;
	}

	public void setTeamProgressionStatsDao(TeamProgressionStatsDao teamProgressionStatsDao) {
		this.teamProgressionStatsDao = teamProgressionStatsDao;
	}

}
