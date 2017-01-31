package com.paolorizzo.xmlsoccer.dao.hibernate.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_HistoricMatch;
import com.paolorizzo.xmlsoccer.dao.facade.HistoricMatchDao;

public class HistoricMatchDaoImpl extends HibernateDaoSupport implements
		HistoricMatchDao {

	@Override
	public void insert(XmlSoccer_HistoricMatch historicMatch) {
		getHibernateTemplate().save(historicMatch);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XmlSoccer_HistoricMatch> list() {
		return (List<XmlSoccer_HistoricMatch>) getHibernateTemplate().find(
				"from XmlSoccer_HistoricMatch");
	}

	@Override
	public void delete(XmlSoccer_HistoricMatch xmlSoccer_HistoricMatch) {
		getHibernateTemplate().delete(xmlSoccer_HistoricMatch);
	}

	@Override
	public void update(XmlSoccer_HistoricMatch xmlSoccer_HistoricMatch) {
		getHibernateTemplate().update(xmlSoccer_HistoricMatch);
	}

	@SuppressWarnings("unchecked")
	@Override
	public XmlSoccer_HistoricMatch getById(String id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				XmlSoccer_HistoricMatch.class).add(Restrictions.eq("id", id));

		return ((List<XmlSoccer_HistoricMatch>) getHibernateTemplate()
				.findByCriteria(criteria)).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XmlSoccer_HistoricMatch> getHistoricMatchesByLeagueAndSeason(
			String league, String season) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(XmlSoccer_HistoricMatch.class)
				.add(Restrictions.eq("league.id", Integer.parseInt(league)))
				.add(Restrictions.eq("season", season));

		return ((List<XmlSoccer_HistoricMatch>) getHibernateTemplate()
				.findByCriteria(criteria));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XmlSoccer_HistoricMatch> getHistoricMatchesByTeams(
			String homeTeamId, String awayTeamId) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(XmlSoccer_HistoricMatch.class)
				.add(Restrictions.eq("homeTeam.id",
						Integer.parseInt(homeTeamId)))
				.add(Restrictions.eq("awayTeam.id",
						Integer.parseInt(awayTeamId)));

		return ((List<XmlSoccer_HistoricMatch>) getHibernateTemplate()
				.findByCriteria(criteria));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XmlSoccer_HistoricMatch> getLast5(String teamId) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(XmlSoccer_HistoricMatch.class)
				.add(Restrictions.or(Restrictions.eq("homeTeam.id",
						Integer.parseInt(teamId)), Restrictions.eq(
						"awayTeam.id", Integer.parseInt(teamId))))

				.addOrder(Order.desc("date"));

		List<XmlSoccer_HistoricMatch> list = ((List<XmlSoccer_HistoricMatch>) getHibernateTemplate()
				.findByCriteria(criteria)).subList(0, 5);
		return list;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<XmlSoccer_HistoricMatch> getSeasonStats(String homeTeamId,
			String awayTeamId, String league, String season) {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(XmlSoccer_HistoricMatch.class)
				.add(Restrictions.or(
						Restrictions.eq("homeTeam.id",
								Integer.parseInt(homeTeamId)),
						Restrictions.eq("awayTeam.id",
								Integer.parseInt(awayTeamId))))
				.add(Restrictions.eq("league.id", Integer.parseInt(league)))
				.add(Restrictions.eq("season", season));
		;
		// .addOrder(Order.desc("date"));

		List<XmlSoccer_HistoricMatch> list = ((List<XmlSoccer_HistoricMatch>) getHibernateTemplate()
				.findByCriteria(criteria));
		return list;

	}
}
