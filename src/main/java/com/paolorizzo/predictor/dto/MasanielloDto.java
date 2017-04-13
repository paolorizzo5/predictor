package com.paolorizzo.predictor.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

import com.paolorizzo.predictor.hibernate.model.Account;
import com.paolorizzo.predictor.hibernate.model.MasanielloRound;
import com.paolorizzo.predictor.services.response.user.dto.UserDto;

public class MasanielloDto {
	
	private String name;
	
	private String eventType;
	
	private UserDto userDto;
	
	private List<MasanielloRoundDto> masanielloRoundDtos;
	
	private BigDecimal amount;
	
	private Integer rounds;
	
	private Integer eventToWin;
	
	private BigDecimal percentage;
	
	private BigDecimal averageQuote;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	


	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
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
	
	

}
