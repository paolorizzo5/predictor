package com.paolorizzo.predictor.dao.hibernate.impl;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.TeamStatsDao;
import com.paolorizzo.predictor.hibernate.model.TeamStats;

public class TeamStatsDaoImpl extends HibernateDaoSupport implements
		TeamStatsDao {

	@Override
	public void insert(TeamStats teamStats) {
		try {
			getHibernateTemplate().save(teamStats);
		} catch (Exception exception) {
			logger.error(teamStats.toString());
		}

	}

	@Override
	public List<TeamStats> list() {
		return (List<TeamStats>) getHibernateTemplate().find("from TeamStats");
	}

	@Override
	public void delete(TeamStats teamStats) {
		getHibernateTemplate().delete(teamStats);
	}

	@Override
	public void update(TeamStats teamStats) {
		getHibernateTemplate().update(teamStats);
	}

	@Override
	public void clear() {
		getHibernateTemplate().deleteAll(list());

	}

}
