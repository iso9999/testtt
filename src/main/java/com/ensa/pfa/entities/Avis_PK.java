package com.ensa.pfa.entities;
import java.io.Serializable;

import javax.persistence.Embeddable;

import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;



@Embeddable
public class Avis_PK implements Serializable{
	private static final long serialVersionUID = 1L; 
	
	
	@ManyToOne
	@JoinColumn(name="idClient")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="idOffreur")
	private Offreur offreur;
	
	
	public Avis_PK() {}
	
	public Avis_PK(Client client, Offreur offreur)
	{this.client = client;
	this.offreur = offreur;}
	
	public Offreur getOffreur()
	{return offreur;}
	
	public void setOffreur(Offreur offreur)
	{this.offreur = offreur;}
	
	public Client getClient() 
	{return client;}
	
	public void setClient(Client client) 
	{this.client = client;}
	

}
