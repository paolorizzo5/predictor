package com.paolorizzo.predictor.business;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.dao.facade.BettypeDao;
import com.paolorizzo.predictor.dao.facade.BettypeGroupDao;
import com.paolorizzo.predictor.hibernate.model.Bettype;
import com.paolorizzo.predictor.hibernate.model.BettypeGroup;
import com.paolorizzo.predictor.utils.BettypeUtils;

public class BettypeGroupBusiness {

	static Logger logger = LogManager
			.getLogger(BettypeGroupBusiness.class.getName());

	@Autowired
	private BettypeGroupDao bettypeGroupDao;
	
	@Autowired
	private FinalScoreBusiness finalScoreBusiness;


	public BettypeGroupBusiness() {
		// TODO Auto-generated constructor stub
	}
	
	

	public BettypeGroupBusiness(BettypeGroupDao bettypeGroupDao,
			FinalScoreBusiness finalScoreBusiness) {
		super();
		this.bettypeGroupDao = bettypeGroupDao;
		this.finalScoreBusiness = finalScoreBusiness;
	}



	@Transactional(readOnly = false)
	public void configure() {
		logger.debug("Calling BetType configure");
		List<BettypeGroup> bettypeGroups = BettypeUtils.createDefaultGroups(finalScoreBusiness
				.list());

		for (BettypeGroup bettypeGroup : bettypeGroups) {
			bettypeGroupDao.insert(bettypeGroup);
		}

	}

	public List<BettypeGroup> list() {
		return bettypeGroupDao.list();
	}

	public boolean isEmpty() {
		return bettypeGroupDao.list().size() == 0;
	}

}
