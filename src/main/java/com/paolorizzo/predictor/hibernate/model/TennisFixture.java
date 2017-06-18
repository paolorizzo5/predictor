package com.paolorizzo.predictor.hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.paolorizzo.predictor.dto.FixtureDto;

@Entity
@Table(name = "DIRETTA_FIXTURES")
public class TennisFixture implements Serializable,Comparable<TennisFixture> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4817511977297134524L;

	@Id
	@Column(name = "DATE", nullable = false)
	private Date date;

	@Id
	@Column(name = "HOME_TEAM",length = 70, nullable = false)
	private String homeTeam;
	
	@Id
	@Column(name = "AWAY_TEAM",length = 70, nullable = false)
	private String awayTeam;


	@Column(name = "HOME_SETS")
	private Integer homeSets;

	@Column(name = "AWAY_SETS")
	private Integer awaySets;

	@Column(name = "QUOTA_1", nullable = false)
	private BigDecimal quota1;
	
	@Column(name = "QUOTA_2", nullable = false)
	private BigDecimal quota2;
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Integer getHomeSets() {
		return homeSets;
	}

	public void setHomeSets(Integer homeSets) {
		this.homeSets = homeSets;
	}

	public Integer getAwaySets() {
		return awaySets;
	}

	public void setAwaySets(Integer awaySets) {
		this.awaySets = awaySets;
	}

	public BigDecimal getQuota1() {
		return quota1;
	}

	public void setQuota1(BigDecimal quota1) {
		this.quota1 = quota1;
	}

	public BigDecimal getQuota2() {
		return quota2;
	}

	public void setQuota2(BigDecimal quota2) {
		this.quota2 = quota2;
	}

	@Override
	public int compareTo(TennisFixture o) {
		return o.getDate().compareTo(this.getDate());
	}

	
	
}
