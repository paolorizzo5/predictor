package com.paolorizzo.predictor.threads;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pabloo99.xmlsoccer.api.dto.GetAllOddsResultDto;
import com.github.pabloo99.xmlsoccer.api.dto.GetFixturesResultDto;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import com.github.pabloo99.xmlsoccer.model.enums.Seasons;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Fixture;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Odd;
import com.paolorizzo.xmlsoccer.business.FixtureBusiness;
import com.paolorizzo.xmlsoccer.data.converter.FixtureDataConverter;
import com.paolorizzo.xmlsoccer.data.converter.OddDataConverter;

public class PopulateFixturesThread extends Thread {

	private String league;

	@Autowired
	private XmlSoccerServiceImpl xmlSoccerServiceBean;

	@Autowired
	private FixtureBusiness fixtureBusiness;

	Logger logger = LogManager.getLogger("root");

	public PopulateFixturesThread(String league,
			XmlSoccerServiceImpl xmlSoccerServiceBean,
			FixtureBusiness fixtureBusiness) {
		super();
		this.league = league;
		this.xmlSoccerServiceBean = xmlSoccerServiceBean;
		this.fixtureBusiness = fixtureBusiness;
	}

	@Override
	public void run() {
		logger.debug("populateFixtures LEAGUE=" + league);

		for (Seasons season : Seasons.values()) {
			List<GetFixturesResultDto> getFixturesResultDtos = xmlSoccerServiceBean
					.getFixturesByLeagueAndSeason(league, season.getParam());
			for (GetFixturesResultDto getFixturesResultDto : getFixturesResultDtos) {

				XmlSoccer_Fixture fixture = FixtureDataConverter.convert(
						getFixturesResultDto, league, season.getParam());

				if (getFixturesResultDto.getDate().getTime() > System
						.currentTimeMillis()) {
					List<GetAllOddsResultDto> allOddsResultDtos = xmlSoccerServiceBean
							.getAllOddsByFixtureMatchId(getFixturesResultDto
									.getId());

					List<XmlSoccer_Odd> odds = new ArrayList<XmlSoccer_Odd>();
					for (GetAllOddsResultDto getAllOddsResultDto : allOddsResultDtos) {
						odds.add(OddDataConverter.convert(getAllOddsResultDto));
					}

					fixture.setOdds(odds);
				}
				fixtureBusiness.insert(fixture);
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
