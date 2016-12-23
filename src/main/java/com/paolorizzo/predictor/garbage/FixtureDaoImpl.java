package com.paolorizzo.predictor.dao.hibernate.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.paolorizzo.predictor.constant.FixtureStatus;
import com.paolorizzo.predictor.dao.facade.FixtureDao;
import com.paolorizzo.predictor.enums.HomeAwayEnum;
import com.paolorizzo.predictor.hibernate.model.Competition;
import com.paolorizzo.predictor.hibernate.model.Fixture;
import com.paolorizzo.predictor.hibernate.model.Team;

public class FixtureDaoImpl extends HibernateDaoSupport implements FixtureDao {

	@Override
	public void insert(Fixture fixture) {
		getHibernateTemplate().save(fixture);

	}

	@Override
	public List<Fixture> findByStatusAndDate(String status, long today,
			long tomorrow) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Fixture.class)
				.add(Restrictions.eq("status", status))
				.add(Restrictions.between("date", today, tomorrow));

		List<?> fixtures = getHibernateTemplate().findByCriteria(criteria);
		return (List<Fixture>) fixtures;
	}

	@Override
	public void update(Fixture fixture) {
		getHibernateTemplate().update(fixture);
	}

	@Override
	public List<Fixture> list() {
		return (List<Fixture>) getHibernateTemplate().find("from Fixture");
	}

	@Override
	public List<Fixture> getByTeam(Team team, Competition competition,
			HomeAwayEnum atHome) {
		DetachedCriteria criteria = null;
		switch (atHome) {
		case ALL:
			criteria = DetachedCriteria
					.forClass(Fixture.class)
					.add(Restrictions.eq("status", FixtureStatus._FINISHED))
					.add(Restrictions.eq("competition.id", competition.getId()))
					.add(Restrictions.or(
							Restrictions.eq("homeTeamName", team.getName()),
							Restrictions.eq("awayTeamName", team.getName())));

			break;
		case HOME:
			criteria = DetachedCriteria
					.forClass(Fixture.class)
					.add(Restrictions.eq("status", FixtureStatus._FINISHED))
					.add(Restrictions.eq("competition.id", competition.getId()))
					.add(Restrictions.eq("homeTeamName", team.getName()));
			break;
		case AWAY:
			criteria = DetachedCriteria
					.forClass(Fixture.class)
					.add(Restrictions.eq("status", FixtureStatus._FINISHED))
					.add(Restrictions.eq("competition.id", competition.getId()))
					.add(Restrictions.eq("awayTeamName", team.getName()));
			break;

		default:
			break;
		}
		List<?> fixtures = getHibernateTemplate().findByCriteria(criteria);
		return (List<Fixture>) fixtures;

	}

	@Override
	public List<Fixture> findByStatusAndCompetition(String status, String id,Boolean hasLimit,Integer numrecords) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Fixture.class)
				.add(Restrictions.eq("status", status))
				.add(Restrictions.eq("competition.id", id))
				.addOrder(Order.desc("date"));
		
		if(hasLimit){
			try{
				List<?> fixtures = getHibernateTemplate().findByCriteria(criteria).subList(0, numrecords -1);
				return (List<Fixture>) fixtures;		
			}catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
				List<?> fixtures = getHibernateTemplate().findByCriteria(criteria);
				return (List<Fixture>) fixtures;
			}
			
		}else{
			List<?> fixtures = getHibernateTemplate().findByCriteria(criteria);
			return (List<Fixture>) fixtures;
		}
		

		
		
	}

	@Override
	public List<Fixture> findDaily(long today, long tomorrow) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Fixture.class)
				.add(Restrictions.between("date", today, tomorrow));

		List<?> fixtures = getHibernateTemplate().findByCriteria(criteria);
		return (List<Fixture>) fixtures;
	}
}
