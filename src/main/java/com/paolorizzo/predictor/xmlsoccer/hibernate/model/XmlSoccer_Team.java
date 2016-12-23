package com.paolorizzo.predictor.xmlsoccer.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "XMLSOCCER_TEAMS")
public class XmlSoccer_Team {

	@Id
	@Column(name = "ID", nullable = false)
	private int id;

	@Column(name = "NAME", nullable = false, length = 100)
	private String name;

	@Column(name = "COUNTRY", nullable = false, length = 100)
	private String country;

	@Column(name = "STADIUM", length = 100)
	private String stadium;

	@Column(name = "HOME_PAGE_URL", length = 300)
	private String homePageURL;

	@Column(name = "WIKI_LINK", length = 300)
	private String wikiLink;

	public XmlSoccer_Team(int id) {
		super();
		this.id = id;
	}

	public XmlSoccer_Team() {
		// TODO Auto-generated constructor stub
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

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public String getHomePageURL() {
		return homePageURL;
	}

	public void setHomePageURL(String homePageURL) {
		this.homePageURL = homePageURL;
	}

	public String getWikiLink() {
		return wikiLink;
	}

	public void setWikiLink(String wikiLink) {
		this.wikiLink = wikiLink;
	}

}
