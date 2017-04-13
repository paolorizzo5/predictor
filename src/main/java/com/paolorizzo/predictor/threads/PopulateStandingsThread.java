package com.paolorizzo.predictor.threads;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pabloo99.xmlsoccer.api.dto.GetLeagueStandingsResultDto;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import com.github.pabloo99.xmlsoccer.model.enums.Seasons;
import com.paolorizzo.predictor.business.WebServiceApiDetailBusiness;
import com.paolorizzo.predictor.constants.WebServiceApiDetailConstants;
import com.paolorizzo.predictor.dto.WebServiceApiDetailDto;
import com.paolorizzo.predictor.utils.ThreadUtils;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Standing;
import com.paolorizzo.xmlsoccer.business.StandingBusiness;
import com.paolorizzo.xmlsoccer.data.converter.StandingDataConverter;

public class PopulateStandingsThread extends Thread {

	private List<Integer> leagues;

	@Autowired
	private XmlSoccerServiceImpl xmlSoccerServiceBean;

	@Autowired
	private StandingBusiness standingBusiness;

	@Autowired
	private WebServiceApiDetailBusiness webServiceApiDetailBusiness;

	Logger logger = LogManager.getLogger("root");

	public PopulateStandingsThread(List<Integer> leagues,
			XmlSoccerServiceImpl xmlSoccerServiceBean,
			StandingBusiness standingBusiness,WebServiceApiDetailBusiness webServiceApiDetailBusiness) {
		super();
		this.leagues = leagues;
		this.xmlSoccerServiceBean = xmlSoccerServiceBean;
		this.standingBusiness = standingBusiness;
		this.webServiceApiDetailBusiness = webServiceApiDetailBusiness;
	}

	@Override
	public void run() {
		logger.debug("populateStandings");
		
		for (Integer leagueInt : leagues) {
			String league = leagueInt + "";
			
			List<XmlSoccer_Standing> standings = new ArrayList<XmlSoccer_Standing>();
			for (Seasons season : Seasons.values()) {
				WebServiceApiDetailDto webServiceApiDetailDto = webServiceApiDetailBusiness.canMakeCall(WebServiceApiDetailConstants.GET_LEAGUE_STANDINGS_BY_SEASON);
				
				if(webServiceApiDetailDto.getCanMakeCall()){
					List<GetLeagueStandingsResultDto> getLeagueStandingsResultDtos = xmlSoccerServiceBean
							.getLeagueStandingsBySeason(league, season.getParam());
					logger.debug(season.getParam());
					for (GetLeagueStandingsResultDto getLeagueStandingsResultDto : getLeagueStandingsResultDtos) {
						standings
								.add(StandingDataConverter.convert(
										getLeagueStandingsResultDto, league,
										season.getParam()));
					}
					ThreadUtils.getSomeSleep(webServiceApiDetailDto);
				}else{
					logger.debug("impossibile effettuare la chiamata a " + WebServiceApiDetailConstants.GET_LEAGUE_STANDINGS_BY_SEASON);
					ThreadUtils.getSomeSleep(webServiceApiDetailDto);
				}
				
			}
			standingBusiness.insertAll(standings);
		}
		
		

		
	}
}
