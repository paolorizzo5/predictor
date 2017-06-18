package com.paolorizzo.predictor.services.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateMasanielloRequest  extends SimpleRequest implements Serializable{
	
	private ArrayList<PlanFilterDto> filtersBase;
	private ArrayList<PlanFilterDto> filtersAdvanced;
	private String masanielloUserEmail;
	
	
	private String masanielloName;
	
	private BigDecimal masanielloAmount;
	
	private Integer masanielloRounds;
	
	private Integer masanielloEventToWin;
	
	private BigDecimal masanielloAverageQuote;
	
	private BigDecimal masanielloAdditionalQuote;
	
	private BigDecimal masanielloPercentage;
	
	private BigDecimal initialAmount;
	
	private BigDecimal patrimonyPercentage;
	

	private BigDecimal lowerByWin;

	private BigDecimal raiseByLoss;
	
	private Integer id;

	
	
	
	public String getMasanielloName() {
		return masanielloName;
	}
	public void setMasanielloName(String masanielloName) {
		this.masanielloName = masanielloName;
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public BigDecimal getMasanielloAdditionalQuote() {
		return masanielloAdditionalQuote;
	}
	public void setMasanielloAdditionalQuote(BigDecimal masanielloAdditionalQuote) {
		this.masanielloAdditionalQuote = masanielloAdditionalQuote;
	}
	public ArrayList<PlanFilterDto> getFiltersBase() {
		return filtersBase;
	}
	public void setFiltersBase(ArrayList<PlanFilterDto> filtersBase) {
		this.filtersBase = filtersBase;
	}
	public ArrayList<PlanFilterDto> getFiltersAdvanced() {
		return filtersAdvanced;
	}
	public void setFiltersAdvanced(ArrayList<PlanFilterDto> filtersAdvanced) {
		this.filtersAdvanced = filtersAdvanced;
	}
	public BigDecimal getLowerByWin() {
		return lowerByWin;
	}
	public void setLowerByWin(BigDecimal lowerByWin) {
		this.lowerByWin = lowerByWin;
	}
	public BigDecimal getRaiseByLoss() {
		return raiseByLoss;
	}
	public void setRaiseByLoss(BigDecimal raiseByLoss) {
		this.raiseByLoss = raiseByLoss;
	}
	
	
	

}
