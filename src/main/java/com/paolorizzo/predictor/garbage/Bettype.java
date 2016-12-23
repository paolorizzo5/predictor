package com.paolorizzo.predictor.garbage;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "BETTYPES")
public class Bettype implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -358840175678330052L;

	@Id
	@Column(name = "ID", nullable = false, length = 30)
	private String id;

	@Column(name = "DESCRIPTION", nullable = false, length = 200)
	private String description;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "BETTYPES_FINALSCORES", joinColumns = { @JoinColumn(name = "BETTYPE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "FINALSCORE_HOME_GOALS"),
			@JoinColumn(name = "FINALSCORE_AWAY_GOALS") })
	private List<FinalScore> finalScores;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BETTYPE_GROUP_ID", nullable = false)
	private BettypeGroup bettypeGroup;
	

	public Bettype() {
		// TODO Auto-generated constructor stub
	}

	

	public Bettype(String id, String description, BettypeGroup bettypeGroup) {
		super();
		this.id = id;
		this.description = description;
		this.bettypeGroup = bettypeGroup;
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

	public List<FinalScore> getFinalScores() {
		return finalScores;
	}

	public void setFinalScores(List<FinalScore> finalScores) {
		this.finalScores = finalScores;
	}

	@Override
	public String toString() {
		return "Bettype [id=" + id + ", description=" + description
				+ ", finalScores=" + finalScores + "]";
	}



	public BettypeGroup getBettypeGroup() {
		return bettypeGroup;
	}



	public void setBettypeGroup(BettypeGroup bettypeGroup) {
		this.bettypeGroup = bettypeGroup;
	}
	
	

}
