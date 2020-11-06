package com.ensa.pfa.entities;
import java.io.Serializable;

import javax.persistence.Embeddable;

import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;



@Embeddable
public class Proposition_PK implements Serializable{
	private static final long serialVersionUID = 1L; 
	
	
	@ManyToOne
	@JoinColumn(name="idPoste")
	private Poste poste;
		
	@ManyToOne
	@JoinColumn(name="idOffreur")
	private Offreur offreur;
	
	
	public Proposition_PK() {}
	
	public Proposition_PK(Poste poste, Offreur offreur)
	{this.poste = poste;
	this.offreur = offreur;}
	
	public Offreur getOffreur()
	{return offreur;}
	
	public void setOffreur(Offreur offreur) 
	{this.offreur = offreur;}
	
	public Poste getPoste() 
	{return poste;}
	
	public void setPoste(Poste poste) 
	{this.poste = poste;}
}
