package com.ensa.pfa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ensa.pfa.entitiesMessaging.Messaging;
import com.ensa.pfa.notification.Notification;

@Entity
public class Offreur {
	
	public Offreur() {}
	
	public Offreur(String nomOffreur, String prenomOffreur, String titreOffreur, String adresseOffreur, String summary, String villeOffreur)
	{	super();
		this.nomOffreur = nomOffreur;
		this.prenomOffreur = prenomOffreur;
		this.adresseOffreur = adresseOffreur;
		this.banOffreur = false;
		this.verifieOffreur = true;
		this.typeOffreur = 'f';
		this.summary = summary;
		this.villeOffreur =  villeOffreur;
		this.titreOffreur = titreOffreur;
	}

	@Id
	@GeneratedValue
	private Long idOffreur;
	private String titreOffreur;
	private String nomOffreur;
	private String prenomOffreur;
	private String adresseOffreur;
	private boolean banOffreur;
	private boolean verifieOffreur;
	private char typeOffreur;
	private String summary;
	private String villeOffreur;
	
	
	@OneToMany (mappedBy="pk.offreur")
	private Set<Details> details = new HashSet<>();
	
	@OneToMany (mappedBy="pk.offreur")
	private Set<Avis> avis = new HashSet<>();
	
	@OneToMany(mappedBy="pk.offreur")
	private Set<Signale> signales = new HashSet<>();
	
	@OneToMany(mappedBy = "offreur")
	private Set<Poste> postes;
	
	
	
	@OneToOne
	@JoinColumn(name="idImage")
	private Image image;
	
	@OneToOne(mappedBy="offreur",fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Users users;
	
	
	@OneToMany(mappedBy="pk.offreur")
	Set<Messaging> Messagings = new HashSet<>();
	
	@OneToMany(mappedBy="offreur")
	private Set<Notification> notifications = new HashSet<>();
	
	
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	
	public String getNomOffreur()
	{return nomOffreur;}
	
	public void setNomOffreur(String nomOffreur)
	{this.nomOffreur = nomOffreur;}
	
	public String getPrenomOffreur() 
	{return prenomOffreur;}
	
	public void setPrenomOffreur(String prenomOffreur)
	{this.prenomOffreur = prenomOffreur;}
	
	public String getAdresseOffreur()
	{return adresseOffreur;}
	
	public void setAdresseOffreur(String adresseOffreur)
	{this.adresseOffreur = adresseOffreur;}
	
	public boolean isVerifieOffreur()
	{return verifieOffreur;}
	
	public void setVerifieOffreur(boolean verifieOffreur)
	{this.verifieOffreur = verifieOffreur;}
	
	public char getTypeOffreur() 
	{return typeOffreur;}
	
	public void setTypeOffreur(char typeOffreur)
	{this.typeOffreur = typeOffreur;}
	
	public boolean isBanOffreur()
	{return banOffreur;}
	
	public void setBanOffreur(boolean banOffreur) 
	{this.banOffreur = banOffreur;}	
	
	public Set<Details> getDetails() 
	{return details;}
	
	public void setDetails(Set<Details> details)
	{this.details = details;}
	
	public Set<Avis> getAvis() 
	{return avis;}
	
	public void setAvis(Set<Avis> avis)
	{this.avis = avis;}
	

	public Long getIdOffreur() {
		return idOffreur;
	}

	public void setIdOffreur(Long idOffreur) {
		this.idOffreur = idOffreur;
	}

	public String getTitreOffreur() {
		return titreOffreur;
	}

	public void setTitreOffreur(String titreOffreur) {
		this.titreOffreur = titreOffreur;
	}

	public Set<Signale> getSignales() {
		return signales;
	}

	public void setSignales(Set<Signale> signales) {
		this.signales = signales;
	}
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getVilleOffreur() {
		return villeOffreur;
	}

	public void setVilleOffreur(String villeOffreur) {
		this.villeOffreur = villeOffreur;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Set<Poste> getPostes() {
		return postes;
	}

	public void setPostes(Set<Poste> postes) {
		this.postes = postes;
	}

	public Set<Messaging> getMessagings() {
		return Messagings;
	}

	public void setMessagings(Set<Messaging> messagings) {
		Messagings = messagings;
	}
	
	public Set<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}
	
	public void addNotification(Notification notification) {
		this.notifications.add(notification);
	}
	
}