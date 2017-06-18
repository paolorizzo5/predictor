package com.paolorizzo.predictor.dao.hibernate.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.UserDao;
import com.paolorizzo.predictor.enums.UserStatus;
import com.paolorizzo.predictor.hibernate.model.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public boolean insert(User user) throws Exception {
		getHibernateTemplate().save(user);
		return true;
	}

	@Override
	public boolean delete(User user) throws Exception {
			getHibernateTemplate().delete(user);
			return true;
		
	}

	@Override
	public User login(String email, String password) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class)
				.add(Restrictions.eq("email", email))
				.add(Restrictions.eq("password", password))
				.add(Restrictions.eq("status", UserStatus.ON.name()));

		List<?> users = getHibernateTemplate().findByCriteria(criteria);
		return (User) users.get(0);
		
	}

	@Override
	public Integer count() throws Exception {
		return DataAccessUtils.intResult(getHibernateTemplate().find(
				"select count(*) from User"));
	}

	@Override
	public User findByEmail(String email) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class)
				.add(Restrictions.eq("email", email));
		List<?> users = getHibernateTemplate().findByCriteria(criteria);
		return (User) users.get(0);
	}

	@Override
	public Boolean update(User user) {
		getHibernateTemplate().update(user);
		return true;
		
	}

	@Override
	public User firstLogin(String email, String password) {
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class)
				.add(Restrictions.eq("email", email))
				.add(Restrictions.eq("password", password))
				.add(Restrictions.eq("status", UserStatus.STANDBY.name()));

		List<?> users = getHibernateTemplate().findByCriteria(criteria);
		return (User) users.get(0);
		
	}

}
