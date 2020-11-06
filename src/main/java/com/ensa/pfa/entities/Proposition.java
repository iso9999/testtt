package com.ensa.pfa.entities;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Proposition {
			
	
	@Id
	private Proposition_PK pk;
	private int dureeProposition;
	private String commentaireProposition;
	private Long prix;
	
	
	
	public Proposition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Proposition(Poste poste, Offreur offreur,int dureeProposition, String commentaireProposition,Long prix) {
		this.pk = new Proposition_PK(poste,offreur);
		this.commentaireProposition= commentaireProposition;
		this.dureeProposition=dureeProposition;
		this.prix=prix;
	}
	   
	public Proposition_PK getPk()
	{return pk;}
	
	public void setPk(Proposition_PK pk)
	{this.pk = pk;}
	
	public Poste getPoste()
	{return this.pk.getPoste();}
	
	public void setPoste(Poste poste) 
	{this.pk.setPoste(poste);}
	
	public Offreur getOffreur()
	{return this.pk.getOffreur();}
	
	public void setOffreur(Offreur offreur)
	{this.pk.setOffreur(offreur);}
		
	public String getCommentaireProposition() 
	{return commentaireProposition;}
	
	public void setCommentaireProposition(String commentaireProposition) 
	{this.commentaireProposition = commentaireProposition;}
	
	public int getDureePropositon()
	{return dureeProposition;}
	
	public void setDureePropositon(int dureePropositon) 
	{this.dureeProposition = dureePropositon;}

	public int getDureeProposition() {
		return dureeProposition;
	}

	public void setDureeProposition(int dureeProposition) {
		this.dureeProposition = dureeProposition;
	}

	public Long getPrix() {
		return prix;
	}

	public void setPrix(Long prix) {
		this.prix = prix;
	}
	
	

}
