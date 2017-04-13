package com.paolorizzo.predictor.business;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjAngle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.jdbc.Driver;
import com.paolorizzo.predictor.dao.facade.DirettaFixtureDao;
import com.paolorizzo.predictor.dto.DirettaStatsDto;
import com.paolorizzo.predictor.hibernate.model.Account;
import com.paolorizzo.predictor.hibernate.model.DirettaFixture;
import com.paolorizzo.predictor.hibernate.model.Masaniello;
import com.paolorizzo.predictor.hibernate.model.MasanielloRound;
import com.paolorizzo.predictor.hibernate.model.User;
import com.paolorizzo.predictor.services.response.GetDirettaFixturesResponse;
import com.paolorizzo.xmlsoccer.data.converter.DirettaFixtureConverter;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.core.spi.scanning.ScannerListener;



public class DirettaFixtureBusiness {
	
	@Autowired
	private DirettaFixtureDao direttaFixtureDao;
	
	@Autowired
	private AccountBusiness accountBusiness;
	
	@Autowired
	private BetMatcherBusiness betMatcherBusiness;
	
	@Autowired
	private MasanielloBusiness masanielloBusiness;
	
	
	static Logger logger = LogManager.getLogger("root");

	
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public DirettaFixtureBusiness(DirettaFixtureDao direttaFixtureDao,AccountBusiness accountBusiness,BetMatcherBusiness betMatcherBusiness,MasanielloBusiness masanielloBusiness) {
		super();
		this.direttaFixtureDao = direttaFixtureDao;
		this.accountBusiness = accountBusiness;
		this.betMatcherBusiness = betMatcherBusiness;
		this.masanielloBusiness = masanielloBusiness;
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
	        
	        Date matchDate = simpleDateFormat.parse(fileDetails.getFileName());
			
	        while (iterator.hasNext()) {
	            Row nextRow = iterator.next();
	            try{
	            	if(nextRow.getCell(1).getStringCellValue() == null || nextRow.getCell(1).getStringCellValue().equals("")){
	            		currentCompetition = nextRow.getCell(0).getStringCellValue();
	            	}else{
	            		DirettaFixture direttaFixture = new DirettaFixture();
	            		direttaFixture.setHomeTeam(nextRow.getCell(1).getStringCellValue());
	            		direttaFixture.setAwayTeam(nextRow.getCell(2).getStringCellValue());
	            		
	            		String fullScore = nextRow.getCell(3).getStringCellValue();
	            		byte[] byteList = removeWhiteSpaces(fullScore.getBytes());
            			fullScore = new String(byteList, StandardCharsets.UTF_8);
            			
	            		String[] score = fullScore.split(":");
	            		direttaFixture.setHomeGoals(Integer.parseInt(score[0]));
	            		direttaFixture.setAwayGoals(Integer.parseInt(score[1]));
	            		
	            		direttaFixture.setQuota1(new BigDecimal(nextRow.getCell(4).getStringCellValue()));
	            		direttaFixture.setQuotaX(new BigDecimal(nextRow.getCell(5).getStringCellValue()));
	            		direttaFixture.setQuota2(new BigDecimal(nextRow.getCell(6).getStringCellValue()));
	            		
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

	private byte[] removeWhiteSpaces(byte[] bytes) {
		List<Byte> list = new ArrayList<Byte>();
		for (byte b : bytes) {
			if (b != -96 && b != 0){
				list.add(b);
			}
		}
		byte[] ret = new byte[list.size()];
		for (int i = 0;i<list.size();i++){
			ret[i] = list.get(i);
		}
		return ret;
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

	public List<DirettaFixture> getDirettaFixtures(String competition,String homeTeam,String awayTeam, BigDecimal quota1From, BigDecimal quota1To,
			BigDecimal quotaXFrom, BigDecimal quotaXTo, BigDecimal quota2From, BigDecimal quota2To) {
		return direttaFixtureDao.getDirettaFixtures(competition,homeTeam,awayTeam,quota1From,quota1To,quotaXFrom,quotaXTo,quota2From,quota2To);
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

	public Masaniello createMasaniello(String competition, String homeTeam, String awayTeam, BigDecimal quota1From,
			BigDecimal quota1To, BigDecimal quotaXFrom, BigDecimal quotaXTo, BigDecimal quota2From,
			BigDecimal quota2To, String masanielloUserEmail, BigDecimal masanielloAmount, Integer masanielloEventToWin, String masanielloEventType, String msanielloName,  BigDecimal masanielloAverageQuote, BigDecimal masanielloPercentage, Integer masanielloRounds, BigDecimal patrimonyPercentage) {
		
		//patrimonyPercentage = new BigDecimal(100).divide(patrimonyPercentage,2, RoundingMode.DOWN);
		
		Masaniello masaniello = new Masaniello();
		masaniello.setUser(new User(masanielloUserEmail));
		masaniello.setName(msanielloName);
		masaniello.setEventToWin(masanielloEventToWin);
		masaniello.setAmount(masanielloAmount);
		masaniello.setPercentage(masanielloPercentage);
		masaniello.setRounds(masanielloRounds);
		masaniello.setEventType(masanielloEventType);
		masaniello.setAverageQuote(masanielloAverageQuote);
		masaniello.setMasanielloRounds(new ArrayList<MasanielloRound>());
		
		BigDecimal initialAmount = masanielloAmount;
		
		BigDecimal amountToInvest = masanielloAmount.multiply(patrimonyPercentage).divide(new BigDecimal(100),2,RoundingMode.FLOOR);
		
		Integer matches = 0;
		Integer roundId = 1;
		Integer wins = 0;
		List<DirettaFixture> direttaFixtures = direttaFixtureDao.getDirettaFixtures(competition,homeTeam,awayTeam,quota1From,quota1To,quotaXFrom,quotaXTo,quota2From,quota2To);
		
		Collections.sort(direttaFixtures);
		
		for (DirettaFixture direttaFixture : direttaFixtures) {
			matches++;
			if(matches <= masanielloRounds){
				if(isWinningEvent(direttaFixture.getHomeGoals(),direttaFixture.getAwayGoals(),masanielloEventType)){
					wins++;
				}else{
					
				}
				
				if(wins == masanielloEventToWin){
					wins = 0;
					MasanielloRound masanielloRound = new MasanielloRound();
					masanielloRound.setInitialAmount(initialAmount);
					masanielloRound.setFinalAmount(initialAmount.add(amountToInvest.multiply(masanielloPercentage).subtract(amountToInvest)));
					initialAmount = masanielloRound.getFinalAmount();
					masanielloRound.setMatches(matches);
					matches = 0;
					masanielloRound.setRoundId(roundId);
					roundId++;
					masanielloRound.setSuccess(true);
					masanielloRound.setMasaniello(new Masaniello(masaniello.getName(),masaniello.getUser()));
					masaniello.getMasanielloRounds().add(masanielloRound);
					amountToInvest = initialAmount.multiply(patrimonyPercentage).divide(new BigDecimal(100),2,RoundingMode.FLOOR);
				}else if(matches == masanielloRounds){
					MasanielloRound masanielloRound = new MasanielloRound();
					masanielloRound.setInitialAmount(initialAmount);
					masanielloRound.setFinalAmount(initialAmount.subtract(amountToInvest));
					initialAmount = masanielloRound.getFinalAmount();
					masanielloRound.setMatches(matches);
					matches = 0;
					masanielloRound.setRoundId(roundId);
					roundId++;
					masanielloRound.setSuccess(false);
					masanielloRound.setMasaniello(new Masaniello(masaniello.getName(),masaniello.getUser()));
					masaniello.getMasanielloRounds().add(masanielloRound);
					amountToInvest = initialAmount.multiply(patrimonyPercentage).divide(new BigDecimal(100),2,RoundingMode.FLOOR);
				}
			}
		}
		
		masanielloBusiness.add(masaniello);
		return masaniello;
	}

	private boolean isWinningEvent(Integer homeGoals, Integer awayGoals, String masanielloEventType) {
		return betMatcherBusiness.isWinningEvent(homeGoals,awayGoals,masanielloEventType);
	}

	public BetMatcherBusiness getBetMatcherBusiness() {
		return betMatcherBusiness;
	}

	public void setBetMatcherBusiness(BetMatcherBusiness betMatcherBusiness) {
		this.betMatcherBusiness = betMatcherBusiness;
	}
	
}
