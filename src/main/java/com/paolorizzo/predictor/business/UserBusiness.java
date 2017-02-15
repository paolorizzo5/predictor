package com.paolorizzo.predictor.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.dao.facade.UserDao;
import com.paolorizzo.predictor.hibernate.model.User;
import com.paolorizzo.predictor.services.response.user.UserLoginResponse;
import com.paolorizzo.predictor.services.response.user.dto.UserDto;
import com.paolorizzo.predictor.utils.EmailManager;
import com.paolorizzo.predictor.utils.MD5;
import com.paolorizzo.predictor.utils.SimpleUtils;

public class UserBusiness {

	@Autowired
	private UserDao userDao;

	public UserBusiness(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Transactional(readOnly = false)
	public boolean insert(String email, String password) {
		try {
			boolean b = false;
			User user = new User(email, password);
			b = userDao.insert(user);
			return b;
		} catch (Exception exception) {

			return false;
		}

	}

	public boolean delete(String email) throws Exception {
		User user = new User(email, null);
		return userDao.delete(user);
	}

	public UserLoginResponse login(String email, String password) {

		try {
			User user = userDao.login(email, password);

			UserLoginResponse loginResponse = new UserLoginResponse(
					UserDto.fromUser(user));
			return loginResponse;
		} catch (Exception exception) {
			return null;
		}
	}

	public User signup(String email) {

		boolean b;
		try {
			String password = SimpleUtils.generateString();

			User user = new User(email, MD5.getMD5(password));
			b = userDao.insert(user);
			if (b) {
				try {
					EmailManager
							.send(email,
									"iBet - Welcome to myBet - EMAIL Confirmation",
									"Hi,\r\nin order to complete your registration please enter the password shown below \r\n"
											+ password);
				} catch (Exception exception) {
				}

			}
			return user;
		} catch (Exception exception) {
		}
		return null;

	}

	public boolean isEmpty() {

		Integer count = 0;
		try {
			count = userDao.count();
		} catch (Exception exception) {
		}

		return count <= 0;
	}

	public User findByEmail(String email) {
		try {
			User user = userDao.findByEmail(email);
			return user;
		} catch (Exception exception) {

			return null;
		}
	}

	public Boolean update(User user) {
		return userDao.update(user);
	}

}
