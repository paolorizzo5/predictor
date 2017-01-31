package com.paolorizzo.predictor.business;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.dao.facade.MoneyTransactionDao;
import com.paolorizzo.predictor.hibernate.model.MoneyTransaction;

public class MoneyTransactionBusiness {
	
	static Logger logger = LogManager.getLogger(MoneyTransactionBusiness.class
			.getName());

	@Autowired
	private MoneyTransactionDao moneyTransactionDao;
	
	public MoneyTransactionBusiness(MoneyTransactionDao moneyTransactionDao) {
		super();
		this.moneyTransactionDao = moneyTransactionDao;
	}

	@Transactional(readOnly=false)
	public void delete(MoneyTransaction moneyTransaction) {
		moneyTransactionDao.delete(moneyTransaction);
	}

}
