package com.ensa.pfa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Categorie {
	@Id
	@GeneratedValue
	private Long idCategorie;
	private String libelleCategorie;
	private String iconCategorie;
	@OneToMany(mappedBy="categorie")
	private Set<Service> services = new HashSet<>();
	
	public Long getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(Long idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getLibelleCategorie() {
		return libelleCategorie;
	}
	public void setLibelleCategorie(String libelleCategorie) {
		this.libelleCategorie = libelleCategorie;
	}
	public String getIconCategorie() {
		return iconCategorie;
	}
	public void setIconCategorie(String iconCategorie) {
		this.iconCategorie = iconCategorie;
	}
	public Set<Service> getServices() {
		return services;
	}
	public void setServices(Set<Service> services) {
		this.services = services;
	}
	
	public void addServices(Service services) {
		this.services.add(services);
	}
	
	public Categorie(long idCategorie, String libelleCategorie, String iconCategorie) {
		super();
		this.idCategorie = idCategorie;
		this.libelleCategorie = libelleCategorie;
		this.iconCategorie = iconCategorie;
	}
	public Categorie(String libelleCategorie, String iconCategorie) {
		super();
		this.libelleCategorie = libelleCategorie;
		this.iconCategorie = iconCategorie;
	}
	public Categorie() {
		super();
	}
	
	
}
