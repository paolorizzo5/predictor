package com.paolorizzo.predictor.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
		betTypes.add(new BetType("1"));
		betTypes.add(new BetType("X"));
		betTypes.add(new BetType("2"));
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
