package com.ensa.pfa.entities;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Raison {
	
	
	@Id
	@GeneratedValue
	private  long idRaison;
	private String  libelleRaison;
	
	
	public Raison() {}
	
	public Raison(String libelleRaison) 
	{	super();
		this.libelleRaison = libelleRaison;
	}


	public String getLibelleRaison()
	{return libelleRaison;}

	public void setLibelleRaison(String libelleRaison)
	{this.libelleRaison = libelleRaison;}

	public long getIdRaison() {
		return idRaison;
	}

	public void setIdRaison(long idRaison) {
		this.idRaison = idRaison;
	}
	
	
	
	
	
}