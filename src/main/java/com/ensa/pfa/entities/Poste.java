package com.ensa.pfa.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Poste {
	@Id
	@GeneratedValue
	private Long idPoste;
	private String titrePoste;
	private String descreptionPoste;
	private LocalDate datePoste;
	private int DureeMaxPoste;
	private int DureeMinPoste;
	private double prixMinPoste;
	private double prixMaxPoste;
	private Integer etatProjet;
	private boolean payementEnLigne;
	private String villePost;
	private String typePeriod;
	
	@ManyToOne
	@JoinColumn(name="idOffreur")
	private Offreur offreur;
	/*@ManyToOne
	@JoinColumn(name="idVille", nullable=true)
	private Ville villePost;*/
	@OneToMany(mappedBy="poste")
	private Set<Image> images;
	@ManyToOne
	@JoinColumn(name="idClient")
	private Client client;
	@ManyToMany
	private Set<Categorie> categories = new HashSet<>();
	@ManyToMany
	@JoinTable(name="poste_servicess",joinColumns=@JoinColumn(name="idPoste"),inverseJoinColumns=@JoinColumn(name="idService"))
	private Set<Service> services = new HashSet<>();
	@OneToMany(mappedBy= "pk.poste")
	private Set<Proposition> proposition = new HashSet<>();
	public Poste() {
		super();
	}
	public Poste(long idPoste, String titrePoste, String descreptionPoste, LocalDate datePoste, int dureeMaxPoste,
			int dureeMinPoste, double prixMinPoste, double prixMaxPoste, boolean payementEnLigne, String ville,
			Set<Image> images, Client client, Set<Service> services) {
		super();
		this.idPoste = idPoste;
		this.titrePoste = titrePoste;
		this.descreptionPoste = descreptionPoste;
		this.datePoste = datePoste;
		DureeMaxPoste = dureeMaxPoste;
		DureeMinPoste = dureeMinPoste;
		this.prixMinPoste = prixMinPoste;
		this.prixMaxPoste = prixMaxPoste;
		this.payementEnLigne = payementEnLigne;
		this.villePost = ville;
		this.images = images;
		this.client = client;
		this.services = services;
		this.etatProjet = 0;

	}
	public Poste(String titrePoste, String descreptionPoste, LocalDate datePoste, int dureeMaxPoste,
			int dureeMinPoste, double prixMinPoste, double prixMaxPoste, boolean payementEnLigne, String ville,
			Set<Image> images, Client client, Set<Service> services) {
		super();
		this.titrePoste = titrePoste;
		this.descreptionPoste = descreptionPoste;
		this.datePoste = datePoste;
		DureeMaxPoste = dureeMaxPoste;
		DureeMinPoste = dureeMinPoste;
		this.prixMinPoste = prixMinPoste;
		this.prixMaxPoste = prixMaxPoste;
		this.payementEnLigne = payementEnLigne;
		this.villePost = ville;
		this.images = images;
		this.client = client;
		this.services = services;
		this.etatProjet = 0;

	}
	
	public Poste(String titrePoste, String descreptionPoste, LocalDate datePoste, int dureeMaxPoste,
			int dureeMinPoste, double prixMinPoste, double prixMaxPoste, boolean payementEnLigne, String ville, Client client, String typePeriod) {
		super();
		this.titrePoste = titrePoste;
		this.descreptionPoste = descreptionPoste;
		this.datePoste = datePoste;
		DureeMaxPoste = dureeMaxPoste;
		DureeMinPoste = dureeMinPoste;
		this.prixMinPoste = prixMinPoste;
		this.prixMaxPoste = prixMaxPoste;
		this.payementEnLigne = payementEnLigne;
		this.villePost = ville;
		this.client = client;
		this.etatProjet = 0;
		this.typePeriod = typePeriod;

	}
	
	public Long getIdPoste() {
		return idPoste;
	}
	public void setIdPoste(Long idPoste) {
		this.idPoste = idPoste;
	}
	public String getTitrePoste() {
		return titrePoste;
	}
	public void setTitrePoste(String titrePoste) {
		this.titrePoste = titrePoste;
	}
	public String getDescreptionPoste() {
		return descreptionPoste;
	}
	public void setDescreptionPoste(String descreptionPoste) {
		this.descreptionPoste = descreptionPoste;
	}
	public LocalDate getDatePoste() {
		return datePoste;
	}
	public void setDatePoste(LocalDate datePoste) {
		this.datePoste = datePoste;
	}
	public int getDureeMaxPoste() {
		return DureeMaxPoste;
	}
	public void setDureeMaxPoste(int dureeMaxPoste) {
		DureeMaxPoste = dureeMaxPoste;
	}
	public int getDureeMinPoste() {
		return DureeMinPoste;
	}
	public void setDureeMinPoste(int dureeMinPoste) {
		DureeMinPoste = dureeMinPoste;
	}
	public double getPrixMinPoste() {
		return prixMinPoste;
	}
	public void setPrixMinPoste(double prixMinPoste) {
		this.prixMinPoste = prixMinPoste;
	}
	public double getPrixMaxPoste() {
		return prixMaxPoste;
	}
	public void setPrixMaxPoste(double prixMaxPoste) {
		this.prixMaxPoste = prixMaxPoste;
	}
	public boolean isPayementEnLigne() {
		return payementEnLigne;
	}
	public void setPayementEnLigne(boolean payementEnLigne) {
		this.payementEnLigne = payementEnLigne;
	}
	public String getVillePost() {
		return villePost;
	}
	public void setVillePost(String ville) {
		this.villePost = ville;
	}
	public Set<Image> getImages() {
		return images;
	}
	public void setImages(Set<Image> images) {
		this.images = images;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Set<Categorie> getCategories() {
		return categories;
	}
	public void setCategories(Set<Categorie> categories) {
		this.categories = categories;
	}
	public void addCategorie(Categorie categories) {
		this.categories.add(categories);
	}
	public Set<Service> getServices() {
		return services;
	}
	public void setServices(Set<Service> services) {
		this.services = services;
	}
	public void addService(Service services) {
		this.services.add(services);
	}
	public Set<Proposition> getProposition() {
		return proposition;
	}
	public void setProposition(Set<Proposition> proposition) {
		this.proposition = proposition;
	}
	public void addProposition(Proposition proposition) {
		this.proposition.add(proposition);
	}
	
	public Integer getEtatProjet() {
		return etatProjet;
	}
	public void setEtatProjet(Integer etatProjet) {
		this.etatProjet = etatProjet;
	}
	public Offreur getOffreur() {
		return offreur;
	}
	public void setOffreur(Offreur offreur) {
		this.offreur = offreur;
	}
	
	
	public String getTypePeriod() {
		return typePeriod;
	}
	public void setTypePeriod(String typePeriod) {
		this.typePeriod = typePeriod;
	}
	
	
	
	}
