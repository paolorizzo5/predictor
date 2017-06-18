package com.paolorizzo.predictor.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.dao.facade.MasanielloPlanDao;
import com.paolorizzo.predictor.hibernate.model.Masaniello;
import com.paolorizzo.predictor.hibernate.model.MasanielloPlan;
import com.paolorizzo.predictor.hibernate.model.User;

public class MasanielloPlanBusiness {
	
	@Autowired
	private MasanielloPlanDao masanielloPlanDao;
	
	

	public MasanielloPlanBusiness(MasanielloPlanDao masanielloPlanDao) {
		super();
		this.masanielloPlanDao = masanielloPlanDao;
	}



	@Transactional(readOnly=false)
	public void add(MasanielloPlan masanielloPlan) {
		masanielloPlanDao.insert(masanielloPlan);
	}

	public List<MasanielloPlan> list(String email) {
		return masanielloPlanDao.list(email);
	}

	@Transactional(readOnly=false)
	public Boolean delete(String email, String name) {
		try{
			MasanielloPlan masanielloPlan = new MasanielloPlan(name, new User(email));
			masanielloPlan.setMasaniellos(null);
			masanielloPlanDao.update(masanielloPlan);
			masanielloPlanDao.delete(masanielloPlan);
			return true;
		}catch (Exception exception){
			return false;
		}
		
	}

	public List<String> getPlansNames(String email) {
		List<String> list = new ArrayList<String>();
		for (MasanielloPlan masanielloPlan : masanielloPlanDao.list(email)) {
			list.add(masanielloPlan.getName());
		}
		return list;
	}

	public MasanielloPlan getPlan(String email, String name) {
		MasanielloPlan plan = masanielloPlanDao.getPlan(email,name);
		return plan;
	}


	@Transactional(readOnly=false)
	public void merge(MasanielloPlan masanielloPlan) {
		masanielloPlanDao.merge(masanielloPlan);
		
	}

	@Transactional(readOnly=false)
	public void update(MasanielloPlan masanielloPlan) {
		masanielloPlanDao.update(masanielloPlan);
	}

}
