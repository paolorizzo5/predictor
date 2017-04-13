package com.paolorizzo.predictor.dao.facade;

import com.paolorizzo.predictor.hibernate.model.User;

public interface UserDao {

	boolean insert(User user) throws Exception;

	boolean delete(User user) throws Exception;

	User login(String email, String password) throws Exception;

	Integer count() throws Exception;

	User findByEmail(String email);

	Boolean update(User user);

	User firstLogin(String email, String password);

}
