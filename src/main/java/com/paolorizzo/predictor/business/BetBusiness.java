package com.paolorizzo.predictor.business;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.paolorizzo.predictor.dao.facade.BetDao;


public class BetBusiness {
	
	static Logger logger = LogManager.getLogger(BetBusiness.class
			.getName());

	@Autowired
	private BetDao betDao;
	

	@Autowired
	private MoneyTransactionBusiness moneyTransactionBusiness;
	
	public BetBusiness(BetDao betDao) {
		super();
		this.betDao = betDao;
	}

	
}
