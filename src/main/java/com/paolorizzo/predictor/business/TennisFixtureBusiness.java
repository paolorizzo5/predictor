package com.paolorizzo.predictor.business;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.dao.facade.DirettaFixtureDao;
import com.paolorizzo.predictor.dao.facade.TennisFixtureDao;
import com.paolorizzo.predictor.hibernate.model.DirettaFixture;
import com.paolorizzo.predictor.hibernate.model.TennisFixture;
import com.paolorizzo.predictor.utils.DateUtils;
import com.paolorizzo.predictor.utils.SimpleUtils;
import com.sun.jersey.core.header.FormDataContentDisposition;

public class TennisFixtureBusiness {

	static Logger logger = LogManager.getLogger("root");
	
	@Autowired
	private TennisFixtureDao tennisFixtureDao;
	

	
	public TennisFixtureBusiness(TennisFixtureDao tennisFixtureDao) {
		super();
		this.tennisFixtureDao = tennisFixtureDao;
	}


	public Integer addFromFile(InputStream inputStream, FormDataContentDisposition fileDetails) {
		Workbook workbook;
		try {
			workbook =  WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> iterator = sheet.iterator();
	       List<TennisFixture> tennisFixtures = new ArrayList<TennisFixture>();
	        
	        Date matchDate = DateUtils.simpleDateFormat_yyyy_MM_dd.parse(fileDetails.getFileName());
			
	        while (iterator.hasNext()) {
	            Row nextRow = iterator.next();
	            try{
	            	if(nextRow.getCell(1).getStringCellValue() == null || nextRow.getCell(1).getStringCellValue().equals("")){
	            	}else{
	            		TennisFixture tennisFixture = new TennisFixture();
	            		tennisFixture.setHomeTeam(nextRow.getCell(1).getStringCellValue());
	            		tennisFixture.setAwayTeam(nextRow.getCell(2).getStringCellValue());
	            		
	            		String fullScore = nextRow.getCell(3).getStringCellValue();
	            		byte[] byteList = SimpleUtils.removeWhiteSpaces(fullScore.getBytes());
            			fullScore = new String(byteList, StandardCharsets.UTF_8);
            			
	            		String[] score = fullScore.split(":");
	            		tennisFixture.setHomeSets(Integer.parseInt(score[0]));
	            		tennisFixture.setAwaySets(Integer.parseInt(score[1]));
	            		
	            		tennisFixture.setQuota1(new BigDecimal(nextRow.getCell(4).getStringCellValue()));
	            		tennisFixture.setQuota2(new BigDecimal(nextRow.getCell(5).getStringCellValue()));
	            		
	            		tennisFixture.setDate(matchDate);
	            		tennisFixtures.add(tennisFixture);
	            		
	            	}
	            }catch(Exception exception){
	            	logger.warn("unable to parse " + nextRow.toString());
	            }
	        }
	            addAll(tennisFixtures);
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
	public void addAll(List<TennisFixture> tennisFixtures) {
		for (TennisFixture tennisFixture : tennisFixtures) {
			tennisFixtureDao.insert(tennisFixture);		
		}
	}


	public TennisFixtureDao getTennisFixtureDao() {
		return tennisFixtureDao;
	}


	public void setTennisFixtureDao(TennisFixtureDao tennisFixtureDao) {
		this.tennisFixtureDao = tennisFixtureDao;
	}

}
