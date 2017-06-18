package com.paolorizzo.predictor.dto;

import java.math.BigDecimal;
import java.util.List;

public class MasanielloDto {

	private String name;

	private Integer id;

	private String eventType;

	private MasanielloPlanDto masanielloPlanDto;

	private List<MasanielloRoundDto> masanielloRoundDtos;

	private BigDecimal amount;

	private Integer rounds;

	private Integer eventToWin;

	private BigDecimal percentage;

	private BigDecimal averageQuote;

	private BigDecimal finalAmount;

	private String panelClass;

	private Boolean showDetail;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public MasanielloPlanDto getMasanielloPlanDto() {
		return masanielloPlanDto;
	}

	public void setMasanielloPlanDto(MasanielloPlanDto masanielloPlanDto) {
		this.masanielloPlanDto = masanielloPlanDto;
	}

	public List<MasanielloRoundDto> getMasanielloRoundDtos() {
		return masanielloRoundDtos;
	}

	public void setMasanielloRoundDtos(List<MasanielloRoundDto> masanielloRoundDtos) {
		this.masanielloRoundDtos = masanielloRoundDtos;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getRounds() {
		return rounds;
	}

	public void setRounds(Integer rounds) {
		this.rounds = rounds;
	}

	public Integer getEventToWin() {
		return eventToWin;
	}

	public void setEventToWin(Integer eventToWin) {
		this.eventToWin = eventToWin;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public BigDecimal getAverageQuote() {
		return averageQuote;
	}

	public void setAverageQuote(BigDecimal averageQuote) {
		this.averageQuote = averageQuote;
	}

	public BigDecimal getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(BigDecimal finalAmount) {
		this.finalAmount = finalAmount;
	}

	public String getPanelClass() {
		return panelClass;
	}

	public void setPanelClass(String panelClass) {
		this.panelClass = panelClass;
	}

	public Boolean getShowDetail() {
		return showDetail;
	}

	public void setShowDetail(Boolean showDetail) {
		this.showDetail = showDetail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
