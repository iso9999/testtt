package com.ensa.pfa.notification;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ensa.pfa.entities.Client;
import com.ensa.pfa.entities.Offreur;

@Entity
public class Notification implements Serializable {
	@Id
	@GeneratedValue
	private Long idNotif;
	private String titre;
	private Boolean etat;
	private String urlNotif;
	
	@ManyToOne
	@JoinColumn(name="idClient")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="idOffreur")
	private Offreur offreur ;
	
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Notification(String titre,String urlNotif) {
		super();
		this.titre = titre;
		this.etat = false;
		this.urlNotif = urlNotif;
	}


	public Boolean getEtat() {
		return etat;
	}


	public void setEtat(Boolean etat) {
		this.etat = etat;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Long getIdNotif() {
		return idNotif;
	}


	public void setIdNotif(Long idNotif) {
		this.idNotif = idNotif;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public Offreur getOffreur() {
		return offreur;
	}


	public void setOffreur(Offreur offreur) {
		this.offreur = offreur;
	}


	public String getUrlNotif() {
		return urlNotif;
	}


	public void setUrlNotif(String urlNotif) {
		this.urlNotif = urlNotif;
	}
	
	
	
}
