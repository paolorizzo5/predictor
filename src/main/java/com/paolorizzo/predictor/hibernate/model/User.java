package com.paolorizzo.predictor.hibernate.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4465963414487195199L;

	@Id
	@Column(name = "EMAIL", unique = true, nullable = false, length = 80)
	private String email;

	@Column(name = "PASSWORD", nullable = false, length = 32)
	private String password;
	
	@Column(name = "STATUS", nullable = false, length = 10)
	private String status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	private List<Prospect> prospects;
	
	
	@Column(name = "PORTFOLIO_AMOUNT", nullable = false)
	private BigDecimal portFolioAmount;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
	@Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	private Set<MasanielloPlan> masanielloPlans;
	

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	

	public User(String email) {
		super();
		this.email = email;
	}



	public User(String email, String password,String status) {
		super();
		this.email = email;
		this.password = password;
		this.status = status;
		this.portFolioAmount = new BigDecimal(0);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + "]";
	}



	public List<Prospect> getProspects() {
		return prospects;
	}



	public void setProspects(List<Prospect> prospects) {
		this.prospects = prospects;
	}



	public BigDecimal getPortFolioAmount() {
		return portFolioAmount;
	}



	public void setPortFolioAmount(BigDecimal portFolioAmount) {
		this.portFolioAmount = portFolioAmount;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public Set<MasanielloPlan> getMasanielloPlans() {
		return masanielloPlans;
	}



	public void setMasanielloPlans(Set<MasanielloPlan> masanielloPlans) {
		this.masanielloPlans = masanielloPlans;
	}




	

}
