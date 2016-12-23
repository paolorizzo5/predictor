package com.paolorizzo.predictor.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OddDto {

	private String bookmaker;

	private Date updateDate;

	private String betType;

	private BigDecimal home;

	private BigDecimal draw;

	private BigDecimal away;

	private BigDecimal oddHandicap;

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
