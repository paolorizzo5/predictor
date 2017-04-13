package com.paolorizzo.predictor.hibernate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BET_TYPES")
public class BetType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2289242654576939202L;
	@Id
	@Column(name = "NAME", nullable = false)
	private String name;
	
	public BetType() {
		// TODO Auto-generated constructor stub
	}

	public BetType(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
