package com.ensa.pfa.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Image {

	@Id
	@GeneratedValue
	private long idImage;
	private String urlImage;
	
	@OneToOne(mappedBy="image")
	private Client client;
	
	@OneToOne(mappedBy="image")
	private Offreur offreur;
	
	
	@ManyToOne
	@JoinColumn(name="idPoste")
	private Poste poste;
	public Image(long idImage, String urlImage, Poste poste) {
		super();
		this.idImage = idImage;
		this.urlImage = urlImage;
		this.poste = poste;
	}
	
	public Image(String urlImage, Poste poste) {
		super();
		this.urlImage = urlImage;
		this.poste = poste;
	}
	
	public long getIdImage() {
		return idImage;
	}
	public void setIdImage(long idImage) {
		this.idImage = idImage;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	public Poste getPoste() {
		return poste;
	}
	public void setPoste(Poste poste) {
		this.poste = poste;
	}
	public Image() {
		super();
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Offreur getOffreur() {
		return offreur;
	}
	public void setOffreur(Offreur offreur) {
		this.offreur = offreur;
	}
	
}
