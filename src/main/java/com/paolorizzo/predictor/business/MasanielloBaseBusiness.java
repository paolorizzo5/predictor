package com.paolorizzo.predictor.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.paolorizzo.predictor.dao.facade.DirettaFixtureDao;
import com.paolorizzo.predictor.hibernate.model.DirettaFixture;
import com.paolorizzo.predictor.hibernate.model.Masaniello;
import com.paolorizzo.predictor.hibernate.model.MasanielloPlan;
import com.paolorizzo.predictor.hibernate.model.MasanielloRound;
import com.paolorizzo.predictor.hibernate.model.User;
import com.paolorizzo.predictor.services.request.PlanFilterDto;
import com.paolorizzo.predictor.utils.DateUtils;

public class MasanielloBaseBusiness {
	
	@Autowired
	private DirettaFixtureDao direttaFixtureDao;
	
	@Autowired
	private MasanielloPlanBusiness masanielloPlanBusiness;
	
	@Autowired
	private BetMatcherBusiness betMatcherBusiness;
	
	@Autowired
	DateUtils dateUtils;
	
	
	Logger logger = LogManager.getLogger("dino");
	

	public MasanielloBaseBusiness(DirettaFixtureDao direttaFixtureDao, MasanielloPlanBusiness masanielloPlanBusiness,
			BetMatcherBusiness betMatcherBusiness, DateUtils dateUtils) {
		super();
		this.direttaFixtureDao = direttaFixtureDao;
		this.masanielloPlanBusiness = masanielloPlanBusiness;
		this.betMatcherBusiness = betMatcherBusiness;
		this.dateUtils = dateUtils;
	}

	public Boolean createMasaniello(List<PlanFilterDto> filters,String masanielloUserEmail, 
			BigDecimal masanielloAmount, Integer masanielloEventToWin, String masanielloName,  
			BigDecimal masanielloAverageQuote, BigDecimal masanielloAdditionalQuote, BigDecimal masanielloPercentage, Integer masanielloRounds, BigDecimal patrimonyPercentage, Integer id
			) throws ParseException {
		
		try{
			MasanielloPlan masanielloPlan = masanielloPlanBusiness.getPlan(masanielloUserEmail, masanielloName);
			if(masanielloPlan != null){
				asyncCreateMasaniello(masanielloPlan,masanielloName,masanielloUserEmail,masanielloEventToWin,
						masanielloAmount,masanielloPercentage,masanielloRounds,
						masanielloAverageQuote,masanielloAdditionalQuote,patrimonyPercentage,filters,id);
			}
			return true;
		}catch (Exception exception){
			logger.error("CreateMasanielloException",exception);
			return false;
		}
	}
	
	@Async
	private void asyncCreateMasaniello(
			MasanielloPlan masanielloPlan,
			String msanielloName,
			String masanielloUserEmail,
			Integer masanielloEventToWin, 
			BigDecimal masanielloAmount, 
			BigDecimal masanielloPercentage, 
			Integer masanielloRounds,
			BigDecimal masanielloAverageQuote, 
			BigDecimal masanielloAdditionalQuote,
			BigDecimal patrimonyPercentage, 
			List<PlanFilterDto> filters, Integer id) {
		List<Masaniello> masaniellos = new ArrayList<Masaniello>();
		
		try{
			Masaniello masaniello = initializeMasaniello(msanielloName, masanielloUserEmail, id, masanielloEventToWin, masanielloAmount, masanielloPercentage, masanielloRounds, masanielloAverageQuote, masanielloAdditionalQuote);
			BigDecimal initialAmount = masanielloAmount;
			BigDecimal amountToInvest = masanielloAmount.multiply(patrimonyPercentage).divide(new BigDecimal(100),2,RoundingMode.FLOOR);
			
			Integer matches = 0;
			Integer roundId = 1;
			Integer wins = 0;
			
			getDirettaFixturesAndApplyToFiter(filters);
			Collections.sort(filters);
			
			List<DirettaFixture> direttaFixtures = filters.get(0).getDirettaFixtures();
			List<DirettaFixture> roundFixtures = new ArrayList<DirettaFixture>();
			
			for (int i = 0;i<direttaFixtures.size();i++){
				logger.debug(direttaFixtures.get(i).toString());
				roundFixtures.add(direttaFixtures.get(i));
				matches++;
				if(matches <= masanielloRounds){
					if(isWinningEvent(filters,i)){
						wins++;
					}else{
						
					}
					
					if(wins == masanielloEventToWin){
						wins = 0;
						
						MasanielloRound masanielloRound = creaRoundVincente(initialAmount, amountToInvest, masanielloPercentage, matches, roundId, masaniello.getName(), masaniello.getId(), masanielloUserEmail);
						aggiornaIndici(initialAmount, masanielloRound.getFinalAmount(), matches, roundId,amountToInvest,patrimonyPercentage);
						//masanielloRound.setDirettaFixtures(roundFixtures);
						roundFixtures = new ArrayList<DirettaFixture>();
						masaniello.getMasanielloRounds().add(masanielloRound);
						
					}else if(matches == masanielloRounds){
						MasanielloRound masanielloRound = creaRoundPerdente(initialAmount, amountToInvest, matches, roundId,masaniello.getName(),masaniello.getId(),masanielloUserEmail);
						aggiornaIndici(initialAmount, masanielloRound.getFinalAmount(), matches, roundId,amountToInvest,patrimonyPercentage);
						//masanielloRound.setDirettaFixtures(roundFixtures);
						roundFixtures = new ArrayList<DirettaFixture>();
						masaniello.getMasanielloRounds().add(masanielloRound);
						wins = 0;
						
					}
				}
			}
			
			masaniellos.add(masaniello);
			masanielloPlan.setMasaniellos(masaniellos);
			masanielloPlanBusiness.update(masanielloPlan);
		}catch (Exception exception){
			logger.error("exception while creating masanielllo",exception);
		}
		
	}
	
	private void aggiornaIndici(BigDecimal initialAmount,BigDecimal finalAmount, Integer matches,Integer roundId, BigDecimal amountToInvest,BigDecimal patrimonyPercentage) {
		initialAmount = finalAmount;
		matches = 0;
		roundId++;
		amountToInvest = initialAmount.multiply(patrimonyPercentage).divide(new BigDecimal(100),2,RoundingMode.FLOOR);
	}

	private MasanielloRound creaRoundPerdente(BigDecimal initialAmount,BigDecimal amountToInvest,Integer matches,Integer roundId, String masanielloName, Integer masanielloId, String masanielloUserEmail) {
		MasanielloRound masanielloRound = new MasanielloRound();
		masanielloRound.setInitialAmount(initialAmount);
		masanielloRound.setFinalAmount(initialAmount.subtract(amountToInvest));
		masanielloRound.setMatches(matches);
		masanielloRound.setRoundId(roundId);
		masanielloRound.setSuccess(false);
		masanielloRound.setMasaniello(new Masaniello(masanielloName,masanielloId,new MasanielloPlan(masanielloName, new User(masanielloUserEmail))));
		
		return masanielloRound;
	}

	private MasanielloRound creaRoundVincente(
			BigDecimal initialAmount,
			BigDecimal amountToInvest,
			BigDecimal masanielloPercentage,
			Integer matches,
			Integer roundId,
			String masanielloName,
			Integer masanielloId,
			String masanielloUserEmail
			) {
		MasanielloRound masanielloRound = new MasanielloRound();
		masanielloRound.setInitialAmount(initialAmount);
		masanielloRound.setFinalAmount(initialAmount.add(amountToInvest.multiply(masanielloPercentage).subtract(amountToInvest)));
		masanielloRound.setMatches(matches);
		masanielloRound.setRoundId(roundId);
		
		masanielloRound.setSuccess(true);
		masanielloRound.setPercentageInvested(masanielloPercentage);
		masanielloRound.setMasaniello(new Masaniello(masanielloName,masanielloId, new MasanielloPlan(masanielloName, new User(masanielloUserEmail))));
		masanielloRound.setPercentageInvested(masanielloPercentage);
		return masanielloRound;
	}

	//questo se giochi la multipla in modalità base
	private boolean isWinningEvent(List<PlanFilterDto> filters,int index) {
		int filtersSize = filters.size();
		boolean isWinningEvent = true;
		for (int i = 0;i<filtersSize; i++){
			isWinningEvent = isWinningEvent && betMatcherBusiness.isWinningEvent(filters.get(i).getDirettaFixtures().get(index).getHomeGoals(),filters.get(i).getDirettaFixtures().get(index).getAwayGoals(),filters.get(i).getBetType());
		}
		return isWinningEvent;
	}

	private void getDirettaFixturesAndApplyToFiter(List<PlanFilterDto> filters) {
		for (PlanFilterDto filter : filters) {
			
			filter.setDateFrom(dateUtils.truncYesterdayDate(filter.getDateFrom()));
			filter.setDateTo(dateUtils.truncTomorrowDate(filter.getDateTo()));
			
			List<DirettaFixture> direttaFixtures = direttaFixtureDao.getDirettaFixtures(filter.getCompetition(),filter.getHomeTeam(),filter.getAwayTeam(),filter.getQuota1From(),filter.getQuota1To(),filter.getQuotaXFrom(),filter.getQuotaXTo(),filter.getQuota2From(),filter.getQuota2To(),filter.getDateFrom(),filter.getDateTo(),null,true);
			filter.setDirettaFixtures(direttaFixtures);
		}
	}
	
	private Masaniello initializeMasaniello(
			String msanielloName,
			String masanielloUserEmail,
			Integer id,
			Integer masanielloEventToWin,
			BigDecimal masanielloAmount,
			BigDecimal masanielloPercentage,
			Integer masanielloRounds,
			BigDecimal masanielloAverageQuote,
			BigDecimal masanielloAdditionalQuote
			) {
		Masaniello masaniello = new Masaniello();
		masaniello.setMasanielloPlan(new MasanielloPlan(msanielloName,new User(masanielloUserEmail)));
		masaniello.setName(msanielloName);
		masaniello.setId(id);
		masaniello.setEventToWin(masanielloEventToWin);
		masaniello.setAmount(masanielloAmount);
		masaniello.setPercentage(masanielloPercentage);
		masaniello.setRounds(masanielloRounds);
		
		masaniello.setAverageQuote(masanielloAverageQuote);
		masaniello.setAdditionalQuote(masanielloAdditionalQuote);
		masaniello.setMasanielloRounds(new ArrayList<MasanielloRound>());
		return masaniello;
	}

}
