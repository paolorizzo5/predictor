package com.paolorizzo.predictor.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.hibernate.model.Account;

public interface AccountDao {

	void insert(Account account);

	List<Account> list();

	void delete(Account account);

	void update(Account account);

	Account getByClass(String name);

	List<Account> list(String email);

	Account get(String name, String email);

}
