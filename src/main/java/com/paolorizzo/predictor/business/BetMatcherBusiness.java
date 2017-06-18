package com.paolorizzo.predictor.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.constants.BettypeConstants;
import com.paolorizzo.predictor.dao.facade.AccountDao;
import com.paolorizzo.predictor.dao.facade.BetMatcherDao;
import com.paolorizzo.predictor.hibernate.model.BetMatcher;

public class BetMatcherBusiness {

	static Logger logger = LogManager.getLogger(JobConfigurationBusiness.class
			.getName());

	@Autowired
	private BetMatcherDao betMatcherDao;

	
	
	
	public BetMatcherBusiness(BetMatcherDao betMatcherDao) {
		super();
		this.betMatcherDao = betMatcherDao;
	}

	public boolean isWinningEvent(Integer homeGoals, Integer awayGoals, String eventType) {
		try{
			BetMatcher betMatcher = betMatcherDao.get(homeGoals, awayGoals, eventType);
			return betMatcher != null;
		}catch(NullPointerException nullPointerException){
			return false;
		}
		
	}

	public BetMatcherDao getBetMatcherDao() {
		return betMatcherDao;
	}

	public void setBetMatcherDao(BetMatcherDao betMatcherDao) {
		this.betMatcherDao = betMatcherDao;
	}

	@Transactional(readOnly=false)
	public void populate() {
		List<BetMatcher> betMatchers = new ArrayList<BetMatcher>();
		
		for (int i = 0;i<20;i++){
			for (int j = 0;j<20;j++){
			if(i > j){
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._1) );
			}else if(i < j){
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._2) );
			}else{
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._X) );
			}
			
			if (i > 0){
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HOMEGOAL) );
			}
			if (j > 0){
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._AWAYGOAL) );
			}
			if (i > 0 && j > 0){
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._GOAL) );
			}
			if (i == 0 || j == 0){
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._NOGOAL) );
			}
			
			switch (i - j) {
			case 20:
			case 19:
			case 18:
			case 17:
			case 16:
			case 15:
			case 14:
			case 13:
			case 12:
			case 11:
			case 10:
			case 9:
			case 8:
			case 7:
			case 6:
			case 5:
			case 4:
			case 3:
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_001_H2_1) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_004_H1_1) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_007_A1_1) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_010_A2_1) );
				break;
			case 2:
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_002_H2_X) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_004_H1_1) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_007_A1_1) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_010_A2_1) );
				break;
			case 1:
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_003_H2_2) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_005_H1_X) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_007_A1_1) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_010_A2_1) );
				break;
			case 0:
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_003_H2_2) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_006_H1_2) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_007_A1_1) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_010_A2_1) );
				break;
			case -1:
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_003_H2_2) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_006_H1_2) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_008_A1_X) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_010_A2_1) );
				break;
			case -2:
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_003_H2_2) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_006_H1_2) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_009_A1_2) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_011_A2_X) );
				break;
			default:
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_003_H2_2) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_006_H1_2) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_009_A1_2) );
				betMatchers.add(new BetMatcher(i, j, BettypeConstants._HND_011_A2_X) );
				break;
			}
			
			switch (i + j) {
				case 0:
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER05) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER15) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER25) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER35) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER45) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER55) );
					break;
				case 1:
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER05) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER15) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER25) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER35) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER45) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER55) );
					break;
				case 2:
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER05) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER15) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER25) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER35) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER45) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER55) );
					break;
				case 3:
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER05) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER15) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER25) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER35) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER45) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER55) );
					break;
				case 4:
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER05) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER15) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER25) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER35) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER45) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER55) );
					break;
				case 5:
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER05) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER15) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER25) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER35) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER45) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._UNDER55) );
					break;
				default:
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER05) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER15) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER25) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER35) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER45) );
					betMatchers.add(new BetMatcher(i, j, BettypeConstants._OVER55) );
					break;
				}	
			}
		
		}
		addAll(betMatchers);
	}

	@Transactional(readOnly=false)
	private void addAll(List<BetMatcher> betMatchers) {
		for (BetMatcher betMatcher : betMatchers) {
			betMatcherDao.insert(betMatcher);
		}
		
	}
}
