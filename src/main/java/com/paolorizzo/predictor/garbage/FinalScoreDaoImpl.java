package com.paolorizzo.predictor.dao.hibernate.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.paolorizzo.predictor.dao.facade.FinalScoreDao;
import com.paolorizzo.predictor.hibernate.model.FinalScore;

public class FinalScoreDaoImpl extends HibernateDaoSupport implements
		FinalScoreDao {

	@Override
	public void insert(FinalScore finalScore) {
		try {
			getHibernateTemplate().save(finalScore);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public List<FinalScore> list() {
		return (List<FinalScore>) getHibernateTemplate()
				.find("from FinalScore");
	}

	@Override
	public FinalScore getByScore(Integer homeGoals, Integer awayGoals) {
		DetachedCriteria criteria = DetachedCriteria.forClass(FinalScore.class)
				.add(Restrictions.eq("homeGoals", homeGoals))
				.add(Restrictions.eq("awayGoals", awayGoals));

		List<?> finalScores = getHibernateTemplate().findByCriteria(criteria);
		if (finalScores.size() == 0) {
			return new FinalScore(0, 0);
		}
		return (FinalScore) finalScores.get(0);
	}

}
