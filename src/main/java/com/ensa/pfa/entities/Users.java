package com.ensa.pfa.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity
public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String username;
	private String email;
	private String password;
	private Boolean active;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idClient")
	private Client client;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idOffreur")
	private Offreur offreur;
	
	@ManyToMany
	private Set<Roles> roles = new HashSet<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}


	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Users(String username, String email, String password, Boolean active) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.active = active;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Offreur getOffreur() {
		return offreur;
	}

	public void setOffreur(Offreur offreur) {
		this.offreur = offreur;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	
	public void setRole(Roles role) {
		this.roles.add(role);
	}
	
	
	
	
	
}
