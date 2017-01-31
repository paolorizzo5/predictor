package com.paolorizzo.predictor.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.paolorizzo.predictor.dao.facade.UserDao;
import com.paolorizzo.predictor.hibernate.model.ProspectElement;
import com.paolorizzo.predictor.hibernate.model.User;
import com.paolorizzo.predictor.services.response.user.dto.UserDto;

public class ProspectDto {
	
	private String name;
	
	private BigDecimal initialAmount;
	
	private Integer duration;
	
	private Date insertDate;
	
	private BigDecimal dailyPercentageExpected;
	
	private List<ProspectElementDto> prospectElements;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public BigDecimal getInitialAmount() {
		return initialAmount;
	}


	public void setInitialAmount(BigDecimal initialAmount) {
		this.initialAmount = initialAmount;
	}


	public Integer getDuration() {
		return duration;
	}


	public void setDuration(Integer duration) {
		this.duration = duration;
	}


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	public BigDecimal getDailyPercentageExpected() {
		return dailyPercentageExpected;
	}


	public void setDailyPercentageExpected(BigDecimal dailyPercentageExpected) {
		this.dailyPercentageExpected = dailyPercentageExpected;
	}


	public List<ProspectElementDto> getProspectElements() {
		return prospectElements;
	}


	public void setProspectElements(List<ProspectElementDto> prospectElements) {
		this.prospectElements = prospectElements;
	}


	
	
	
	
	
}
