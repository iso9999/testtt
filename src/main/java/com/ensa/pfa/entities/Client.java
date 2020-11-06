
package com.ensa.pfa.entities;

import java.io.Serializable;
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

import com.ensa.pfa.notification.Notification;
import com.ensa.pfa.entitiesMessaging.Messaging;




@Entity
public class Client implements Serializable {
	@Id
	@GeneratedValue
	private Long idClient;
	private String nomClient;
	private String prenomClient;
	private String titreClient;
	private String adresseClient;
	private boolean banClient;
	private boolean verifieClient;
	private String villeClient;
	private String summary;
	
	
	
	/*@ManyToOne
	@JoinColumn(name="idVille", nullable=true)
	private Ville villeClient;*/
	@OneToMany(mappedBy = "client")
	private Set<Poste> postes;
	@OneToMany(mappedBy = "pk.client")
	private Set<Avis> avis;
	
	@OneToMany(mappedBy="pk.client")
	private Set<Signale> signales =new HashSet<>();
	
	@OneToOne(mappedBy="client", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Users users;
	
	@OneToOne
	@JoinColumn(name="idImage")
	private Image image;
	
	
	@OneToMany(mappedBy="pk.client")
	Set<Messaging> Messagings = new HashSet<>();
	
	
	@OneToMany(mappedBy="client")
	private Set<Notification> notifications = new HashSet<>();
	
	
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	
	public Long getIdClient() {
		return idClient;
	}
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getPrenomClient() {
		return prenomClient;
	}
	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}
	public String getTitreClient() {
		return titreClient;
	}
	public void setTitreClient(String titreClient) {
		this.titreClient = titreClient;
	}
	public String getAdresseClient() {
		return adresseClient;
	}
	public void setAdresseClient(String adresseClient) {
		this.adresseClient = adresseClient;
	}
	public boolean isBanClient() {
		return banClient;
	}
	public void setBanClient(boolean banClient) {
		this.banClient = banClient;
	}
	public boolean isVerifieClient() {
		return verifieClient;
	}
	public void setVerifieClient(boolean verifieClient) {
		this.verifieClient = verifieClient;
	}
	
	public Set<Poste> getPostes() {
		return postes;
	}
	public void setPostes(Set<Poste> postes) {
		this.postes = postes;
	}
	public Set<Avis> getAvis() {
		return avis;
	}
	public void setAvis(Set<Avis> avis) {
		this.avis = avis;
	}
	public Client(String nomClient, String prenomClient, String titreClient, String adresseClient,
			boolean banClient, boolean verifieClient, String villeC, String summary) {
		super();
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.titreClient = titreClient;
		this.adresseClient = adresseClient;
		this.banClient = banClient;
		this.verifieClient = verifieClient;
		this.villeClient = villeC;
		this.summary = summary;
	}
	
	public Client(Long idClient, String nomClient, String prenomClient, String titreClient, String adresseClient,
			boolean banClient, boolean verifieClient, String villeC, String summary) {
		super();
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.titreClient = titreClient;
		this.adresseClient = adresseClient;
		this.banClient = banClient;
		this.verifieClient = verifieClient;
		this.villeClient = villeC;
		this.summary = summary;
	}
	public Client() {
		super();
	}
	public Set<Signale> getSignales() {
		return signales;
	}
	public void setSignales(Set<Signale> signales) {
		this.signales = signales;
	}
	
	public String getVilleClient() {
		return villeClient;
	}
	public void setVilleClient(String villeClient) {
		this.villeClient = villeClient;
	}
	
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
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
