package com.paolorizzo.predictor.garbage;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FINALSCORES")
public class FinalScore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -358840175678330052L;

	@Id
	@Column(name = "HOME_GOALS", length = 3)
	private Integer homeGoals;

	@Id
	@Column(name = "AWAY_GOALS", length = 3)
	private Integer awayGoals;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "finalScore")
	private List<Fixture> fixtures;

	@ManyToMany(mappedBy = "finalScores", fetch = FetchType.LAZY)
	private List<Bettype> bettypes;

	public FinalScore() {
		// TODO Auto-generated constructor stub
	}

	public FinalScore(Integer homeGoals, Integer awayGoals) {
		super();
		this.homeGoals = homeGoals;
		this.awayGoals = awayGoals;
	}

	public Integer getHomeGoals() {
		return homeGoals;
	}

	public void setHomeGoals(Integer homeGoals) {
		this.homeGoals = homeGoals;
	}

	public Integer getAwayGoals() {
		return awayGoals;
	}

	public void setAwayGoals(Integer awayGoals) {
		this.awayGoals = awayGoals;
	}

	public List<Bettype> getBettypes() {
		return bettypes;
	}

	public void setBettypes(List<Bettype> bettypes) {
		this.bettypes = bettypes;
	}

	public List<Fixture> getFixtures() {
		return fixtures;
	}

	public void setFixtures(List<Fixture> fixtures) {
		this.fixtures = fixtures;
	}

	@Override
	public String toString() {
		return "FinalScore [homeGoals=" + homeGoals + ", awayGoals="
				+ awayGoals + ", fixtures=" + fixtures + ", bettypes="
				+ bettypes + "]";
	}

}
