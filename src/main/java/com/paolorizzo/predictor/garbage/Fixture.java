package com.paolorizzo.predictor.garbage;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "FIXTURES")
public class Fixture implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3423513043217479945L;

	@Id
	@Column(name = "AWAY_TEAM_CODE", length = 100)
	private String awayTeamCode;

	@Id
	@Column(name = "DATE", nullable = false)
	private Long date;

	@Id
	@Column(name = "HOME_TEAM_CODE", length = 100)
	private String homeTeamCode;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPETITION_ID", nullable = false)
	private Competition competition;

	
	@Column(name = "HALFTIME_HOME_GOALS", length = 3)
	private Integer halfTimeHomeGoals;

	@Column(name = "HALFTIME_AWAY_GOALS", length = 3)
	private Integer halfTimeAwayGoals;

	@Column(name = "EXTRATIME_HOME_GOALS", length = 3)
	private Integer extraTimeHomeGoals;

	@Column(name = "EXTRATIME_AWAY_GOALS", length = 3)
	private Integer extraTimeAwayGoals;

	@Column(name = "STATUS", nullable = false, length = 30)
	private String status;

	@Column(name = "MATCHDAY", nullable = false, length = 3)
	private String matchday;

	@Column(name = "AWAY_TEAM_NAME", length = 100)
	private String awayTeamName;

	
	@Column(name = "HOME_TEAM_NAME", length = 100)
	private String homeTeamName;

	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "HOME_GOALS"),
			@JoinColumn(name = "AWAY_GOALS") })
	private FinalScore finalScore;
	
	
	@Column(name = "URL", length = 200)
	private String url;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "FIXTURES_BETTYPES", joinColumns = {
			@JoinColumn(name = "AWAY_TEAM_NAME"), @JoinColumn(name = "DATE"),
			@JoinColumn(name = "HOME_TEAM_NAME"),
			@JoinColumn(name = "COMPETITION_ID") }, inverseJoinColumns = { @JoinColumn(name = "BETTYPE_ID") })
	private List<Bettype> bettypes;

	
	public Fixture() {
		// TODO Auto-generated constructor stub
	}

	public Fixture(Integer halfTimeHomeGoals, Integer halfTimeAwayGoals,
			Integer extraTimeHomeGoals, Integer extraTimeAwayGoals,
			String status, String matchday, String awayTeamName,
			String awayTeamCode, Long date, String homeTeamName,
			String homeTeamCode, Competition competition,
			FinalScore finalScore, String url, List<Bettype> bettypes) {
		super();
		this.halfTimeHomeGoals = halfTimeHomeGoals;
		this.halfTimeAwayGoals = halfTimeAwayGoals;
		this.extraTimeHomeGoals = extraTimeHomeGoals;
		this.extraTimeAwayGoals = extraTimeAwayGoals;
		this.status = status;
		this.matchday = matchday;
		this.awayTeamName = awayTeamName;
		this.awayTeamCode = awayTeamCode;
		this.date = date;
		this.homeTeamName = homeTeamName;
		this.homeTeamCode = homeTeamCode;
		this.competition = competition;
		this.finalScore = finalScore;
		this.url = url;

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMatchday() {
		return matchday;
	}

	public void setMatchday(String matchday) {
		this.matchday = matchday;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}

	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	public Integer getHalfTimeHomeGoals() {
		return halfTimeHomeGoals;
	}

	public void setHalfTimeHomeGoals(Integer halfTimeHomeGoals) {
		this.halfTimeHomeGoals = halfTimeHomeGoals;
	}

	public Integer getHalfTimeAwayGoals() {
		return halfTimeAwayGoals;
	}

	public void setHalfTimeAwayGoals(Integer halfTimeAwayGoals) {
		this.halfTimeAwayGoals = halfTimeAwayGoals;
	}

	public Integer getExtraTimeHomeGoals() {
		return extraTimeHomeGoals;
	}

	public void setExtraTimeHomeGoals(Integer extraTimeHomeGoals) {
		this.extraTimeHomeGoals = extraTimeHomeGoals;
	}

	public Integer getExtraTimeAwayGoals() {
		return extraTimeAwayGoals;
	}

	public void setExtraTimeAwayGoals(Integer extraTimeAwayGoals) {
		this.extraTimeAwayGoals = extraTimeAwayGoals;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Bettype> getBettypes() {
		return bettypes;
	}

	public void setBettypes(List<Bettype> bettypes) {
		this.bettypes = bettypes;
	}

	public FinalScore getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(FinalScore finalScore) {
		this.finalScore = finalScore;
	}

	




	@Override
	public String toString() {
		return "Fixture [halfTimeHomeGoals=" + halfTimeHomeGoals
				+ ", halfTimeAwayGoals=" + halfTimeAwayGoals
				+ ", extraTimeHomeGoals=" + extraTimeHomeGoals
				+ ", extraTimeAwayGoals=" + extraTimeAwayGoals + ", status="
				+ status + ", matchday=" + matchday + ", awayTeamName="
				+ awayTeamName + ", awayTeamCode=" + awayTeamCode + ", date="
				+ date + ", homeTeamName=" + homeTeamName + ", homeTeamCode="
				+ homeTeamCode + ", competition=" + competition
				+ ", finalScore=" + finalScore + ", url="
				+ url + ", bettypes=" + bettypes + "]";
	}

	public String getAwayTeamCode() {
		return awayTeamCode;
	}

	public void setAwayTeamCode(String awayTeamCode) {
		this.awayTeamCode = awayTeamCode;
	}

	public String getHomeTeamCode() {
		return homeTeamCode;
	}

	public void setHomeTeamCode(String homeTeamCode) {
		this.homeTeamCode = homeTeamCode;
	}

}
