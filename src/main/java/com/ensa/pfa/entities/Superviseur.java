package com.ensa.pfa.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import javax.persistence.OneToMany;

@Entity
public class Superviseur {
	
	public Superviseur() {}
	
	public Superviseur(String nomSuperviseur, String prenomSuperviseur, String adresseSuperviseur)
	{	super();
		this.nomSuperviseur = nomSuperviseur;
		this.prenomSuperviseur = prenomSuperviseur;
		this.adresseSuperviseur = adresseSuperviseur;
	}
	
	

	@Id
	@GeneratedValue
	private  long idSuperviseur;
	private String nomSuperviseur;
	private String prenomSuperviseur;
	private String adresseSuperviseur;
	
	
	@OneToMany(mappedBy="superviseur")
	private Set<Signale> Signales ;
	
	

	public String getNomSuperviseur() 
	{return nomSuperviseur;}

	public void setNomSuperviseur(String nomSuperviseur)
	{this.nomSuperviseur = nomSuperviseur;}

	public String getPrenomSuperviseur() 
	{return prenomSuperviseur;}

	public void setPrenomSuperviseur(String prenomSuperviseur) 
	{this.prenomSuperviseur = prenomSuperviseur;}

	public String getAdresseSuperviseur() 
	{return adresseSuperviseur;}

	public void setAdresseSuperviseur(String adresseSuperviseur) 
	{this.adresseSuperviseur = adresseSuperviseur;}
	
}