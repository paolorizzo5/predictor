package com.paolorizzo.predictor.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
				betMatchers.add(new BetMatcher(i, j, "1") );
			}else if(i < j){
				betMatchers.add(new BetMatcher(i, j, "2") );
			}else
				betMatchers.add(new BetMatcher(i, j, "X") );
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
