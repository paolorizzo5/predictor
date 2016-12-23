package com.paolorizzo.predictor.dao.hibernate.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.UserDao;
import com.paolorizzo.predictor.hibernate.model.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public boolean insert(User user) throws Exception {
		try {
			getHibernateTemplate().save(user);
			return true;
		} catch (Exception exception) {
			return false;
		}

	}

	@Override
	public boolean delete(User user) throws Exception {
		try {
			getHibernateTemplate().delete(user);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	@Override
	public User login(String email, String password) throws Exception {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(User.class)
					.add(Restrictions.eq("email", email))
					.add(Restrictions.eq("password", password));

			List<?> users = getHibernateTemplate().findByCriteria(criteria);
			return (User) users.get(0);
		} catch (Exception exception) {
			return null;
		}
	}

	@Override
	public Integer count() throws Exception {
		return DataAccessUtils.intResult(getHibernateTemplate().find(
				"select count(*) from User"));
	}

	@Override
	public User findByEmail(String email) {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(User.class)
					.add(Restrictions.eq("email", email));
			List<?> users = getHibernateTemplate().findByCriteria(criteria);
			return (User) users.get(0);
		} catch (Exception exception) {
			return null;
		}
	}

}
