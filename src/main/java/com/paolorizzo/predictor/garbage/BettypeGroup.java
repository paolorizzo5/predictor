package com.paolorizzo.predictor.garbage;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BETTYPE_GROUPS")
public class BettypeGroup {
	
	@Id
	@Column(name = "ID", nullable = false, length = 30)
	private String id;
	
	@Column(name = "DESCRIPTION", nullable = false, length = 100)
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bettypeGroup")
	private List<Bettype> bettypes;
	
	
	public BettypeGroup() {
		// TODO Auto-generated constructor stub
	}


	public BettypeGroup(String id, String description, List<Bettype> bettypes) {
		super();
		this.id = id;
		this.description = description;
		this.bettypes = bettypes;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<Bettype> getBettypes() {
		return bettypes;
	}


	public void setBettypes(List<Bettype> bettypes) {
		this.bettypes = bettypes;
	}
	
	

}
