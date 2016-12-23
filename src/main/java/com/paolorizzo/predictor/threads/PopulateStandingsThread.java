package com.paolorizzo.predictor.threads;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pabloo99.xmlsoccer.api.dto.GetLeagueStandingsResultDto;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import com.github.pabloo99.xmlsoccer.model.enums.Seasons;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Standing;
import com.paolorizzo.xmlsoccer.business.StandingBusiness;
import com.paolorizzo.xmlsoccer.data.converter.StandingDataConverter;

public class PopulateStandingsThread extends Thread {

	private String league;

	@Autowired
	private XmlSoccerServiceImpl xmlSoccerServiceBean;

	@Autowired
	private StandingBusiness standingBusiness;

	Logger logger = LogManager.getLogger("root");

	public PopulateStandingsThread(String league,
			XmlSoccerServiceImpl xmlSoccerServiceBean,
			StandingBusiness standingBusiness) {
		super();
		this.league = league;
		this.xmlSoccerServiceBean = xmlSoccerServiceBean;
		this.standingBusiness = standingBusiness;
	}

	@Override
	public void run() {
		logger.debug("populateStandings");
		List<XmlSoccer_Standing> standings = new ArrayList<XmlSoccer_Standing>();
		for (Seasons season : Seasons.values()) {
			List<GetLeagueStandingsResultDto> getLeagueStandingsResultDtos = xmlSoccerServiceBean
					.getLeagueStandingsBySeason(league, season.getParam());
			logger.debug(season.getParam());
			for (GetLeagueStandingsResultDto getLeagueStandingsResultDto : getLeagueStandingsResultDtos) {
				standings
						.add(StandingDataConverter.convert(
								getLeagueStandingsResultDto, league,
								season.getParam()));
			}
			try {
				Thread.sleep(16000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		standingBusiness.insertAll(standings);
	}
}
