package com.paolorizzo.predictor.dao.hibernate.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.MoneyTransactionDao;
import com.paolorizzo.predictor.hibernate.model.Account;
import com.paolorizzo.predictor.hibernate.model.MoneyTransaction;

public class MoneyTransactionDaoImpl extends HibernateDaoSupport implements MoneyTransactionDao {

	@Override
	public void delete(MoneyTransaction moneyTransaction) {
		getHibernateTemplate().delete(moneyTransaction);
		
	}
	

}
