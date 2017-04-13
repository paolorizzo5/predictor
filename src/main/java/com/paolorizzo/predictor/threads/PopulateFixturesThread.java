package com.paolorizzo.predictor.threads;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pabloo99.xmlsoccer.api.dto.GetAllOddsResultDto;
import com.github.pabloo99.xmlsoccer.api.dto.GetFixturesResultDto;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import com.github.pabloo99.xmlsoccer.model.enums.Seasons;
import com.paolorizzo.predictor.business.WebServiceApiDetailBusiness;
import com.paolorizzo.predictor.constants.WebServiceApiDetailConstants;
import com.paolorizzo.predictor.dto.WebServiceApiDetailDto;
import com.paolorizzo.predictor.utils.ThreadUtils;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Fixture;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Odd;
import com.paolorizzo.xmlsoccer.business.FixtureBusiness;
import com.paolorizzo.xmlsoccer.data.converter.FixtureDataConverter;
import com.paolorizzo.xmlsoccer.data.converter.OddDataConverter;

public class PopulateFixturesThread extends Thread {

	private List<Integer> leagues;

	@Autowired
	private XmlSoccerServiceImpl xmlSoccerServiceBean;

	@Autowired
	private FixtureBusiness fixtureBusiness;
	
	@Autowired
	private WebServiceApiDetailBusiness webServiceApiDetailBusiness;
	
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	

	Logger logger = LogManager.getLogger("root");

	public PopulateFixturesThread(List<Integer> leagues,
			XmlSoccerServiceImpl xmlSoccerServiceBean,
			FixtureBusiness fixtureBusiness, WebServiceApiDetailBusiness webServiceApiDetailBusiness) {
		super();
		this.leagues = leagues;
		this.xmlSoccerServiceBean = xmlSoccerServiceBean;
		this.fixtureBusiness = fixtureBusiness;
		this.webServiceApiDetailBusiness = webServiceApiDetailBusiness;
	}

	@Override
	public void run() {
		for (Integer leagueInt : leagues) {
		
		String league = leagueInt + "";
		logger.debug("populateFixtures LEAGUE=" + league);

		for (Seasons season : Seasons.values()) {
			List<GetFixturesResultDto> getFixturesResultDtos = null;
			
			WebServiceApiDetailDto webServiceApiDetailDto = webServiceApiDetailBusiness.canMakeCall(WebServiceApiDetailConstants.GET_FIXTURES_BY_LEAGUE_AND_SEASON);
			
			if(webServiceApiDetailDto.getCanMakeCall()){
				getFixturesResultDtos = xmlSoccerServiceBean
						.getFixturesByDateIntervalAndLeague(league, "1900-01-01", simpleDateFormat.format(new Date()));
				for (GetFixturesResultDto getFixturesResultDto : getFixturesResultDtos) {

					XmlSoccer_Fixture fixture = FixtureDataConverter.convert(
							getFixturesResultDto, league, season.getParam());

					if (getFixturesResultDto.getDate().getTime() > System
							.currentTimeMillis()) {
						
						
//						WebServiceApiDetailDto dto = webServiceApiDetailBusiness.canMakeCall(WebServiceApiDetailConstants.GET_ALL_ODDS_BY_FIXTURE_MATCH_ID);
						
//						if(dto.getCanMakeCall()){
//							List<GetAllOddsResultDto> allOddsResultDtos = xmlSoccerServiceBean
//									.getAllOddsByFixtureMatchId(getFixturesResultDto
//											.getId());
//
//							List<XmlSoccer_Odd> odds = new ArrayList<XmlSoccer_Odd>();
//							for (GetAllOddsResultDto getAllOddsResultDto : allOddsResultDtos) {
//								odds.add(OddDataConverter.convert(getAllOddsResultDto));
//							}
//
//							fixture.setOdds(odds);
//							
//							ThreadUtils.getSomeSleep(dto);
//						}else{
//							logger.debug("impossibile effettuare la chiamata a " + WebServiceApiDetailConstants.GET_FIXTURES_BY_LEAGUE_AND_SEASON);
//							ThreadUtils.getSomeSleep(dto);
//							
//							
//						}
						
					}
					fixtureBusiness.insert(fixture);
				}

				ThreadUtils.getSomeSleep(webServiceApiDetailDto);
			}else{
				logger.debug("impossibile effettuare la chiamata a " + WebServiceApiDetailConstants.GET_FIXTURES_BY_LEAGUE_AND_SEASON);
				ThreadUtils.getSomeSleep(webServiceApiDetailDto);
			}
			
		}
		
		}
	}

}
