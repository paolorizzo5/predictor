package com.paolorizzo.predictor.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.hibernate.model.Account;
import com.paolorizzo.predictor.hibernate.model.AccountStats;

public interface AccountStatsDao {

	void insert(AccountStats accountStats);

	void delete(AccountStats accountStats);

	void update(AccountStats accountStats);

	List<Account> list(String accountName);

}
