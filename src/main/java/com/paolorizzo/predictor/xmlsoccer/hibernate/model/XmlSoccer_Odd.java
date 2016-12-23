package com.paolorizzo.predictor.xmlsoccer.hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "XMLSOCCER_ODDS")
public class XmlSoccer_Odd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2793286266048701416L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FIXTURE_ID")
	private XmlSoccer_Fixture fixture;

	@Id
	@Column(name = "BOOKMAKER")
	private String bookmaker;

	@Id
	@Column(name = "UPDATE_DATE")
	private Date updateDate;

	@Id
	@Column(name = "BET_TYPE")
	private String betType;

	@Column(name = "HOME")
	private BigDecimal home;

	@Column(name = "DRAW")
	private BigDecimal draw;

	@Column(name = "AWAY")
	private BigDecimal away;

	@Id
	@Column(name = "ODD_HANDICAP")
	private BigDecimal oddHandicap;

	public XmlSoccer_Odd() {
		// TODO Auto-generated constructor stub
	}

	public XmlSoccer_Fixture getFixture() {
		return fixture;
	}

	public void setFixture(XmlSoccer_Fixture fixture) {
		this.fixture = fixture;
	}

	public String getBookmaker() {
		return bookmaker;
	}

	public void setBookmaker(String bookmaker) {
		this.bookmaker = bookmaker;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getBetType() {
		return betType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}

	public BigDecimal getHome() {
		return home;
	}

	public void setHome(BigDecimal home) {
		this.home = home;
	}

	public BigDecimal getDraw() {
		return draw;
	}

	public void setDraw(BigDecimal draw) {
		this.draw = draw;
	}

	public BigDecimal getAway() {
		return away;
	}

	public void setAway(BigDecimal away) {
		this.away = away;
	}

	public BigDecimal getOddHandicap() {
		return oddHandicap;
	}

	public void setOddHandicap(BigDecimal oddHandicap) {
		this.oddHandicap = oddHandicap;
	}

}
