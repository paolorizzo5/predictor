package com.paolorizzo.predictor.services.request;

import java.math.BigDecimal;

public class GetDirettaFixturesRequest extends SimpleRequest {
	
	private String competition;
	
	private String homeTeam;
	private String awayTeam;
	
	
	private BigDecimal quota1From;
	private BigDecimal quotaXFrom;
	private BigDecimal quota2From;
	
	private BigDecimal quota1To;
	private BigDecimal quotaXTo;
	private BigDecimal quota2To;
	public String getCompetition() {
		return competition;
	}
	public void setCompetition(String competition) {
		this.competition = competition;
	}
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
	
	

}
