package com.paolorizzo.predictor.hibernate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JOBS_CONFIGURATIONS")
public class JobConfiguration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 99798403585168744L;

	@Id
	@Column(name = "NAME", length = 50)
	private String name;

	@Column(name = "CRON_EXPRESSION", length = 30)
	private String cronExpression;

	@Column(name = "JOB_GROUP", length = 10)
	private String jobGroup;

	@Column(name = "CLASS_NAME", length = 200)
	private String className;

	@Column(name = "LAST_EXECUTION", length = 200)
	private Date lastExecution;

	public JobConfiguration() {
		// TODO Auto-generated constructor stub
	}

	public JobConfiguration(String name, String cronExpression,
			String jobGroup, String className, Date lastExecution) {
		super();
		this.name = name;
		this.cronExpression = cronExpression;
		this.jobGroup = jobGroup;
		this.className = className;
		this.lastExecution = lastExecution;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Date getLastExecution() {
		return lastExecution;
	}

	public void setLastExecution(Date lastExecution) {
		this.lastExecution = lastExecution;
	}

}
