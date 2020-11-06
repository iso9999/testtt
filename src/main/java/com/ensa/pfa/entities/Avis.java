package com.ensa.pfa.entities;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Avis {
			
	
	@Id
	private Avis_PK pk;
	private int noteAvis;
	private String commentaireAvis;
	
	
	
	public Avis() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Avis(Client client, Offreur offreur,int noteAvis, String commentaireAvis)
	{this.pk = new Avis_PK(client,offreur);
	this.commentaireAvis= commentaireAvis;
	this.noteAvis=noteAvis;}
	
	public Avis_PK getPk()
	{return pk;}
	
	public void setPk(Avis_PK pk)
	{this.pk = pk;}
	
	public Client getClient()
	{return this.pk.getClient();}
	
	public void setClient(Client client) 
	{this.pk.setClient(client);}
	
	public Offreur getOffreur()
	{return this.pk.getOffreur();}
	
	public void setOffreur(Offreur offreur)
	{this.pk.setOffreur(offreur);}
	
	public int getNoteAvis() 
	{return noteAvis;}
	
	public void setNoteAvis(int noteAvis) 
	{this.noteAvis = noteAvis;}
	
	public String getCommentaireAvis()
	{return commentaireAvis;}
	
	public void setCommentaireAvis(String commentaireAvis)
	{this.commentaireAvis = commentaireAvis;}
	

}
