package com.paolorizzo.predictor.business;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.dao.facade.BettypeDao;
import com.paolorizzo.predictor.hibernate.model.Bettype;
import com.paolorizzo.predictor.utils.BettypeUtils;

public class BettypeBusiness {

	static Logger logger = LogManager
			.getLogger(BettypeBusiness.class.getName());

	@Autowired
	private BettypeDao bettypeDao;

	@Autowired
	private FinalScoreBusiness finalScoreBusiness;

	public BettypeBusiness() {
		// TODO Auto-generated constructor stub
	}

	public BettypeBusiness(BettypeDao bettypeDao,
			FinalScoreBusiness finalScoreBusiness) {
		super();
		this.bettypeDao = bettypeDao;
		this.finalScoreBusiness = finalScoreBusiness;
	}

	public BettypeDao getBettypeDao() {
		return bettypeDao;
	}

	public void setBettypeDao(BettypeDao bettypeDao) {
		this.bettypeDao = bettypeDao;
	}

	public boolean isEmpty() {
		return bettypeDao.list().size() == 0;
	}

	@Transactional(readOnly = false)
	public void configure() {
		logger.debug("Calling BetType configure");
		List<Bettype> bettypes = BettypeUtils.createDefault(finalScoreBusiness
				.list());

		for (Bettype bettype : bettypes) {
			bettypeDao.insert(bettype);
		}

	}

	@Cacheable("bettypeCache")
	public List<Bettype> list() {
		return bettypeDao.list();
	}

}
