package com.paolorizzo.xmlsoccer.dao.hibernate.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Odd;
import com.paolorizzo.xmlsoccer.dao.facade.OddDao;

public class OddDaoImpl extends HibernateDaoSupport implements OddDao {

	@Override
	public void insert(XmlSoccer_Odd odd) {
		getHibernateTemplate().save(odd);

	}

	@Override
	public List<XmlSoccer_Odd> list() {
		return (List<XmlSoccer_Odd>) getHibernateTemplate().find(
				"from XmlSoccer_Odd");
	}

	@Override
	public void delete(XmlSoccer_Odd xmlSoccer_Odd) {
		getHibernateTemplate().delete(xmlSoccer_Odd);
	}

	@Override
	public void update(XmlSoccer_Odd xmlSoccer_Odd) {
		getHibernateTemplate().update(xmlSoccer_Odd);
	}

	@Override
	public XmlSoccer_Odd getById(String id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				XmlSoccer_Odd.class).add(Restrictions.eq("id", id));

		return ((List<XmlSoccer_Odd>) getHibernateTemplate().findByCriteria(
				criteria)).get(0);
	}

	@Override
	public List<XmlSoccer_Odd> getOddsByFixtureId(String id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(
				XmlSoccer_Odd.class).add(
				Restrictions.eq("fixture.id", Integer.parseInt(id)));

		return ((List<XmlSoccer_Odd>) getHibernateTemplate().findByCriteria(
				criteria));
	}

}
