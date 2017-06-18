package com.paolorizzo.predictor.business;

import java.util.List;

import org.apache.log4j.varia.FallbackErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.dao.facade.MasanielloDao;
import com.paolorizzo.predictor.hibernate.model.Masaniello;
import com.paolorizzo.predictor.hibernate.model.MasanielloRound;

public class MasanielloBusiness {
	
	@Autowired
	private MasanielloDao masanielloDao;

	public MasanielloBusiness(MasanielloDao masanielloDao) {
		super();
		this.masanielloDao = masanielloDao;
	}

	@Transactional(readOnly=false)
	public void add(Masaniello masaniello) {
		masanielloDao.insert(masaniello);
	}

	public List<MasanielloRound> getRounds(String name, String email) {
		Masaniello masaniello = getMasaniello(name,email);
		return masaniello.getMasanielloRounds();
	}

	public Masaniello getMasaniello(String name, String email) {
		return masanielloDao.find(name,email);
	}
	
	
	

}
