package com.ensa.pfa.entitiesMessaging;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ensa.pfa.entities.Client;
import com.ensa.pfa.entities.Offreur;

@Embeddable
public class Messaging_PK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idClient")
	private Client client;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idOffreur")//toClient
	private Offreur offreur;

	public Messaging_PK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Messaging_PK(Client client, Offreur offreur) {
		super();
		this.client = client;
		this.offreur = offreur;
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
