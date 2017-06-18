package com.paolorizzo.predictor.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.constants.BettypeConstants;
import com.paolorizzo.predictor.dao.facade.AccountDao;
import com.paolorizzo.predictor.dao.facade.BetTypeDao;
import com.paolorizzo.predictor.hibernate.model.BetType;

public class BetTypeBusiness {
	
	static Logger logger = LogManager.getLogger(BetTypeBusiness.class
			.getName());

	@Autowired
	private BetTypeDao betTypeDao;
	
	@Autowired
	private BetMatcherBusiness betMatcherBusiness;
	

	

	public BetTypeBusiness(BetTypeDao betTypeDao, BetMatcherBusiness betMatcherBusiness) {
		super();
		this.betTypeDao = betTypeDao;
		this.betMatcherBusiness = betMatcherBusiness;
	}

	public BetTypeDao getBetTypeDao() {
		return betTypeDao;
	}

	public void setBetTypeDao(BetTypeDao betTypeDao) {
		this.betTypeDao = betTypeDao;
	}

	public List<BetType> list() {
		return betTypeDao.list();
	}

	public boolean isEmpty() {
		return betTypeDao.list().size() == 0;
	}

	
	@Transactional(readOnly=false)
	public void populate() {
		List<BetType> betTypes = new ArrayList<BetType>();
		betTypes.add(new BetType(BettypeConstants._1));
		betTypes.add(new BetType(BettypeConstants._2));
		betTypes.add(new BetType(BettypeConstants._X));
		betTypes.add(new BetType(BettypeConstants._AWAYGOAL));
		betTypes.add(new BetType(BettypeConstants._GOAL));
		betTypes.add(new BetType(BettypeConstants._HND_001_H2_1));
		betTypes.add(new BetType(BettypeConstants._HND_002_H2_X));
		betTypes.add(new BetType(BettypeConstants._HND_003_H2_2));
		betTypes.add(new BetType(BettypeConstants._HND_004_H1_1));
		betTypes.add(new BetType(BettypeConstants._HND_005_H1_X));
		betTypes.add(new BetType(BettypeConstants._HND_006_H1_2));
		betTypes.add(new BetType(BettypeConstants._HND_007_A1_1));
		betTypes.add(new BetType(BettypeConstants._HND_008_A1_X));
		betTypes.add(new BetType(BettypeConstants._HND_009_A1_2));
		betTypes.add(new BetType(BettypeConstants._HND_010_A2_1));
		betTypes.add(new BetType(BettypeConstants._HND_011_A2_X));
		betTypes.add(new BetType(BettypeConstants._HND_012_A2_2));
		
		betTypes.add(new BetType(BettypeConstants._HOMEGOAL));
		betTypes.add(new BetType(BettypeConstants._NOGOAL));
		betTypes.add(new BetType(BettypeConstants._OVER05));
		betTypes.add(new BetType(BettypeConstants._OVER15));
		betTypes.add(new BetType(BettypeConstants._OVER25));
		betTypes.add(new BetType(BettypeConstants._OVER35));
		betTypes.add(new BetType(BettypeConstants._OVER45));
		betTypes.add(new BetType(BettypeConstants._OVER55));
		
		betTypes.add(new BetType(BettypeConstants._UNDER05));
		betTypes.add(new BetType(BettypeConstants._UNDER15));
		betTypes.add(new BetType(BettypeConstants._UNDER25));
		betTypes.add(new BetType(BettypeConstants._UNDER35));
		betTypes.add(new BetType(BettypeConstants._UNDER45));
		betTypes.add(new BetType(BettypeConstants._UNDER55));
		
		betMatcherBusiness.populate();
		addAll(betTypes);
	}

	@Transactional(readOnly=false)
	private void addAll(List<BetType> betTypes) {
		for (BetType betType : betTypes) {
			betTypeDao.insert(betType);
		}
	}
	
	

}
