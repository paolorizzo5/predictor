package com.paolorizzo.predictor.dto;

import java.util.Date;



public class WebServiceApiDetailDto {
	
	private String name;
	
	private Date lastExecutionDate;
	
	private Long callFrequency;
	
	private Long numberOfCalls;

	private Boolean canMakeCall;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastExecutionDate() {
		return lastExecutionDate;
	}

	public void setLastExecutionDate(Date lastExecutionDate) {
		this.lastExecutionDate = lastExecutionDate;
	}

	public Long getCallFrequency() {
		return callFrequency;
	}

	public void setCallFrequency(Long callFrequency) {
		this.callFrequency = callFrequency;
	}

	public Long getNumberOfCalls() {
		return numberOfCalls;
	}

	public void setNumberOfCalls(Long numberOfCalls) {
		this.numberOfCalls = numberOfCalls;
	}

	public Boolean getCanMakeCall() {
		return canMakeCall;
	}

	public void setCanMakeCall(Boolean canMakeCall) {
		this.canMakeCall = canMakeCall;
	}
	
	
}
