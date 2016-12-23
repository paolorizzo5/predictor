package com.paolorizzo.predictor.garbage;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TEAMS")
public class Team implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7537457692513200396L;

	@Id
	@Column(name = "CODE", length = 50)
	private String code;

	@Column(name = "SQUAD_MARKET_VALUE")
	private Long squadMarketValue;

	@Column(name = "CREST_URL", length = 200)
	private String crestUrl;

	@Column(name = "NAME", nullable = false, length = 70)
	private String name;

	@Column(name = "SHORT_NAME", length = 30)
	private String shortName;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPETITION_ID", nullable = false)
	private Competition competition;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "team")
	private List<TeamStats> teamStats;

	public Team() {
		// TODO Auto-generated constructor stub
	}

	public Team(String code, Long squadMarketValue, String crestUrl,
			String name, String shortName, Competition competition) {
		super();
		this.code = code;
		this.squadMarketValue = squadMarketValue;
		this.crestUrl = crestUrl;
		this.name = name;
		this.shortName = shortName;
		this.competition = competition;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getSquadMarketValue() {
		return squadMarketValue;
	}

	public void setSquadMarketValue(Long squadMarketValue) {
		this.squadMarketValue = squadMarketValue;
	}

	public String getCrestUrl() {
		return crestUrl;
	}

	public void setCrestUrl(String crestUrl) {
		this.crestUrl = crestUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public List<TeamStats> getTeamStats() {
		return teamStats;
	}

	public void setTeamStats(List<TeamStats> teamStats) {
		this.teamStats = teamStats;
	}

	@Override
	public String toString() {
		return "Team [code=" + code + ", squadMarketValue=" + squadMarketValue
				+ ", crestUrl=" + crestUrl + ", name=" + name + ", shortName="
				+ shortName + ", competition=" + competition + ", teamStats="
				+ teamStats + "]";
	}

}
