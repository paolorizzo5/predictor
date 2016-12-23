package com.paolorizzo.xmlsoccer.business;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Team;
import com.paolorizzo.xmlsoccer.dao.facade.TeamDao;

public class TeamBusiness {
	
	static Logger logger = LogManager
			.getLogger(TeamBusiness.class.getName());
	
	@Autowired
	private TeamDao teamDao;
	
	

	public TeamBusiness(TeamDao teamDao) {
		super();
		this.teamDao = teamDao;
	}



	@Transactional(readOnly=false)
	public void insertAll(List<XmlSoccer_Team> teams) {
		for (XmlSoccer_Team team : teams) {
			teamDao.insert(team);
		}
		
	}



	public boolean isEmpty() {
		return teamDao.list().size() == 0;
	}

}
