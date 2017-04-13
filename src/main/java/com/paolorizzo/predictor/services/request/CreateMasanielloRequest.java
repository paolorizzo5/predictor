package com.paolorizzo.predictor.services.request;

import java.math.BigDecimal;

public class CreateMasanielloRequest extends SimpleRequest {
	
	private String competition;
	
	private String homeTeam;
	private String awayTeam;
	
	private BigDecimal quota1From;
	private BigDecimal quotaXFrom;
	private BigDecimal quota2From;
	
	private BigDecimal quota1To;
	private BigDecimal quotaXTo;
	private BigDecimal quota2To;
	
	
	private String masanielloUserEmail;
	
	
	private String masanielloName;
	
	private String masanielloEventType;
	
	private BigDecimal masanielloAmount;
	
	private Integer masanielloRounds;
	
	private Integer masanielloEventToWin;
	
	private BigDecimal masanielloAverageQuote;
	
	private BigDecimal masanielloPercentage;
	
	private BigDecimal initialAmount;
	
	private BigDecimal patrimonyPercentage;

	
	
	public BigDecimal getQuota1From() {
		return quota1From;
	}
	public void setQuota1From(BigDecimal quota1From) {
		this.quota1From = quota1From;
	}
	public BigDecimal getQuotaXFrom() {
		return quotaXFrom;
	}
	public void setQuotaXFrom(BigDecimal quotaXFrom) {
		this.quotaXFrom = quotaXFrom;
	}
	public BigDecimal getQuota2From() {
		return quota2From;
	}
	public void setQuota2From(BigDecimal quota2From) {
		this.quota2From = quota2From;
	}
	public BigDecimal getQuota1To() {
		return quota1To;
	}
	public void setQuota1To(BigDecimal quota1To) {
		this.quota1To = quota1To;
	}
	public BigDecimal getQuotaXTo() {
		return quotaXTo;
	}
	public void setQuotaXTo(BigDecimal quotaXTo) {
		this.quotaXTo = quotaXTo;
	}
	public BigDecimal getQuota2To() {
		return quota2To;
	}
	public void setQuota2To(BigDecimal quota2To) {
		this.quota2To = quota2To;
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
	public String getCompetition() {
		return competition;
	}
	public void setCompetition(String competition) {
		this.competition = competition;
	}
	public String getMasanielloName() {
		return masanielloName;
	}
	public void setMasanielloName(String masanielloName) {
		this.masanielloName = masanielloName;
	}
	public String getMasanielloEventType() {
		return masanielloEventType;
	}
	public void setMasanielloEventType(String masanielloEventType) {
		this.masanielloEventType = masanielloEventType;
	}
	public BigDecimal getMasanielloAmount() {
		return masanielloAmount;
	}
	public void setMasanielloAmount(BigDecimal masanielloAmount) {
		this.masanielloAmount = masanielloAmount;
	}
	public Integer getMasanielloRounds() {
		return masanielloRounds;
	}
	public void setMasanielloRounds(Integer masanielloRounds) {
		this.masanielloRounds = masanielloRounds;
	}
	public Integer getMasanielloEventToWin() {
		return masanielloEventToWin;
	}
	public void setMasanielloEventToWin(Integer masanielloEventToWin) {
		this.masanielloEventToWin = masanielloEventToWin;
	}
	public BigDecimal getMasanielloPercentage() {
		return masanielloPercentage;
	}
	public void setMasanielloPercentage(BigDecimal masanielloPercentage) {
		this.masanielloPercentage = masanielloPercentage;
	}
	public BigDecimal getInitialAmount() {
		return initialAmount;
	}
	public void setInitialAmount(BigDecimal initialAmount) {
		this.initialAmount = initialAmount;
	}
	public String getMasanielloUserEmail() {
		return masanielloUserEmail;
	}
	public void setMasanielloUserEmail(String masanielloUserEmail) {
		this.masanielloUserEmail = masanielloUserEmail;
	}
	
	public BigDecimal getMasanielloAverageQuote() {
		return masanielloAverageQuote;
	}
	public void setMasanielloAverageQuote(BigDecimal masanielloAverageQuote) {
		this.masanielloAverageQuote = masanielloAverageQuote;
	}
	public BigDecimal getPatrimonyPercentage() {
		return patrimonyPercentage;
	}
	public void setPatrimonyPercentage(BigDecimal patrimonyPercentage) {
		this.patrimonyPercentage = patrimonyPercentage;
	}
	
	
	

}
