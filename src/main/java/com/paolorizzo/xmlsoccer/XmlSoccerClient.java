package com.paolorizzo.xmlsoccer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pabloo99.xmlsoccer.api.dto.GetAllLeaguesResultDto;
import com.github.pabloo99.xmlsoccer.api.dto.GetAllOddsResultDto;
import com.github.pabloo99.xmlsoccer.api.dto.GetHistoricMatchesResultDto;
import com.github.pabloo99.xmlsoccer.api.dto.GetLeagueStandingsResultDto;
import com.github.pabloo99.xmlsoccer.api.dto.GetLiveScoreResultDto;
import com.github.pabloo99.xmlsoccer.api.dto.GetTeamResultDto;
import com.github.pabloo99.xmlsoccer.client.XmlSoccerServiceImpl;
import com.paolorizzo.predictor.threads.PopulateFixturesThread;
import com.paolorizzo.predictor.threads.PopulateHistoricMatchThread;
import com.paolorizzo.predictor.threads.PopulateStandingsThread;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_HistoricMatch;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_League;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Team;
import com.paolorizzo.xmlsoccer.business.FixtureBusiness;
import com.paolorizzo.xmlsoccer.business.HistoricMatchBusiness;
import com.paolorizzo.xmlsoccer.business.LeagueBusiness;
import com.paolorizzo.xmlsoccer.business.OddBusiness;
import com.paolorizzo.xmlsoccer.business.StandingBusiness;
import com.paolorizzo.xmlsoccer.business.TeamBusiness;
import com.paolorizzo.xmlsoccer.data.converter.HistoricMatchDataConverter;
import com.paolorizzo.xmlsoccer.data.converter.LeagueDataConverter;
import com.paolorizzo.xmlsoccer.data.converter.TeamDataConverter;

public class XmlSoccerClient {

	Logger logger = LogManager.getLogger("root");

	@Autowired
	private LeagueBusiness leagueBusiness;

	@Autowired
	private TeamBusiness teamBusiness;

	@Autowired
	private HistoricMatchBusiness historicMatchBusiness;

	@Autowired
	private FixtureBusiness fixtureBusiness;

	@Autowired
	private OddBusiness oddBusiness;

	@Autowired
	private StandingBusiness standingBusiness;

	@Autowired
	private XmlSoccerServiceImpl xmlSoccerServiceBean;

	public XmlSoccerClient(LeagueBusiness leagueBusiness,
			TeamBusiness teamBusiness,
			HistoricMatchBusiness historicMatchBusiness,
			FixtureBusiness fixtureBusiness, OddBusiness oddBusiness,
			StandingBusiness standingBusiness) {
		super();
		this.leagueBusiness = leagueBusiness;
		this.teamBusiness = teamBusiness;
		this.historicMatchBusiness = historicMatchBusiness;
		this.fixtureBusiness = fixtureBusiness;
		this.oddBusiness = oddBusiness;
		this.standingBusiness = standingBusiness;
	}

	public XmlSoccerClient() {
		// TODO Auto-generated constructor stub
	}

	public void execute() throws InterruptedException {
		xmlSoccerServiceBean
				.setApiKey("CHGUCEAMXWHYTZWGNWSLORFLDOCOEIYLKRPNYYZQJEPTKWWIGT");
		xmlSoccerServiceBean
				.setServiceUrl("http://www.xmlsoccer.com/FootballDataDemo.asmx");

		// se è tutto vuoto faccio il primo popolamento
		if (teamBusiness.isEmpty()) {
			populateTeams();
		}

		if (leagueBusiness.isEmpty()) {
			populateLeagues();
		}

	}

	private void populateTeams() {
		logger.debug("populateTeams");
		List<XmlSoccer_Team> teams = new ArrayList<XmlSoccer_Team>();
		Collection<GetTeamResultDto> getTeamResultDtos = xmlSoccerServiceBean
				.getAllTeams();

		for (GetTeamResultDto getTeamResultDto : getTeamResultDtos) {
			teams.add(TeamDataConverter.convert(getTeamResultDto));
		}
		teamBusiness.insertAll(teams);
	}

	@Transactional(readOnly = false)
	private void populateLeagues() throws InterruptedException {
		logger.debug("populateLeagues");
		List<XmlSoccer_League> leagues = new ArrayList<XmlSoccer_League>();
		List<XmlSoccer_HistoricMatch> historicMatches = new ArrayList<XmlSoccer_HistoricMatch>();
		// recupera tutte le leghe
		Collection<GetAllLeaguesResultDto> getAllLeaguesResultDtos = xmlSoccerServiceBean
				.getAllLeagues();

		for (GetAllLeaguesResultDto getAllLeaguesResultDto : getAllLeaguesResultDtos) {
			leagueBusiness.insert(LeagueDataConverter
					.convert(getAllLeaguesResultDto));
			if (getAllLeaguesResultDto.getId() == 3) {

				Runnable populateHistoricMatchesThread = new PopulateHistoricMatchThread(
						getAllLeaguesResultDto.getId() + "",
						xmlSoccerServiceBean, historicMatchBusiness);
				new Thread(populateHistoricMatchesThread).start();

				Runnable populateFixturesThread = new PopulateFixturesThread(
						getAllLeaguesResultDto.getId() + "",
						xmlSoccerServiceBean, fixtureBusiness);
				new Thread(populateFixturesThread).start();

				Runnable populateStandingsThread = new PopulateStandingsThread(
						getAllLeaguesResultDto.getId() + "",
						xmlSoccerServiceBean, standingBusiness);
				new Thread(populateStandingsThread).start();

			}

		}

	}

	public XmlSoccer_HistoricMatch getHistoricMatchById(Integer id,
			int leagueId, String season) {

		List<GetHistoricMatchesResultDto> getHistoricMatchesResultDto = xmlSoccerServiceBean
				.getHistoricMatchesByFixtureMatchID(id);

		if (getHistoricMatchesResultDto.size() > 0) {
			return HistoricMatchDataConverter.convert(
					getHistoricMatchesResultDto.get(0), leagueId + "", season);
		}
		return null;
	}

	public List<GetLeagueStandingsResultDto> getLeagueStandingsBySeason(
			String league, String season) {
		return xmlSoccerServiceBean.getLeagueStandingsBySeason(league, season);
	}

	public List<GetAllOddsResultDto> getAllOddsByFixtureMatchId(Integer id) {
		return xmlSoccerServiceBean.getAllOddsByFixtureMatchId(id);
	}

	public List<GetLiveScoreResultDto> getLivescores() {
		return xmlSoccerServiceBean.getLiveScore();

	}
}
