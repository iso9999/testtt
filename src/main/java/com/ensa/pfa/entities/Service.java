package com.ensa.pfa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Service {
	@Id
	@GeneratedValue
	private Long idService;
	private String libelleService;
	private boolean lastService;
	@ManyToOne
	@JoinColumn(name = "idCategorie")
	private Categorie categorie;
	
	@OneToMany (mappedBy="pk.service")
	private Set<Details> details = new HashSet<>();
	@ManyToMany(mappedBy = "services")
	private Set<Poste> postes = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Service parent;

	@OneToMany(mappedBy="parent", cascade = CascadeType.ALL)
	private Set<Service> children = new HashSet<Service>();

	public Long getIdService() {
		return idService;
	}

	public void setIdService(Long idService) {
		this.idService = idService;
	}

	public String getLibelleService() {
		return libelleService;
	}

	public void setLibelleService(String libelleService) {
		this.libelleService = libelleService;
	}

	public boolean isLastService() {
		return lastService;
	}

	public void setLastService(boolean lastService) {
		this.lastService = lastService;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Set<Details> getDetails() {
		return details;
	}

	public void setDetails(Set<Details> details) {
		this.details = details;
	}

	public Set<Poste> getPostes() {
		return postes;
	}

	public void setPostes(Set<Poste> postes) {
		this.postes = postes;
	}
	
	public void addPoste(Poste poste) {
		this.postes.add(poste);
	}

	public Service getParent() {
		return parent;
	}

	public void setParent(Service parent) {
		this.parent = parent;
	}

	public Set<Service> getChildren() {
		return children;
	}

	public void setChildren(Set<Service> children) {
		this.children = children;
	}

	public Service(Long idService, String libelleService, boolean lastService, Categorie categorie) {
		super();
		this.idService = idService;
		this.libelleService = libelleService;
		this.lastService = lastService;
		this.categorie = categorie;
	}
	
	public Service(String libelleService, boolean lastService, Categorie categorie) {
		super();
		this.libelleService = libelleService;
		this.lastService = lastService;
		this.categorie = categorie;
	}

	public Service() {
		super();
	}
	
	
}
