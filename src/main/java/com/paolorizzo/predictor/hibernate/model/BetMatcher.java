package com.paolorizzo.predictor.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BET_MATCHERS")
public class BetMatcher implements Serializable{
	
	@Id
	@Column(name = "HOME_GOALS", nullable = false)
	private Integer homeGoals;

	@Id
	@Column(name = "AWAY_GOALS", nullable = false)
	private Integer awayGoals;
	
	@Id
	@Column(name = "EVENT_TYPE", nullable = false)
	private String eventType;
	
	public BetMatcher() {
		// TODO Auto-generated constructor stub
	}

	public BetMatcher(Integer homeGoals, Integer awayGoals, String eventType) {
		super();
		this.homeGoals = homeGoals;
		this.awayGoals = awayGoals;
		this.eventType = eventType;
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

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	

}
