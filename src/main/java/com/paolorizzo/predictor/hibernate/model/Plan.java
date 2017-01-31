package com.paolorizzo.predictor.hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="PLANS")
public class Plan implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1199298712640421931L;

	@Id
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "plan", cascade = CascadeType.ALL)
	private Prospect prospect;
	
	@Id
	@Column(name = "INCREMENTAL", nullable = false)
	private Integer incremental;
	
	@Column(name = "AMOUNT", nullable = false)
	private Integer amount;
	
	@Column(name = "MOLTIPLICATOR", nullable = false)
	private BigDecimal moltiplicator;

	public Prospect getProspect() {
		return prospect;
	}

	public void setProspect(Prospect prospect) {
		this.prospect = prospect;
	}

	public Integer getIncremental() {
		return incremental;
	}

	public void setIncremental(Integer incremental) {
		this.incremental = incremental;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public BigDecimal getMoltiplicator() {
		return moltiplicator;
	}

	public void setMoltiplicator(BigDecimal moltiplicator) {
		this.moltiplicator = moltiplicator;
	}
	
	

}
