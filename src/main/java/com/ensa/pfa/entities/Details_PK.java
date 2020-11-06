package com.ensa.pfa.entities;
import java.io.Serializable;

import javax.persistence.Embeddable;

import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;



@Embeddable
public class Details_PK implements Serializable{
	private static final long serialVersionUID = 1L; 
	
	
	@ManyToOne
	@JoinColumn(name="idOffreur")
	private Offreur offreur;
	
	@ManyToOne
	@JoinColumn(name="idService")
	private Service service;
	
	
	public Details_PK() {}
	
	public Details_PK(Offreur offreur, Service service)
	{this.offreur = offreur;
	this.service = service;}
	
	public Offreur getOffreur()
	{return offreur;}
	
	public void setOffreur(Offreur offreur)
	{this.offreur = offreur;}
	
	public Service getService() 
	{return service;}
	
	public void setService(Service service) 
	{this.service = service;}
	

}
