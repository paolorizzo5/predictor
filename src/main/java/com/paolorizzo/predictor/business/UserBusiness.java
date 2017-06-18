package com.paolorizzo.predictor.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.dao.facade.UserDao;
import com.paolorizzo.predictor.enums.UserStatus;
import com.paolorizzo.predictor.hibernate.model.MasanielloPlan;
import com.paolorizzo.predictor.hibernate.model.User;
import com.paolorizzo.predictor.services.response.user.UserLoginResponse;
import com.paolorizzo.predictor.services.response.user.dto.UserDto;
import com.paolorizzo.predictor.utils.EmailManager;
import com.paolorizzo.predictor.utils.MD5;
import com.paolorizzo.predictor.utils.SimpleUtils;

public class UserBusiness {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MasanielloPlanBusiness masanielloPlanBusiness;
	
	@Autowired
	private SimpleUtils simpleUtils;
	
	static Logger logger = LogManager.getLogger(JobConfigurationBusiness.class
			.getName());


	public UserBusiness(UserDao userDao,MasanielloPlanBusiness masanielloPlanBusiness, SimpleUtils simpleUtils) {
		super();
		this.userDao = userDao;
		this.setMasanielloPlanBusiness(masanielloPlanBusiness);
		this.simpleUtils = simpleUtils;
	}

	@Transactional(readOnly = false)
	public boolean insert(String email, String password) {
		try {
			boolean b = false;
			User user = new User(email, password,UserStatus.ON.name());
			b = userDao.insert(user);
			return b;
		} catch (Exception exception) {

			return false;
		}

	}

	public boolean delete(String email) throws Exception {
		User user = new User(email);
		return userDao.delete(user);
	}

	public UserLoginResponse login(String email, String password) {

		try {
			User user = userDao.login(email, password);
			if(user == null){
				user = userDao.firstLogin(email, password);
				if(user != null){
					user.setStatus(UserStatus.ON.name());
					update(user);
				}
			}

			UserLoginResponse loginResponse = new UserLoginResponse(
					UserDto.fromUser(user));
			return loginResponse;
		} catch (Exception exception) {
			return null;
		}
	}

	public Boolean signup(String email) {

		boolean b;
		try {
			String password = simpleUtils.generateString();

			User user = new User(email, MD5.getMD5(password),UserStatus.STANDBY.name());
			b = userDao.insert(user);
			if (b) {
				try {
					EmailManager
							.send(email,
									"iBet - Welcome to myBet - EMAIL Confirmation",
									"Hi,\r\nin order to complete your registration please enter the password shown below \r\n"
											+ password);
				} catch (Exception exception) {
					logger.error("An error occured during email signup process");
				}

			}
			return b;
		} catch (Exception exception) {
			logger.error("An error occured during signup process");
		}
		return false;

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

	@Transactional(readOnly = false)
	public Boolean deleteMasanielloPlan(String email, String name) {
		try {
			User user = findByEmail(email);
			user.getMasanielloPlans().remove(masanielloPlanBusiness.getPlan(email, name));
			update(user);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	public MasanielloPlanBusiness getMasanielloPlanBusiness() {
		return masanielloPlanBusiness;
	}

	public void setMasanielloPlanBusiness(MasanielloPlanBusiness masanielloPlanBusiness) {
		this.masanielloPlanBusiness = masanielloPlanBusiness;
	}

	public SimpleUtils getSimpleUtils() {
		return simpleUtils;
	}

	public void setSimpleUtils(SimpleUtils simpleUtils) {
		this.simpleUtils = simpleUtils;
	}

}
