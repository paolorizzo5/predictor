package com.paolorizzo.predictor.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.dao.facade.ProspectDao;
import com.paolorizzo.predictor.hibernate.model.Account;
import com.paolorizzo.predictor.hibernate.model.Prospect;
import com.paolorizzo.predictor.hibernate.model.ProspectElement;
import com.paolorizzo.predictor.hibernate.model.User;
import com.paolorizzo.predictor.utils.SimpleUtils;

public class ProspectBusiness {
	
	@Autowired
	private ProspectDao prospectDao;
	
	@Autowired
	private AccountBusiness accountBusiness;
	
	@Autowired
	private SimpleUtils simpleUtils;
	

	
	public ProspectBusiness(ProspectDao prospectDao, AccountBusiness accountBusiness,SimpleUtils simpleUtils) {
		super();
		this.prospectDao = prospectDao;
		this.accountBusiness = accountBusiness;
		this.simpleUtils = simpleUtils;
	}


	@Transactional(readOnly=false)
	public Prospect add(String name, String accountName, BigDecimal dailyPercentageExpected, Integer stepFrequency, Integer duration,
			String email,BigDecimal initialAmount) {
		Account account = accountBusiness.get(accountName,email);
		Prospect prospect = new Prospect();
		prospect.setAccount(account);
		prospect.setDailyPercentageExpected(dailyPercentageExpected);
		prospect.setDuration(duration);
		prospect.setInitialAmount(initialAmount);
		prospect.setInsertDate(new Date());
		prospect.setName(name);
		prospect.setStepFrequency(stepFrequency);
		prospect.setUser(new User(email));
		BigDecimal expectedGoal = initialAmount;
		Date startDate = new Date();
		
		List<ProspectElement> prospectElements = new ArrayList<ProspectElement>();
		
		for(int i = 1;i<=duration; i++){
			ProspectElement prospectElement = new ProspectElement();
			prospectElement.setEndDate(SimpleUtils.nextDate(startDate,stepFrequency));
			
			expectedGoal= expectedGoal.multiply((new BigDecimal(100).add(dailyPercentageExpected))).divide(new BigDecimal(100),RoundingMode.DOWN).setScale(2, RoundingMode.DOWN);
			
			//expectedGoal = expectedGoal.multiply(new BigDecimal(100).add(prospect.getDailyPercentageExpected()).divide(new BigDecimal(100),RoundingMode.DOWN));
			prospectElement.setExpectedGoal(expectedGoal);
			prospectElement.setIncremental(i);
			prospectElement.setStartDate(startDate);
			startDate = SimpleUtils.nextDate(startDate,stepFrequency);
			prospectElement.setProspect(prospect);
			prospectElements.add(prospectElement);
			
		}
		
		prospect.setProspectElements(prospectElements);
		prospectDao.insert(prospect);
		
		//accountBusiness.deposit(account.getName(), initialAmount.toPlainString(), email);
		
		return prospect;
	}


	public Prospect get(String accountName, String email) {
		Prospect prospect = prospectDao.get(accountName,email);
		
		
		return prospect;
	}


	public void update(Prospect prospect) {
		prospectDao.update(prospect);
	}

	@Transactional(readOnly=false)
	public Prospect pushProspectElement(String accountName, String email, Integer incremental, String prospectName) {
		Account account = accountBusiness.get(accountName, email);
		Date now = new Date();
		Date startDate = new Date();
		
		
		Boolean updateUpcomingElements = false;
		Boolean getNextElement = false;
		for (ProspectElement prospectElement : account.getProspect().getProspectElements()) {
			
			
			if (updateUpcomingElements){
				prospectElement.setEndDate(simpleUtils.tomorrow(startDate));
				prospectElement.setStartDate(startDate);
				startDate = simpleUtils.tomorrow(startDate);
			}
			
			if(getNextElement){
				getNextElement = false;
			}
			
			if(account.getLiveAmount().compareTo(prospectElement.getExpectedGoal()) > 0 && prospectElement.getTerminationDate() == null){
				prospectElement.setTerminationDate(now);
				prospectElement.setEndDate(now);
				updateUpcomingElements = true;
				getNextElement = true;
				
			}
		}
		
		prospectDao.update(account.getProspect());
		return (account.getProspect());
		
	}

	@Transactional(readOnly=false)
	public Prospect popProspectElement(String accountName, String email, Integer incremental, String prospectName) {
		Account account = accountBusiness.get(accountName, email);
		Date now = new Date();
		Date startDate = new Date();
		boolean changeDate = false;
		boolean updateLiveAmount = false;
		
		Date startDateToRestore = null;
		Integer indexToRestore = null;
		
		for (int i = 0;i<account.getProspect().getProspectElements().size();i++){
			ProspectElement prospectElement = account.getProspect().getProspectElements().get(i);
			
			if(changeDate){
				if(updateLiveAmount){
					updateLiveAmount = false;
					startDateToRestore = account.getProspect().getProspectElements().get(i).getStartDate();
					indexToRestore = i;
				}
				
				account.getProspect().getProspectElements().get(i).setEndDate(simpleUtils.tomorrow(startDate));
				account.getProspect().getProspectElements().get(i).setStartDate(startDate);
				account.getProspect().getProspectElements().get(i).setTerminationDate(null);
				startDate = simpleUtils.tomorrow(startDate);
				
			}
			
			
			if(prospectElement.getEndDate().compareTo(now) > 0 && prospectElement.getStartDate().compareTo(now) < 0){
				i = i-2;
				changeDate = true;
				updateLiveAmount = true;
				if(i >= 0){
					account.getProspect().getProspectElements().get(i).setEndDate(now);
				}
				
			}
			
			
		}
		
		account.getProspect().getProspectElements().get(indexToRestore).setStartDate(startDateToRestore);
		
		prospectDao.update(account.getProspect());
		return (account.getProspect());
	}


	public SimpleUtils getSimpleUtils() {
		return simpleUtils;
	}


	public void setSimpleUtils(SimpleUtils simpleUtils) {
		this.simpleUtils = simpleUtils;
	}

}
