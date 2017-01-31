package com.paolorizzo.predictor.dao.hibernate.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.AccountDao;
import com.paolorizzo.predictor.hibernate.model.Account;

public class AccountDaoImpl extends HibernateDaoSupport implements AccountDao {

	@Override
	public void insert(Account account) {
		getHibernateTemplate().save(account);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> list() {
		return (List<Account>) getHibernateTemplate().find(
				"from Account");
	}

	@Override
	public void delete(Account account) {
		getHibernateTemplate().delete(account);

	}

	@Override
	public void update(Account account) {
		getHibernateTemplate().update(account);

	}

	@SuppressWarnings("unchecked")
	@Override
	public Account getByClass(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				Account.class).add(Restrictions.eq("className", name));

		List<?> accounts = getHibernateTemplate().findByCriteria(
				criteria);
		return ((List<Account>) accounts).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> list(String email) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				Account.class).add(Restrictions.eq("user.email", email));
		List<?> accounts = getHibernateTemplate().findByCriteria(
				criteria);
		return (List<Account>) accounts;
	}

	@Override
	public Account get(String name, String email) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				Account.class).add(Restrictions.eq("user.email", email))
				.add(Restrictions.eq("name", name));
		Account account =  (Account) (getHibernateTemplate().findByCriteria(
				criteria)).get(0);
		return account;
	}

}
