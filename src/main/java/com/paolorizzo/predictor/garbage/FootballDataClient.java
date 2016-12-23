package com.paolorizzo.predictor.business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.paolorizzo.predictor.client.model.v2.CompetitionFixtures_API;
import com.paolorizzo.predictor.client.model.v2.CompetitionTeams_API;
import com.paolorizzo.predictor.client.model.v2.Competition_API;
import com.paolorizzo.predictor.client.model.v2.FixtureDetail_API;
import com.paolorizzo.predictor.client.model.v2.Player_API;
import com.paolorizzo.predictor.client.model.v2.Team_API;
import com.paolorizzo.predictor.client.model.v2.competitionfixtures.Fixture;
import com.paolorizzo.predictor.constant.FixtureStatus;
import com.paolorizzo.predictor.dao.facade.CompetitionDao;
import com.paolorizzo.predictor.dao.facade.FinalScoreDao;
import com.paolorizzo.predictor.dao.facade.FixtureDao;
import com.paolorizzo.predictor.dao.facade.TeamDao;
import com.paolorizzo.predictor.hibernate.model.Bettype;
import com.paolorizzo.predictor.hibernate.model.Competition;
import com.paolorizzo.predictor.hibernate.model.FinalScore;
import com.paolorizzo.predictor.utils.SimpleUtils;

public class FootballDataClient {

	private CompetitionDao competitionDao;

	private TeamDao teamDao;

	private FixtureDao fixtureDao;

	private FinalScoreDao finalScoreDao;

	Logger logger = LogManager.getLogger("root");

	public FootballDataClient() {
		// TODO Auto-generated constructor stub
	}

	public FootballDataClient(CompetitionDao competitionDao, TeamDao teamDao,
			FixtureDao fixtureDao, FinalScoreDao finalScoreDao) {
		super();
		this.competitionDao = competitionDao;
		this.teamDao = teamDao;
		this.fixtureDao = fixtureDao;
		this.finalScoreDao = finalScoreDao;
	}

	// http://localhost:8080/RESTfulExample/json/product/get
	@Transactional(readOnly = false)
	public void populateDb() throws NumberFormatException, ParseException {

		logger.debug("populateDb");

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss");
		List<Competition_API> competitions = getCompetitions();

		for (Competition_API competition : competitions) {
			//if(competition.getId().equals("438")){
				logger.debug("populateDb - retrieved " + competitions.size()
						+ " competitions ");
				Competition c = new Competition(competition.getId(),
						Integer.parseInt(competition.getNumberOfGames()),
						Integer.parseInt(competition.getNumberOfTeams()),
						dateFormat.parse(competition.getLastUpdated()).getTime(),
						Integer.parseInt(competition.getCurrentMatchday()),
						Integer.parseInt(competition.getYear()),
						competition.getCaption(), competition.getLeague(),
						Integer.parseInt(competition.getNumberOfMatchdays()), null,
						null);

				competitionDao.insert(c);
				CompetitionTeams_API competitionTeams = getCompetitionTeams(competition
						.get_links().getTeams().getHref());

				for (Team_API team : competitionTeams.getTeams()) {
					long squadMarketValue = 0;
					try {
						squadMarketValue = Long.parseLong(team
								.getSquadMarketValue());
					} catch (Exception exception) {

					}
					String teamCode = team
							.get_links()
							.getSelf()
							.getHref()
							.replaceAll("http://api.football-data.org/v1/teams/",
									"");
					com.paolorizzo.predictor.hibernate.model.Team t = new com.paolorizzo.predictor.hibernate.model.Team(
							teamCode, squadMarketValue, team.getCrestUrl(),
							team.getName(), team.getShortName(), c);

					teamDao.insert(t);

					// // TODO DB INSERT
					// // try {
					// // List<Player> players = getPlayers(team.get_links()
					// // .getPlayers().getHref());
					// // for (Player player : players) {
					// // // TODO DB INSERT
					// // }
					// // } catch (Exception exception) {
					// //
					// // }
					//
					// }
					//
					// CompetitionFixtures competitionFixtures =
					// getFixtures(competition
					// .get_links().getFixtures().getHref());
					//
					// for (Fixture f : competitionFixtures.getFixtures()) {
					// f.get_links();
				}

				CompetitionFixtures_API competitionFixtures = getCompetitionfixtures(competition
						.get_links().getFixtures().getHref());

				if (competitionFixtures != null) {

					for (Fixture fixture : competitionFixtures.getFixtures()) {
						Integer extraTimeHomeGoals = null;
						Integer extraTimeAwayGoals = null;
						Integer halfTimeHomeGoals = null;
						Integer halfTimeAwayGoals = null;

						Integer fullTimeHomeGoals = SimpleUtils
								.getNullOrValue(fixture.getResult()
										.getGoalsHomeTeam());

						Integer fullTimeAwayGoals = SimpleUtils
								.getNullOrValue(fixture.getResult()
										.getGoalsAwayTeam());

						try {
							extraTimeHomeGoals = SimpleUtils.getNullOrValue(fixture
									.getResult().getExtraTime().getGoalsHomeTeam());
							extraTimeAwayGoals = SimpleUtils.getNullOrValue(fixture
									.getResult().getExtraTime().getGoalsAwayTeam());
						} catch (NullPointerException nullPointerException) {

						}

						try {
							halfTimeHomeGoals = SimpleUtils.getNullOrValue(fixture
									.getResult().getHalfTime().getGoalsHomeTeam());
							halfTimeAwayGoals = SimpleUtils.getNullOrValue(fixture
									.getResult().getHalfTime().getGoalsAwayTeam());
						} catch (NullPointerException nullPointerException) {

						}

						FinalScore finalScore = finalScoreDao.getByScore(
								fullTimeHomeGoals, fullTimeAwayGoals);

						String awayTeamCode = fixture
								.get_links()
								.getAwayTeam()
								.getHref()
								.replaceAll(
										"http://api.football-data.org/v1/teams/",
										"");
						String homeTeamCode = fixture
								.get_links()
								.getHomeTeam()
								.getHref()
								.replaceAll(
										"http://api.football-data.org/v1/teams/",
										"");

						com.paolorizzo.predictor.hibernate.model.Fixture f = new com.paolorizzo.predictor.hibernate.model.Fixture(
								halfTimeHomeGoals, halfTimeAwayGoals,
								extraTimeHomeGoals, extraTimeAwayGoals,
								fixture.getStatus(), fixture.getMatchday(),
								fixture.getAwayTeamName(), awayTeamCode, dateFormat
										.parse(fixture.getDate()).getTime(),
								fixture.getHomeTeamName(), homeTeamCode, c,
								finalScore,
								fixture.get_links().getSelf().getHref(), null);

						if (FixtureStatus._FINISHED.equals(fixture.getStatus())) {
							List<Bettype> bettypes = new ArrayList<Bettype>();
							bettypes.addAll(finalScore.getBettypes());
							f.setBettypes(bettypes);
						}

						fixtureDao.insert(f);

					}
				//}
			}
		}
	}

	private CompetitionFixtures_API getCompetitionfixtures(String href) {
		String ret = clientCall(href);
		System.out.println(ret);
		ObjectMapper mapper = new ObjectMapper();
		try {

			CompetitionFixtures_API competitionFixtures = mapper.readValue(ret,
					CompetitionFixtures_API.class);

			return competitionFixtures;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private CompetitionFixtures_API getFixtures(String href) {
		String ret = clientCall(href);

		ObjectMapper mapper = new ObjectMapper();
		try {
			CompetitionFixtures_API competitionFixtures = mapper.readValue(ret,
					CompetitionFixtures_API.class);

			return competitionFixtures;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private List<Player_API> getPlayers(String href) {
		String ret = clientCall(href);
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Player_API> players = Arrays.asList(mapper.readValue(ret,
					Player_API[].class));

			return players;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private CompetitionTeams_API getCompetitionTeams(String href) {
		String ret = clientCall(href);
		ObjectMapper mapper = new ObjectMapper();
		try {

			CompetitionTeams_API competitionTeams = mapper.readValue(ret,
					CompetitionTeams_API.class);

			return competitionTeams;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private List<Competition_API> getCompetitions() {
		String ret = clientCall("http://api.football-data.org/v1/competitions/");
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Competition_API> competitions = Arrays.asList(mapper
					.readValue(ret, Competition_API[].class));

			return competitions;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
		T[] arr = new Gson().fromJson(s, clazz);
		return Arrays.asList(arr); // or return Arrays.asList(new
									// Gson().fromJson(s, clazz)); for a
									// one-liner
	}

	private String clientCall(String address) {

		URL url;
		try {

			url = new URL(address);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "JsonObject");
			conn.setRequestProperty("X-Auth-Token",
					"150be6b9b8274610a09fd939883ed914");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			String output;
			String ret = "";
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				ret = ret + output;

			}
			conn.disconnect();
			return ret;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public FixtureDetail_API getFixture(String url) {
		String ret = clientCall(url);
		ObjectMapper mapper = new ObjectMapper();
		try {
			FixtureDetail_API fixture_API = mapper.readValue(ret,
					FixtureDetail_API.class);

			return fixture_API;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public FixtureDao getFixtureDao() {
		return fixtureDao;
	}

	public void setFixtureDao(FixtureDao fixtureDao) {
		this.fixtureDao = fixtureDao;
	}

	public CompetitionDao getCompetitionDao() {
		return competitionDao;
	}

	public void setCompetitionDao(CompetitionDao competitionDao) {
		this.competitionDao = competitionDao;
	}

	public TeamDao getTeamDao() {
		return teamDao;
	}

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}

	public FinalScoreDao getFinalScoreDao() {
		return finalScoreDao;
	}

	public void setFinalScoreDao(FinalScoreDao finalScoreDao) {
		this.finalScoreDao = finalScoreDao;
	}

}
