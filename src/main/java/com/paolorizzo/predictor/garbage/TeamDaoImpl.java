package com.paolorizzo.predictor.dao.hibernate.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.TeamDao;
import com.paolorizzo.predictor.hibernate.model.Team;

public class TeamDaoImpl extends HibernateDaoSupport implements TeamDao {

	@Override
	public void insert(Team team) {
		try {
			getHibernateTemplate().save(team);
		} catch (Exception exception) {
			System.out.println("ERROR");
		}
	}

	@Override
	public Team getByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Team team) {
		getHibernateTemplate().update(team);

	}

	@Override
	public Team find(String name, String id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Team.class)
				.add(Restrictions.eq("code", name))
				.add(Restrictions.eq("competition.id", id));
		List<?> teams = getHibernateTemplate().findByCriteria(criteria);
		return ((List<Team>) teams).get(0);

	}

}
