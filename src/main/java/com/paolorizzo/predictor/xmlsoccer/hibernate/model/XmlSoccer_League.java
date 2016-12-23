package com.paolorizzo.predictor.xmlsoccer.hibernate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "XMLSOCCER_LEAGUES")
public class XmlSoccer_League implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5850645707307521142L;

	@Id
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "NAME", nullable = false, length = 50)
	private String name;

	@Column(name = "COUNTRY", nullable = false, length = 20)
	private String country;

	@Column(name = "HISTORICAL_DATA", nullable = false, length = 7)
	private String historicalData;

	@Column(name = "FIXTURES", nullable = false, length = 5)
	private String fixtures;

	@Column(name = "LIVESCORE", nullable = false, length = 5)
	private String livescore;

	@Column(name = "NUMBER_OF_MATCHES", nullable = false)
	private int numberOfMatches;

	@Column(name = "LATEST_MATCH", nullable = false)
	private Date latestMatch;

	public XmlSoccer_League() {
		// TODO Auto-generated constructor stub
	}

	public XmlSoccer_League(Integer id, String name, String country,
			String historicalData, String fixtures, String livescore,
			int numberOfMatches, Date latestMatch) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.historicalData = historicalData;
		this.fixtures = fixtures;
		this.livescore = livescore;
		this.numberOfMatches = numberOfMatches;
		this.latestMatch = latestMatch;
	}

	public XmlSoccer_League(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHistoricalData() {
		return historicalData;
	}

	public void setHistoricalData(String historicalData) {
		this.historicalData = historicalData;
	}

	public String getFixtures() {
		return fixtures;
	}

	public void setFixtures(String fixtures) {
		this.fixtures = fixtures;
	}

	public String getLivescore() {
		return livescore;
	}

	public void setLivescore(String livescore) {
		this.livescore = livescore;
	}

	public int getNumberOfMatches() {
		return numberOfMatches;
	}

	public void setNumberOfMatches(int numberOfMatches) {
		this.numberOfMatches = numberOfMatches;
	}

	public Date getLatestMatch() {
		return latestMatch;
	}

	public void setLatestMatch(Date latestMatch) {
		this.latestMatch = latestMatch;
	}

}
