package com.paolorizzo.predictor.hibernate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="WEBSERVICE_API_DETAIL")
public class WebServiceApiDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3870314142816073528L;

	@Id
	@Column(name = "NAME", nullable = false, length = 50)
	private String name;
	
	@Column(name = "LAST_EXECUTION_DATE", nullable = false)
	private Date lastExecutionDate;
	
	@Column(name = "CALL_FREQUENCY", nullable = false)
	private Long callFrequency;
	

	@Column(name = "NUMBER_OF_CALLS", nullable = false)
	private Long numberOfCalls;

	
public WebServiceApiDetail() {
	// TODO Auto-generated constructor stub
}
	
	public WebServiceApiDetail(String name, Date lastExecutionDate, Long callFrequency) {
		super();
		this.name = name;
		this.lastExecutionDate = lastExecutionDate;
		this.callFrequency = callFrequency;
		this.numberOfCalls = new Long(0);
	}

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
	
	
	
	
	

}
