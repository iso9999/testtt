package com.ensa.pfa.notification;

public class NotifResult {
	private Long idNotif;
	private String titre;
	private Boolean etat;
	private Long idClient;
	private String urlNotif;
	
	
	public NotifResult() {
		super();
		// TODO Auto-generated constructor stub
	}


	public NotifResult(Long idNotif, String titre, Boolean etat, Long idClient,String urlNotif) {
		super();
		this.idNotif = idNotif;
		this.titre = titre;
		this.etat = etat;
		this.idClient = idClient;
		this.urlNotif = urlNotif;
	}


	public Long getIdNotif() {
		return idNotif;
	}


	public void setIdNotif(Long idNotif) {
		this.idNotif = idNotif;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public Boolean getEtat() {
		return etat;
	}


	public void setEtat(Boolean etat) {
		this.etat = etat;
	}


	public Long getIdClient() {
		return idClient;
	}


	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}


	public String getUrlNotif() {
		return urlNotif;
	}


	public void setUrlNotif(String urlNotif) {
		this.urlNotif = urlNotif;
	}
	
	
	
	
}
