package com.ensa.pfa.entities;




import javax.persistence.Entity;

import javax.persistence.Id;


@Entity
public class Details {
			
	
	@Id
	private Details_PK pk;
	private int niveau;
	
	
	public Details() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Details(Offreur offreur, Service service,int niveau)
	{this.pk = new Details_PK(offreur,service);
	this.niveau = niveau;}
	
	public Details_PK getPk()
	{return pk;}
	
	public void setPk(Details_PK pk)
	{this.pk = pk;}
	
	public Offreur getOffreur()
	{return this.pk.getOffreur();}
	
	public void setOffreur(Offreur offreur) 
	{this.pk.setOffreur(offreur);}
	
	public Service getService()
	{return this.pk.getService();}
	
	public void setService(Service service)
	{this.pk.setService(service);}
				
	public int getNiveau() 
	{return niveau;	}
	
	public void setNiveau(int niveau)
	{this.niveau = niveau;}

}
