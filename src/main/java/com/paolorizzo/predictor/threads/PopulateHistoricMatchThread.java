package com.paolorizzo.predictor.threads;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pabloo99.xmlsoccer.api.dto.GetHistoricMatchesResultDto;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import com.github.pabloo99.xmlsoccer.model.enums.Seasons;
import com.paolorizzo.xmlsoccer.business.HistoricMatchBusiness;
import com.paolorizzo.xmlsoccer.data.converter.HistoricMatchDataConverter;

public class PopulateHistoricMatchThread extends Thread {

	private String league;

	@Autowired
	private XmlSoccerServiceImpl xmlSoccerServiceBean;

	@Autowired
	private HistoricMatchBusiness historicMatchBusiness;

	Logger logger = LogManager.getLogger("root");

	public PopulateHistoricMatchThread(String league,
			XmlSoccerServiceImpl xmlSoccerServiceBean,
			HistoricMatchBusiness historicMatchBusiness) {
		super();
		this.league = league;
		this.xmlSoccerServiceBean = xmlSoccerServiceBean;
		this.historicMatchBusiness = historicMatchBusiness;
	}

	@Override
	public void run() {
		logger.debug("populateHistoricMatches LEAGUE=" + league);

		for (Seasons season : Seasons.values()) {
			List<GetHistoricMatchesResultDto> getHistoricMatchesResultDtos = xmlSoccerServiceBean
					.getHistoricMatchesByLeagueAndSeason(league,
							season.getParam());

			for (GetHistoricMatchesResultDto getHistoricMatchesResultDto : getHistoricMatchesResultDtos) {
				getHistoricMatchesResultDto.setSeason(season.getParam());
				historicMatchBusiness.insert(HistoricMatchDataConverter
						.convert(getHistoricMatchesResultDto, league, null));
			}

			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
