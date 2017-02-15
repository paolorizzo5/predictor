package com.paolorizzo.predictor.jobs;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.paolorizzo.predictor.business.AccountBusiness;
import com.paolorizzo.predictor.hibernate.model.Account;
import com.paolorizzo.predictor.spring.AppContext;

public class AccountStatsJob implements Job{
	
	private AccountBusiness accountBusiness;
	

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		accountBusiness = AppContext.getApplicationContext().getBean(
				"accountBusinessBean", AccountBusiness.class);

		
		List<Account> accounts =  accountBusiness.list();
		for (Account account : accounts) {
			accountBusiness.addStatistic(account);
		}
		
	}

}
