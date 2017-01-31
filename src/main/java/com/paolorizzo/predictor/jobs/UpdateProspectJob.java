package com.paolorizzo.predictor.jobs;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.paolorizzo.predictor.business.AccountBusiness;
import com.paolorizzo.predictor.business.JobConfigurationBusiness;
import com.paolorizzo.predictor.business.ProspectBusiness;
import com.paolorizzo.predictor.enums.BetStatus;
import com.paolorizzo.predictor.hibernate.model.Account;
import com.paolorizzo.predictor.hibernate.model.Bet;
import com.paolorizzo.predictor.hibernate.model.Prospect;
import com.paolorizzo.predictor.hibernate.model.ProspectElement;
import com.paolorizzo.predictor.spring.AppContext;

public class UpdateProspectJob implements Job{

	private AccountBusiness accountBusiness;
	
	private ProspectBusiness prospectBusiness;
	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		accountBusiness = AppContext.getApplicationContext().getBean(
				"accountBusinessBean", AccountBusiness.class);

		
		List<Account> accounts =  accountBusiness.list();
		for (Account account : accounts) {
			Prospect prospect =  account.getProspect();
			BigDecimal initialAmount = prospect.getInitialAmount();
			for (ProspectElement prospectElement : prospect.getProspectElements()) {
				if(prospectElement.getAmountAchieved() == null && prospectElement.getEndDate().compareTo(new Date()) == -1){
					BigDecimal earnings = new BigDecimal(0);
					BigDecimal totalExpense = new BigDecimal(0);
					
					
					for (Bet bet : account.getBets()) {
						if (BetStatus.WINNING.getText().equals(bet.getBetStatus())){
							earnings = earnings.add(bet.getMoltiplicator().multiply(new BigDecimal(bet.getAmount())));
							totalExpense = totalExpense.add(new BigDecimal(bet.getAmount()));
						}else if (BetStatus.LOSING.getText().equals(bet.getBetStatus())){
							totalExpense = totalExpense.add(new BigDecimal(bet.getAmount()));
						}
					}
					
					BigDecimal amountAchieved = initialAmount.subtract(totalExpense).add(earnings);
					prospectElement.setAmountAchieved(amountAchieved);
				}else{
					initialAmount = prospectElement.getAmountAchieved();
				}
			}
			
			
			prospectBusiness.update(prospect);
			
		}
	}
	
	
	
	
	

}
