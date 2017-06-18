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

public class TeamBusiness {
	
	static Logger logger = LogManager
			.getLogger(TeamBusiness.class.getName());
	
	@Autowired
	private TeamDao teamDao;

	@Autowired
	private HistoricMatchBusiness historicMatchBusiness;
	
	@Autowired
	FixtureUtils fixtureUtils;
	
	public TeamBusiness(TeamDao teamDao, HistoricMatchBusiness historicMatchBusiness,FixtureUtils fixtureUtils) {
		super();
		this.teamDao = teamDao;
		this.historicMatchBusiness = historicMatchBusiness;
		this.fixtureUtils = fixtureUtils;
	}

	@Transactional(readOnly=false)
	public void insertAll(List<XmlSoccer_Team> teams) {
		for (XmlSoccer_Team team : teams) {
			try{
				teamDao.insert(team);	
			}catch (Exception exception){
				logger.warn("warning inserting team " + team.toString());
			}
			
			
		}
		
	}

	public boolean isEmpty() {
		return teamDao.list().size() == 0;
	}

	public void update(XmlSoccer_Team team) {
		teamDao.update(team);
	}
	
	@Transactional(readOnly=false)
	public void updateTeamProgressionStats(XmlSoccer_Team team) {
		Integer consecutiveNotWinningStreak = 0;
		Integer consecutiveNotDrawingStreak = 0;
		Integer consecutiveNotLosingStreak = 0;
		
		List<XmlSoccer_HistoricMatch> historicMatches =  historicMatchBusiness.getLastMatches(team.getId() + "",50);
		for (XmlSoccer_HistoricMatch xmlSoccer_HistoricMatch : historicMatches) {
			if(xmlSoccer_HistoricMatch.getHomeTeam().getId() == team.getId()){
				//SQUARDA DI CASA
				if(fixtureUtils.homeTeamWins(xmlSoccer_HistoricMatch.getHomeGoals(),xmlSoccer_HistoricMatch.getAwayGoals())){
					consecutiveNotWinningStreak = 0;
					consecutiveNotDrawingStreak++;
					consecutiveNotLosingStreak++;
				}else if(fixtureUtils.isDraw(xmlSoccer_HistoricMatch.getHomeGoals(),xmlSoccer_HistoricMatch.getAwayGoals())){
					consecutiveNotWinningStreak++;
					consecutiveNotDrawingStreak = 0;
					consecutiveNotLosingStreak++;
				}else{
					consecutiveNotWinningStreak++;
					consecutiveNotDrawingStreak++;
					consecutiveNotLosingStreak = 0;
				}
			}else{
				//SQUADRA DI TRASFERTA
				if(fixtureUtils.homeTeamWins(xmlSoccer_HistoricMatch.getHomeGoals(),xmlSoccer_HistoricMatch.getAwayGoals())){
					consecutiveNotWinningStreak++;
					consecutiveNotDrawingStreak++;
					consecutiveNotLosingStreak = 0;
				}else if(fixtureUtils.isDraw(xmlSoccer_HistoricMatch.getHomeGoals(),xmlSoccer_HistoricMatch.getAwayGoals())){
					consecutiveNotWinningStreak++;
					consecutiveNotDrawingStreak = 0;
					consecutiveNotLosingStreak++;
				}else{
					consecutiveNotWinningStreak = 0;
					consecutiveNotDrawingStreak++;
					consecutiveNotLosingStreak++;
				}
			}
		}
		XmlSoccer_TeamProgressionStats xmlSoccer_TeamProgressionStats = new XmlSoccer_TeamProgressionStats();
		//xmlSoccer_TeamProgressionStats.setTeam(team);
		xmlSoccer_TeamProgressionStats.setConsecutiveNotDrawingStreak(consecutiveNotDrawingStreak);
		xmlSoccer_TeamProgressionStats.setConsecutiveNotLosingStreak(consecutiveNotLosingStreak);
		xmlSoccer_TeamProgressionStats.setConsecutiveNotWinningStreak(consecutiveNotWinningStreak);
		team.setTeamProgressionStats(xmlSoccer_TeamProgressionStats);
	}

	public List<XmlSoccer_Team> list() {
		return teamDao.list();
	}

}
