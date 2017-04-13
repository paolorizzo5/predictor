package com.paolorizzo.predictor.threads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pabloo99.xmlsoccer.api.dto.GetHistoricMatchesResultDto;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import com.github.pabloo99.xmlsoccer.model.enums.Seasons;
import com.paolorizzo.predictor.business.WebServiceApiDetailBusiness;
import com.paolorizzo.predictor.constants.WebServiceApiDetailConstants;
import com.paolorizzo.predictor.dto.WebServiceApiDetailDto;
import com.paolorizzo.predictor.utils.ThreadUtils;
import com.paolorizzo.xmlsoccer.business.HistoricMatchBusiness;
import com.paolorizzo.xmlsoccer.data.converter.HistoricMatchDataConverter;

public class PopulateHistoricMatchThread extends Thread {

	private List<Integer> leagues;

	@Autowired
	private XmlSoccerServiceImpl xmlSoccerServiceBean;

	@Autowired
	private HistoricMatchBusiness historicMatchBusiness;
	
	@Autowired
	private WebServiceApiDetailBusiness webServiceApiDetailBusiness;
	
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	

	Logger logger = LogManager.getLogger("root");

	public PopulateHistoricMatchThread(List<Integer> leagues,
			XmlSoccerServiceImpl xmlSoccerServiceBean,
			HistoricMatchBusiness historicMatchBusiness,WebServiceApiDetailBusiness webServiceApiDetailBusiness) {
		super();
		this.leagues = leagues;
		this.xmlSoccerServiceBean = xmlSoccerServiceBean;
		this.historicMatchBusiness = historicMatchBusiness;
		this.webServiceApiDetailBusiness = webServiceApiDetailBusiness;
	}

	@Override
	public void run() {
		for (Integer leagueInt : leagues) {
			String league = leagueInt + "";
			
			logger.debug("populateHistoricMatches LEAGUE=" + league);

			for (Seasons season : Seasons.values()) {
				List<GetHistoricMatchesResultDto> getHistoricMatchesResultDtos = null;
				WebServiceApiDetailDto webServiceApiDetailDto = webServiceApiDetailBusiness.canMakeCall(WebServiceApiDetailConstants.GET_HISTORIC_MATCHES_BY_LEAGUE_AND_SEASON);
				
				if(webServiceApiDetailDto.getCanMakeCall()){
					getHistoricMatchesResultDtos = xmlSoccerServiceBean
							.getHistoricMatchesByLeagueAndDateInterval(league,"1900-01-01",simpleDateFormat.format(new Date()));
					for (GetHistoricMatchesResultDto getHistoricMatchesResultDto : getHistoricMatchesResultDtos) {
						getHistoricMatchesResultDto.setSeason(season.getParam());
						historicMatchBusiness.insert(HistoricMatchDataConverter
								.convert(getHistoricMatchesResultDto, league, null));
					}

					ThreadUtils.getSomeSleep(webServiceApiDetailDto);
				}else{
					logger.debug("impossibile effettuare la chiamata a " + WebServiceApiDetailConstants.GET_HISTORIC_MATCHES_BY_LEAGUE_AND_SEASON);
					ThreadUtils.getSomeSleep(webServiceApiDetailDto);
				}
			}
		}
		
	}

}
