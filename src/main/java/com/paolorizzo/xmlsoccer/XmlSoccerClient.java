package com.paolorizzo.xmlsoccer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
import com.paolorizzo.predictor.business.WebServiceApiDetailBusiness;
import com.paolorizzo.predictor.constants.WebServiceApiDetailConstants;
import com.paolorizzo.predictor.dto.WebServiceApiDetailDto;
import com.paolorizzo.predictor.hibernate.model.WebServiceApiDetail;
import com.paolorizzo.predictor.threads.PopulateFixturesThread;
import com.paolorizzo.predictor.threads.PopulateHistoricMatchThread;
import com.paolorizzo.predictor.threads.PopulateStandingsThread;

import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_HistoricMatch;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_League;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Team;
import com.paolorizzo.xmlsoccer.business.FixtureBusiness;
import com.paolorizzo.xmlsoccer.business.HistoricMatchBusiness;
import com.paolorizzo.xmlsoccer.business.LeagueBusiness;
import com.paolorizzo.xmlsoccer.business.StandingBusiness;
import com.paolorizzo.xmlsoccer.business.TeamBusiness;
import com.paolorizzo.xmlsoccer.business.TeamProgressionStatsBusiness;
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

//	@Autowired
//	private OddBusiness oddBusiness;

	@Autowired
	private StandingBusiness standingBusiness;

	@Autowired
	private TeamProgressionStatsBusiness teamProgressionStatsBusiness;
	
	@Autowired
	private WebServiceApiDetailBusiness webServiceApiDetailBusiness;

	@Autowired
	private XmlSoccerServiceImpl xmlSoccerServiceBean;

	

	public XmlSoccerClient(LeagueBusiness leagueBusiness, TeamBusiness teamBusiness,
			HistoricMatchBusiness historicMatchBusiness, FixtureBusiness fixtureBusiness,
			StandingBusiness standingBusiness, TeamProgressionStatsBusiness teamProgressionStatsBusiness
			, WebServiceApiDetailBusiness webServiceApiDetailBusiness,
			XmlSoccerServiceImpl xmlSoccerServiceBean) {
		super();
		this.leagueBusiness = leagueBusiness;
		this.teamBusiness = teamBusiness;
		this.historicMatchBusiness = historicMatchBusiness;
		this.fixtureBusiness = fixtureBusiness;
		this.standingBusiness = standingBusiness;
		this.teamProgressionStatsBusiness = teamProgressionStatsBusiness;
		this.webServiceApiDetailBusiness = webServiceApiDetailBusiness;
		this.xmlSoccerServiceBean = xmlSoccerServiceBean;
		
	}

	public XmlSoccerClient() {
		// TODO Auto-generated constructor stub
	}

	public void execute() throws InterruptedException {
		xmlSoccerServiceBean
				.setApiKey("CHGUCEAMXWHYTZWGNWSLORFLDOCOEIYLKRPNYYZQJEPTKWWIGT");
		xmlSoccerServiceBean
				.setServiceUrl("http://www.xmlsoccer.com/footballdatademo.asmx");
		
		if(webServiceApiDetailBusiness.isEmpty()){
			populateWebServiceApiDetails();
		}

		// se è tutto vuoto faccio il primo popolamento
		
		if (teamBusiness.isEmpty()) {
			populateTeams();
		}

		//populateLeagues();
		
		
		
		List<Integer> updatedTeams = new ArrayList<Integer>();
		
		if(teamProgressionStatsBusiness.isEmpty()){
			for (XmlSoccer_Team xmlSoccer_Team : teamBusiness.list()) {
				if(!updatedTeams.contains(xmlSoccer_Team.getId())){
					updatedTeams.add(xmlSoccer_Team.getId());
					teamBusiness.updateTeamProgressionStats(xmlSoccer_Team);
				}
			}
		}

	}

	
	private void populateWebServiceApiDetails() {
		List<WebServiceApiDetail> webServiceApiDetails = new ArrayList<WebServiceApiDetail>(); 
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_ALL_GROUPS_BY_LEAGUES_AND_SEASON,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_ALL_LEAGUES,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_ALL_ODDS_BY_FIXTURE_MATCH_ID,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_ALL_TEAMS,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_ALL_TEAMS_BY_LEAGUE_AND_SEASON,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_CUP_STANDINGS_BY_GROUP_ID,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_EARLIEST_MATCH_DATE_PER_LEAGUE,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_FIXTURE_MATCH_BY_ID,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_FIXTURES_BY_DATE_INTERVAL,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_FIXTURES_BY_DATE_INTERVAL_AND_LEAGUE,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_FIXTURES_BY_DATE_INTERVAL_AND_TEAM,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_FIXTURES_BY_LEAGUE_AND_SEASON,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_HISTORIC_MATCHES_BY_FIXTURE_MATCH_ID,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_HISTORIC_MATCHES_BY_ID,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_HISTORIC_MATCHES_BY_LEAGUE_AND_DATE_INTERVAL,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_HISTORIC_MATCHES_BY_LEAGUE_AND_SEASON,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_HISTORIC_MATCHES_BY_TEAM_AND_DATE_INTERVAL,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_HISTORIC_MATCHES_BY_TEAMS_AND_DATE_INTERVAL,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_LEAGUE_STANDINGS_BY_SEASON,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_LIVESCORE,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_LIVESCORE_BY_LEAGUE,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_NEXT_MATCH_ODDS_BY_LEAGUE,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_ODDS_BY_FIXTURE_MATCH_ID,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_ODDS_BY_FIXTURE_MATCH_ID2,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_PLAYER_BY_ID,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_PLAYERS_BY_TEAM,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_PLAYOFF_FIXTURES_BY_LEAGUE_AND_SEASON,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_RESCHEDULE_OF_MATCH_DATES_BY_FIXTURE_MATCH_ID,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_TEAM,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_TOPSCORERS_BY_GROUP_ID,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.GET_TOPSCORERS_BY_LEAGUE_AND_SEASON,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.I_M_ALIVE,new Date(),new Long(3600000)));
		webServiceApiDetails.add(new WebServiceApiDetail(WebServiceApiDetailConstants.IS_MY_API_KEY_PUT_ON_SPAMMERS_LIST,new Date(),new Long(3600000)));
		
		webServiceApiDetailBusiness.addAll(webServiceApiDetails);
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

	@SuppressWarnings("unused")
	@Transactional(readOnly = false)
	private void populateLeagues() throws InterruptedException {
		
		
		logger.debug("populateLeagues");
		List<XmlSoccer_League> leagues = new ArrayList<XmlSoccer_League>();
		List<XmlSoccer_HistoricMatch> historicMatches = new ArrayList<XmlSoccer_HistoricMatch>();
		// recupera tutte le leghe
		
		
		Collection<GetAllLeaguesResultDto> getAllLeaguesResultDtos = null;
		
		List<Integer> leaguesToProcess = new ArrayList<Integer>();
		
		
		if(leagueBusiness.isEmpty()){
			WebServiceApiDetailDto webServiceApiDetailDto = webServiceApiDetailBusiness.canMakeCall(WebServiceApiDetailConstants.GET_ALL_LEAGUES);
			
			if(webServiceApiDetailDto.getCanMakeCall()){
				getAllLeaguesResultDtos = xmlSoccerServiceBean.getAllLeagues();
				for (GetAllLeaguesResultDto getAllLeaguesResultDto : getAllLeaguesResultDtos) {
					leaguesToProcess.add(getAllLeaguesResultDto.getId());
					leagueBusiness.insert(LeagueDataConverter
							.convert(getAllLeaguesResultDto));
				}
			}else{
				logger.debug("impossibile effettuare la chiamata a " + WebServiceApiDetailConstants.GET_ALL_LEAGUES);
			}
		}else{
			leagues = leagueBusiness.list();
			for (XmlSoccer_League league : leagues) {
				leaguesToProcess.add(league.getId());
			}
		}
		
		
		
			
		
		Runnable populateHistoricMatchesThread = new PopulateHistoricMatchThread(
				leaguesToProcess,
				xmlSoccerServiceBean, historicMatchBusiness,webServiceApiDetailBusiness);
		
		new Thread(populateHistoricMatchesThread).start();
	
		Runnable populateFixturesThread = new PopulateFixturesThread(
				leaguesToProcess,
				xmlSoccerServiceBean, fixtureBusiness,webServiceApiDetailBusiness);
		
		new Thread(populateFixturesThread).start();
	
		Runnable populateStandingsThread = new PopulateStandingsThread(
				leaguesToProcess,
				xmlSoccerServiceBean, standingBusiness,webServiceApiDetailBusiness);
		new Thread(populateStandingsThread).start();
		
				
			
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
