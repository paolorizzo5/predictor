package com.paolorizzo.predictor.xmlsoccer.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "XMLSOCCER_TEAMS_PROGRESSION_STATS")
public class XmlSoccer_TeamProgressionStats implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8675210824870509838L;

	@Id
	@OneToOne(fetch = FetchType.LAZY)
	private XmlSoccer_Team team;
	
	
	@Column(name = "CONSECUTIVE_NOT_WINNING_STREAK")
	private Integer consecutiveNotWinningStreak;
	
	@Column(name = "CONSECUTIVE_NOT_DRAWING_STREAK")
	private Integer consecutiveNotDrawingStreak;
	
	@Column(name = "CONSECUTIVE_NOT_LOSING_STREAK")
	private Integer consecutiveNotLosingStreak;

	public XmlSoccer_Team getTeam() {
		return team;
	}

	public void setTeam(XmlSoccer_Team team) {
		this.team = team;
	}

	public Integer getConsecutiveNotWinningStreak() {
		return consecutiveNotWinningStreak;
	}

	public void setConsecutiveNotWinningStreak(Integer consecutiveNotWinningStreak) {
		this.consecutiveNotWinningStreak = consecutiveNotWinningStreak;
	}

	public Integer getConsecutiveNotDrawingStreak() {
		return consecutiveNotDrawingStreak;
	}

	public void setConsecutiveNotDrawingStreak(Integer consecutiveNotDrawingStreak) {
		this.consecutiveNotDrawingStreak = consecutiveNotDrawingStreak;
	}

	public Integer getConsecutiveNotLosingStreak() {
		return consecutiveNotLosingStreak;
	}

	public void setConsecutiveNotLosingStreak(Integer consecutiveNotLosingStreak) {
		this.consecutiveNotLosingStreak = consecutiveNotLosingStreak;
	}

}
