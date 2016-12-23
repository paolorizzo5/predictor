package com.paolorizzo.xmlsoccer.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_HistoricMatch;

public interface HistoricMatchDao {

	void insert(XmlSoccer_HistoricMatch xmlSoccer_HistoricMatch);

	List<XmlSoccer_HistoricMatch> list();

	void delete(XmlSoccer_HistoricMatch xmlSoccer_HistoricMatch);

	void update(XmlSoccer_HistoricMatch xmlSoccer_HistoricMatch);

	XmlSoccer_HistoricMatch getById(String id);

	List<XmlSoccer_HistoricMatch> getHistoricMatchesByLeagueAndSeason(
			String league, String season);

	List<XmlSoccer_HistoricMatch> getHistoricMatchesByTeams(String homeTeamId,
			String awayTeamId);

	List<XmlSoccer_HistoricMatch> getLast5(String teamId);

	List<XmlSoccer_HistoricMatch> getSeasonStats(String homeTeamId,
			String awayTeamId, String league, String season);

}
