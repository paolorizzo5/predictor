package com.paolorizzo.predictor.business;

import org.apache.log4j.varia.FallbackErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.dao.facade.MasanielloDao;
import com.paolorizzo.predictor.hibernate.model.Masaniello;

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
	
	
	

}
