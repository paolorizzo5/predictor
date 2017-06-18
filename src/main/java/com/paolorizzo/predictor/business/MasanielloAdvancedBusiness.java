package com.paolorizzo.predictor.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.paolorizzo.predictor.constants.BettypeConstants;
import com.paolorizzo.predictor.dao.facade.DirettaFixtureDao;
import com.paolorizzo.predictor.hibernate.model.DirettaFixture;
import com.paolorizzo.predictor.hibernate.model.Masaniello;
import com.paolorizzo.predictor.hibernate.model.MasanielloPlan;
import com.paolorizzo.predictor.hibernate.model.MasanielloRound;
import com.paolorizzo.predictor.hibernate.model.User;
import com.paolorizzo.predictor.services.request.PlanFilterDto;
import com.paolorizzo.predictor.utils.DateUtils;

public class MasanielloAdvancedBusiness {
	
	@Autowired
	private DirettaFixtureDao direttaFixtureDao;
	
	@Autowired
	private MasanielloPlanBusiness masanielloPlanBusiness;
	
	@Autowired
	private BetMatcherBusiness betMatcherBusiness;
	
	@Autowired
	DateUtils dateUtils;
	
	
	Logger logger = LogManager.getLogger("dino");
	
	
	
	public MasanielloAdvancedBusiness(DirettaFixtureDao direttaFixtureDao,
			MasanielloPlanBusiness masanielloPlanBusiness, BetMatcherBusiness betMatcherBusiness, DateUtils dateUtils) {
		super();
		this.direttaFixtureDao = direttaFixtureDao;
		this.masanielloPlanBusiness = masanielloPlanBusiness;
		this.betMatcherBusiness = betMatcherBusiness;
		this.dateUtils = dateUtils;
	}

	public Boolean createMasanielloAdvanced(List<PlanFilterDto> filters,String masanielloUserEmail, 
			BigDecimal masanielloAmount, String masanielloName,Integer masanielloRounds, BigDecimal patrimonyPercentage, BigDecimal lowerByWin, BigDecimal raiseByLoss, Integer id
			) throws ParseException {
		
		try{
			MasanielloPlan masanielloPlan = masanielloPlanBusiness.getPlan(masanielloUserEmail, masanielloName);
			if(masanielloPlan != null){
				asyncCreateMasanielloAdvanced(masanielloPlan,masanielloName,masanielloUserEmail,masanielloAmount,masanielloRounds,patrimonyPercentage,lowerByWin,raiseByLoss,filters,id);
			}
			return true;
		}catch (Exception exception){
			logger.error("CreateMasanielloException",exception);
			return false;
		}
	}

	@Async
	private void asyncCreateMasanielloAdvanced(
			MasanielloPlan masanielloPlan,
			String masanielloName,
			String masanielloUserEmail,
			BigDecimal masanielloAmount, 
			Integer masanielloRounds,
			BigDecimal patrimonyPercentage, 
			BigDecimal lowerByWin, 
			BigDecimal raiseByLoss, 
			List<PlanFilterDto> filters, Integer id) {
		
		List<Masaniello> masaniellos = new ArrayList<Masaniello>();

		try{

			Masaniello masaniello = initializeMasaniello(masanielloName,masanielloUserEmail,id,1,masanielloAmount,masanielloRounds);
			BigDecimal initialPatrimonyPercentage = patrimonyPercentage;
			BigDecimal initialAmount = masanielloAmount;
			
			//esempio : masaniello 1000 euro, percentuale da investire 20% = 200 euro
			BigDecimal amountToInvest = masanielloAmount.multiply(patrimonyPercentage).divide(new BigDecimal(100),2,RoundingMode.FLOOR);
			
			Integer matches = 0;
			Integer roundId = 1;
			
			getDirettaFixturesAndApplyToFiter(filters,masanielloRounds);
			
			//ciclo su tutti i filtri impostati, ognuno dei quali acrà un importo definito in %
			
			int filterIndex = 0;
			BigDecimal partialAmount = initialAmount;
			BigDecimal moltiplicator = new BigDecimal(1);
			int fixtureIndex = 0;
			
			
			int supportFixtureIndex = 0;
			boolean isWinningSupport = true;
			
			BigDecimal totalRoundAmount = BigDecimal.ZERO;
			do{
				
				if(filters.get(filterIndex).getAmount().compareTo(BigDecimal.ZERO) != 0){
					DirettaFixture direttaFixture = filters.get(filterIndex).getDirettaFixtures().get(fixtureIndex);
					matches = matches + 1;
					//qua sottraggo la percentuale di importo che gioco
					BigDecimal betAmount = amountToInvest.multiply(filters.get(filterIndex).getAmount()).divide(new BigDecimal(100),2,RoundingMode.FLOOR);
					totalRoundAmount = totalRoundAmount.add(betAmount);
					partialAmount = partialAmount.subtract(betAmount);
					logger.debug(direttaFixture.toString());
					if(isWinningSupport && isWinningEvent(direttaFixture, filters.get(filterIndex).getBetType())){
						
						BigDecimal direttaFixtureQuote = getDirettaFixturesQuote(direttaFixture,filters.get(filterIndex).getBetType());
						MasanielloRound masanielloRound = creaRoundVincente(totalRoundAmount, initialAmount, moltiplicator, partialAmount, betAmount, matches, roundId, masaniello, masanielloName, masanielloUserEmail, direttaFixtureQuote);
						//masanielloRound.setDirettaFixtures(roundFixtures);
						
						masaniello.getMasanielloRounds().add(masanielloRound);
						patrimonyPercentage = initialPatrimonyPercentage;
						amountToInvest = initialAmount.multiply(patrimonyPercentage).divide(new BigDecimal(100),2,RoundingMode.FLOOR);
						aggiornaIndiciDiGiocata(filterIndex,fixtureIndex,isWinningSupport,moltiplicator,totalRoundAmount);
					}else{
						if(filterIndex >= filters.size() -1){
							MasanielloRound masanielloRound = creaRoundPerdente(totalRoundAmount, initialAmount, moltiplicator, partialAmount, betAmount, matches, roundId, masaniello, masanielloName, masanielloUserEmail);
							masaniello.getMasanielloRounds().add(masanielloRound);
							patrimonyPercentage = patrimonyPercentage.add(raiseByLoss);
							amountToInvest = initialAmount.multiply(patrimonyPercentage).divide(new BigDecimal(100),2,RoundingMode.FLOOR);
							aggiornaIndiciDiGiocata(filterIndex,fixtureIndex,isWinningSupport,moltiplicator,totalRoundAmount);
						}else{
							filterIndex = filterIndex + 1;
						}
					}
					
				}else{
					DirettaFixture direttaFixture = filters.get(filterIndex).getDirettaFixtures().get(supportFixtureIndex);
					supportFixtureIndex = supportFixtureIndex + 1;
					moltiplicator = moltiplicator.multiply(getDirettaFixturesQuote(direttaFixture,filters.get(filterIndex).getBetType()));
					isWinningSupport = isWinningSupport && isWinningEvent(direttaFixture, filters.get(filterIndex).getBetType());
					filterIndex = filterIndex + 1;
				}
			}while(fixtureIndex < masanielloRounds);
			
			masaniellos.add(masaniello);
			masanielloPlan.setMasaniellos(masaniellos);
			masanielloPlanBusiness.update(masanielloPlan);
		}catch (Exception exception){
			logger.error("exception while creating masanielllo",exception);
		}
	}
	
	private BigDecimal getDirettaFixturesQuote(DirettaFixture direttaFixture, String betType) {
		if(BettypeConstants._1.equals(betType)){
			return direttaFixture.getQuota1();
		}else if(BettypeConstants._X.equals(betType)){
			return direttaFixture.getQuotaX();
		}else{
			return direttaFixture.getQuota2();
		}
	}

	private boolean isWinningEvent(DirettaFixture direttaFixture, String betType) {
		return betMatcherBusiness.isWinningEvent(direttaFixture.getHomeGoals(),direttaFixture.getAwayGoals(),betType);
	}

	private boolean isWinningEvent(Integer homeGoals, Integer awayGoals, String betType) {
		return betMatcherBusiness.isWinningEvent(homeGoals,awayGoals,betType);
	}

	private Masaniello initializeMasaniello(
			String msanielloName,
			String masanielloUserEmail,
			Integer id,
			Integer eventToWin,
			BigDecimal masanielloAmount,
			Integer masanielloRounds
			) {
		Masaniello masaniello = new Masaniello();
		masaniello.setMasanielloPlan(new MasanielloPlan(msanielloName,new User(masanielloUserEmail)));
		masaniello.setName(msanielloName);
		masaniello.setId(id);
		masaniello.setEventToWin(eventToWin);
		masaniello.setAmount(masanielloAmount); //1000
		masaniello.setRounds(masanielloRounds); //il numero massimo di cicli es:100
		masaniello.setMasanielloRounds(new ArrayList<MasanielloRound>());
		return masaniello;
	}
	
	private void getDirettaFixturesAndApplyToFiter(List<PlanFilterDto> filters,Integer masanielloRounds) {
		for (PlanFilterDto filter : filters) {
			filter.setDateFrom(dateUtils.truncYesterdayDate(filter.getDateFrom()));
			filter.setDateTo(dateUtils.truncTomorrowDate(filter.getDateTo()));
			
			if(filter.getAmount().compareTo(BigDecimal.ZERO) == 0){
				List<DirettaFixture> direttaFixtures = direttaFixtureDao.getDirettaFixtures(filter.getCompetition(),filter.getHomeTeam(),filter.getAwayTeam(),filter.getQuota1From(),filter.getQuota1To(),filter.getQuotaXFrom(),filter.getQuotaXTo(),filter.getQuota2From(),filter.getQuota2To(),filter.getDateFrom(),filter.getDateTo(),masanielloRounds * filters.size() - 1,true);
				filter.setDirettaFixtures(direttaFixtures);
			}else{
				List<DirettaFixture> direttaFixtures = direttaFixtureDao.getDirettaFixtures(filter.getCompetition(),filter.getHomeTeam(),filter.getAwayTeam(),filter.getQuota1From(),filter.getQuota1To(),filter.getQuotaXFrom(),filter.getQuotaXTo(),filter.getQuota2From(),filter.getQuota2To(),filter.getDateFrom(),filter.getDateTo(),masanielloRounds,true);
				filter.setDirettaFixtures(direttaFixtures);
			}
		}
	}
	
	private void aggiornaIndiciDiGiocata(int filterIndex, int fixtureIndex,boolean isWinningSupport,BigDecimal moltiplicator,BigDecimal totalRoundAmount) {
		filterIndex = 0;
		fixtureIndex = fixtureIndex + 1;
		isWinningSupport = true;
		moltiplicator = new BigDecimal(1);
		totalRoundAmount= BigDecimal.ZERO;
	}

	private MasanielloRound creaRoundPerdente(
			BigDecimal totalRoundAmount, 
			BigDecimal initialAmount,
			BigDecimal moltiplicator, 
			BigDecimal partialAmount, 
			BigDecimal betAmount, 
			Integer matches, 
			Integer roundId,
			Masaniello masaniello, 
			String masanielloName, 
			String masanielloUserEmail) {
		MasanielloRound masanielloRound = new MasanielloRound();
		BigDecimal percentageInvested = totalRoundAmount.divide(initialAmount,2,RoundingMode.CEILING);
		percentageInvested  = percentageInvested.multiply(new BigDecimal(100).setScale(2, RoundingMode.CEILING));
		masanielloRound.setPercentageInvested(percentageInvested);
				
		
		masanielloRound.setInitialAmount(initialAmount);
		masanielloRound.setFinalAmount(partialAmount);
		initialAmount = masanielloRound.getFinalAmount();
		masanielloRound.setMatches(matches);
		matches = 0;
		masanielloRound.setRoundId(roundId);
		roundId++;
		masanielloRound.setSuccess(false);
		masanielloRound.setMasaniello(new Masaniello(masaniello.getName(),masaniello.getId(), new MasanielloPlan(masanielloName, new User(masanielloUserEmail))));
		return masanielloRound;
	}

	private MasanielloRound creaRoundVincente(
			BigDecimal totalRoundAmount,
			BigDecimal initialAmount,
			BigDecimal moltiplicator,
			BigDecimal partialAmount,
			BigDecimal betAmount,
			Integer matches,
			Integer roundId,
			Masaniello masaniello,
			String masanielloName,
			String masanielloUserEmail,
			BigDecimal direttaFixtureQuote
			) {
		MasanielloRound masanielloRound= new MasanielloRound();
		BigDecimal percentageInvested = totalRoundAmount.divide(initialAmount,2,RoundingMode.CEILING);
		percentageInvested  = percentageInvested.multiply(new BigDecimal(100).setScale(2, RoundingMode.CEILING));
		masanielloRound.setPercentageInvested(percentageInvested);
		masanielloRound.setInitialAmount(initialAmount);
		moltiplicator = moltiplicator.multiply(direttaFixtureQuote);
		partialAmount = partialAmount.add(betAmount.multiply(moltiplicator));
		masanielloRound.setFinalAmount(partialAmount);
		initialAmount = masanielloRound.getFinalAmount();
		masanielloRound.setMatches(matches);
		matches = 0;
		masanielloRound.setRoundId(roundId);
		roundId++;
		masanielloRound.setSuccess(true);
		masanielloRound.setMasaniello(new Masaniello(masaniello.getName(),masaniello.getId(), new MasanielloPlan(masanielloName, new User(masanielloUserEmail))));
		return masanielloRound;
	}

}
