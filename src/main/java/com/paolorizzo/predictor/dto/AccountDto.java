package com.paolorizzo.predictor.dto;

import java.io.Serializable;
import java.util.List;

public class AccountDto implements Serializable{
	
	private static final long serialVersionUID = 6627605908926623615L;

	private String name;
	
	private String description;
	
	private List<MoneyTransactionDto> moneyTransactionDtos;
	
	private Long insertDate;
	
	private List<BetDto> bets;
	
	private ProspectDto prospect;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public List<MoneyTransactionDto> getMoneyTransactionDtos() {
		return moneyTransactionDtos;
	}

	public void setMoneyTransactionDtos(List<MoneyTransactionDto> moneyTransactionDtos) {
		this.moneyTransactionDtos = moneyTransactionDtos;
	}

	public Long getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Long insertDate) {
		this.insertDate = insertDate;
	}

	public List<BetDto> getBets() {
		return bets;
	}

	public void setBets(List<BetDto> bets) {
		this.bets = bets;
	}

	public ProspectDto getProspect() {
		return prospect;
	}

	public void setProspect(ProspectDto prospect) {
		this.prospect = prospect;
	}

}
