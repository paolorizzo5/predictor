package com.paolorizzo.predictor.business;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.rules.DisableOnDebug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.constants.BettypeConstants;
import com.paolorizzo.predictor.dao.facade.DirettaFixtureDao;
import com.paolorizzo.predictor.dto.DirettaStatsDto;
import com.paolorizzo.predictor.hibernate.model.DirettaFixture;
import com.paolorizzo.predictor.hibernate.model.Masaniello;
import com.paolorizzo.predictor.hibernate.model.MasanielloPlan;
import com.paolorizzo.predictor.hibernate.model.MasanielloRound;
import com.paolorizzo.predictor.hibernate.model.PlanFilter;
import com.paolorizzo.predictor.hibernate.model.User;
import com.paolorizzo.predictor.services.request.PlanFilterDto;
import com.paolorizzo.predictor.services.response.GetDirettaFixturesResponse;
import com.paolorizzo.predictor.utils.DateUtils;
import com.paolorizzo.predictor.utils.SimpleUtils;
import com.paolorizzo.xmlsoccer.data.converter.DirettaFixtureConverter;
import com.paolorizzo.xmlsoccer.data.converter.PlanFilterDataConverter;
import com.sun.jersey.core.header.FormDataContentDisposition;



public class DirettaFixtureBusiness {
	
	@Autowired
	private DirettaFixtureDao direttaFixtureDao;
	
	@Autowired
	private AccountBusiness accountBusiness;
	
	@Autowired
	private BetMatcherBusiness betMatcherBusiness;
	
	@Autowired
	private MasanielloPlanBusiness masanielloPlanBusiness;
	
	@Autowired
	DateUtils dateUtils;
	
	Logger logger = LogManager.getLogger("dino");
	
	
	
	public DirettaFixtureBusiness(DirettaFixtureDao direttaFixtureDao,AccountBusiness accountBusiness,BetMatcherBusiness betMatcherBusiness,MasanielloPlanBusiness masanielloPlanBusiness,DateUtils dateUtils) {
		super();
		this.direttaFixtureDao = direttaFixtureDao;
		this.setAccountBusiness(accountBusiness);
		this.betMatcherBusiness = betMatcherBusiness;
		this.masanielloPlanBusiness = masanielloPlanBusiness;
		this.dateUtils = dateUtils;
	}

	@Transactional(readOnly=false)
	public Integer addFromFile(InputStream inputStream, FormDataContentDisposition fileDetails) {
	 
		Workbook workbook;
		try {
			workbook =  WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> iterator = sheet.iterator();
	        String currentCompetition = "";
	        List<DirettaFixture> direttaFixtures = new ArrayList<DirettaFixture>();
	        
	        Date matchDate = DateUtils.simpleDateFormat_yyyy_MM_dd.parse(fileDetails.getFileName());
			
	        while (iterator.hasNext()) {
	            Row nextRow = iterator.next();
	            try{
	            	int i = 0;
	            	if(nextRow.getCell(1).getStringCellValue() == null || nextRow.getCell(1).getStringCellValue().equals("")){
	            		currentCompetition = nextRow.getCell(0).getStringCellValue();
	            	}else{
//	            		if("".equals(nextRow.getCell(0).getStringCellValue())){
//	            			i = 1;
//	            		}
	            		DirettaFixture direttaFixture = new DirettaFixture();
	            		direttaFixture.setHomeTeam(nextRow.getCell(i + 1).getStringCellValue());
	            		direttaFixture.setAwayTeam(nextRow.getCell(i + 2).getStringCellValue());
	            		
	            		String fullScore = nextRow.getCell(i + 3).getStringCellValue();
	            		byte[] byteList = SimpleUtils.removeWhiteSpaces(fullScore.getBytes());
            			fullScore = new String(byteList, StandardCharsets.UTF_8);
            			
	            		String[] score = fullScore.split(":");
	            		direttaFixture.setHomeGoals(Integer.parseInt(score[0]));
	            		direttaFixture.setAwayGoals(Integer.parseInt(score[1]));
	            		
	            		direttaFixture.setQuota1(new BigDecimal(nextRow.getCell(i + 4).getStringCellValue()));
	            		direttaFixture.setQuotaX(new BigDecimal(nextRow.getCell(i + 5).getStringCellValue()));
	            		direttaFixture.setQuota2(new BigDecimal(nextRow.getCell(i + 6).getStringCellValue()));
	            		
	            		direttaFixture.setCurrentCompetition(currentCompetition);
	            		direttaFixture.setDate(matchDate);
	            		direttaFixtures.add(direttaFixture);
	            		
	            	}
	            }catch(Exception exception){
	            	logger.warn("unable to parse " + nextRow.toString());
	            }
	        }
	            addAll(direttaFixtures);
	        //((Closeable) workbook).close();
	        
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	

	@Transactional(readOnly=false)
	public void addAll(List<DirettaFixture> direttaFixtures) {
		for (DirettaFixture direttaFixture : direttaFixtures) {
			direttaFixtureDao.insert(direttaFixture);		
		}
	}

	public List<String> getCompetitions() {
		return direttaFixtureDao.getCompetitions();
	}

	public List<DirettaFixture> getDirettaFixtures(
			String competition,
			String homeTeam,
			String awayTeam, 
			BigDecimal quota1From, 
			BigDecimal quota1To,
			BigDecimal quotaXFrom, 
			BigDecimal quotaXTo, 
			BigDecimal quota2From, 
			BigDecimal quota2To, 
			Date dateFromDate, 
			Date dateToDate) throws ParseException {
		
		dateFromDate = dateUtils.truncYesterdayDate(dateFromDate);
		dateToDate = dateUtils.truncTomorrowDate(dateToDate);
//		
		return direttaFixtureDao.getDirettaFixtures(
				competition,
				homeTeam,
				awayTeam,
				quota1From,
				quota1To,
				quotaXFrom,
				quotaXTo,
				quota2From,
				quota2To,
				dateFromDate,dateToDate,
				null,false);
	}

	public GetDirettaFixturesResponse getStats(List<DirettaFixture> direttaFixtures) {
		GetDirettaFixturesResponse getDirettaFixturesResponse = new GetDirettaFixturesResponse();
		getDirettaFixturesResponse.setDirettaFixtures(DirettaFixtureConverter.convert(direttaFixtures));
		
		Integer _1 = 0;
		Integer _X = 0;
		Integer _2 = 0;
		Integer _even = 0;
		Integer _odd = 0;
		Integer _homeEven = 0;
		Integer _homeOdd = 0;
		Integer _awayEven = 0;
		Integer _awayOdd = 0;
		
		Integer _handicapHome2_1 = 0;
		Integer _handicapHome2_X = 0;
		Integer _handicapHome2_2 = 0;
		Integer _handicapHome1_1 = 0;
		Integer _handicapHome1_X = 0;
		Integer _handicapHome1_2 = 0;
		Integer _handicapAway2_1 = 0;
		Integer _handicapAway2_X = 0;
		Integer _handicapAway2_2 = 0;
		Integer _handicapAway1_1 = 0;
		Integer _handicapAway1_X = 0;
		Integer _handicapAway1_2 = 0;
		
		Integer _0_0 = 0;
		Integer _0_1 = 0;
		Integer _0_2 = 0;
		Integer _0_3 = 0;
		Integer _0_4 = 0;
		Integer _1_0 = 0;
		Integer _1_2 = 0;
		Integer _1_3 = 0;
		Integer _1_4 = 0;
		Integer _2_0 = 0;
		Integer _2_3 = 0;
		Integer _2_4 = 0;
		Integer _3_0 = 0;
		Integer _4_0 = 0;
		Integer _1_1 = 0;
		Integer _2_1 = 0;
		Integer _3_1 = 0;
		Integer _4_1 = 0;
		Integer _2_2 = 0;
		Integer _3_2 = 0;
		Integer _4_2 = 0;
		Integer _3_3 = 0;
		Integer _3_4 = 0;
		Integer _4_3 = 0;
		Integer _4_4 = 0;
		
		
		Integer _matches = 0;
		
		for (DirettaFixture direttaFixture : direttaFixtures) {
			_matches++;
			
			if(direttaFixture.getHomeGoals() > direttaFixture.getAwayGoals()){
				_1++;
			}else if(direttaFixture.getHomeGoals() == direttaFixture.getAwayGoals()){
				_X++;
			}else{
				_2++;
			}
			
			if((direttaFixture.getHomeGoals() + direttaFixture.getAwayGoals()) % 2 == 0){
				_even++;
			}else{
				_odd++;
			}
			
			if(direttaFixture.getHomeGoals() % 2 == 0){
				_homeEven++;
			}else{
				_homeOdd++;
			}
			
			if(direttaFixture.getAwayGoals() % 2 == 0){
				_awayEven++;
			}else{
				_awayOdd++;
			}
			
			switch (direttaFixture.getHomeGoals() - direttaFixture.getAwayGoals()) {
			case 20:
			case 19:
			case 18:
			case 17:
			case 16:
			case 15:
			case 14:
			case 13:
			case 12:
			case 11:
			case 10:
			case 9:
			case 8:
			case 7:
			case 6:
			case 5:
			case 4:
			case 3:
				_handicapHome1_1++;
				_handicapHome2_1++;
				_handicapAway1_1++;
				_handicapAway2_1++;	
				break;
			case 2:
				_handicapHome2_X++;
				_handicapHome1_1++;
				_handicapAway1_1++;
				_handicapAway2_1++;	
				break;
			case 1:
				_handicapHome2_2++;
				_handicapHome1_X++;
				_handicapAway1_1++;
				_handicapAway2_1++;	
				break;
			case 0:
				_handicapHome2_2++;
				_handicapHome1_2++;
				_handicapAway1_1++;
				_handicapAway2_1++;	
				break;
			case -1:
				_handicapHome2_2++;
				_handicapHome1_2++;
				_handicapAway1_X++;
				_handicapAway2_1++;	
				break;
			case -2:
				_handicapHome2_2++;
				_handicapHome1_2++;
				_handicapAway1_2++;
				_handicapAway2_X++;	
				break;
			default:
				_handicapHome2_2++;
				_handicapHome1_2++;
				_handicapAway1_2++;
				_handicapAway2_2++;	
				break;
			}
			
			switch (direttaFixture.getHomeGoals()) {
			case 0:
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;

			default:
				break;
			}
			
		}
		
		DirettaStatsDto statsDto = new DirettaStatsDto();
		
		statsDto.set_1(_1);
		statsDto.set_X(_X);
		statsDto.set_2(_2);
		statsDto.set_matches(_matches);
		statsDto.set_even(_even);
		statsDto.set_odd(_odd);
		statsDto.set_homeEven(_homeEven);
		statsDto.set_homeOdd(_homeOdd);
		statsDto.set_awayEven(_awayEven);
		statsDto.set_awayOdd(_awayOdd);
		statsDto.set_handicapAway1_1(_handicapAway1_1);
		statsDto.set_handicapAway1_2(_handicapAway1_2);
		statsDto.set_handicapAway1_X(_handicapAway1_X);
		statsDto.set_handicapAway2_1(_handicapAway2_1);
		statsDto.set_handicapAway2_2(_handicapAway2_2);
		statsDto.set_handicapAway2_X(_handicapAway2_X);
		statsDto.set_handicapHome1_1(_handicapHome1_1);
		statsDto.set_handicapHome1_2(_handicapHome1_2);
		statsDto.set_handicapHome1_X(_handicapHome1_X);
		statsDto.set_handicapHome2_1(_handicapHome2_1);
		statsDto.set_handicapHome2_2(_handicapHome2_2);
		statsDto.set_handicapHome2_X(_handicapHome2_X);
		
		getDirettaFixturesResponse.setStats(statsDto);
		
		return getDirettaFixturesResponse;
	}

	public BetMatcherBusiness getBetMatcherBusiness() {
		return betMatcherBusiness;
	}

	public void setBetMatcherBusiness(BetMatcherBusiness betMatcherBusiness) {
		this.betMatcherBusiness = betMatcherBusiness;
	}

	@Transactional(readOnly=false)
	public MasanielloPlan createPlan(List<PlanFilterDto> filters,String masanielloUserEmail, 
			BigDecimal masanielloAmount, Integer masanielloEventToWin, String msanielloName,  
			BigDecimal masanielloAverageQuote, BigDecimal masanielloAdditionalQuote, BigDecimal masanielloPercentage, Integer masanielloRounds, BigDecimal patrimonyPercentage
			) throws ParseException {
		try{
			MasanielloPlan masanielloPlan = new MasanielloPlan(msanielloName, new User(masanielloUserEmail),masanielloAmount,masanielloRounds,masanielloEventToWin,masanielloPercentage,masanielloAverageQuote,masanielloAdditionalQuote,null);
			masanielloPlanBusiness.add(masanielloPlan);
			return masanielloPlan;
		}catch (Exception exception){
			return null;
		}
	}

	public AccountBusiness getAccountBusiness() {
		return accountBusiness;
	}

	public void setAccountBusiness(AccountBusiness accountBusiness) {
		this.accountBusiness = accountBusiness;
	}

	public MasanielloPlan createPlan(ArrayList<PlanFilterDto> filters, String masanielloUserEmail,
			BigDecimal masanielloAmount, String masanielloName, Integer masanielloRounds,
			BigDecimal patrimonyPercentage) {
		try{
			MasanielloPlan masanielloPlan = new MasanielloPlan(masanielloName, new User(masanielloUserEmail),masanielloAmount,masanielloRounds,1,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,null);
			masanielloPlan.setPlanFilters(PlanFilterDataConverter.convert(filters,masanielloPlan));
			
			masanielloPlanBusiness.add(masanielloPlan);
			
			
			return masanielloPlan;
		}catch (Exception exception){
			return null;
		}
	}
	
}
