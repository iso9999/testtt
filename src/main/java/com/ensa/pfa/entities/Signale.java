package com.ensa.pfa.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Signale {
	
	public Signale() {}
	
	@Id
	private Signale_PK pk;
	private boolean traitee;
	private int typeSignale;
	
	
	@ManyToOne
	@JoinColumn(name="idSuperviseur")
	private Superviseur superviseur;
	
	@ManyToMany
	private Set<Raison> raisons;
	
	
	

	public Signale(Offreur offreur, Client client, int typeSignale) {
		super();
		this.pk = new Signale_PK(client, offreur);
		this.traitee = false;
		this.typeSignale = typeSignale;
	}
	
	
	public Client getClient() {
		return this.pk.getClient();
	}

	public void setClient(Client client) {
		this.pk.setClient(client);
	}

	public Offreur getOffreur() {
		return this.pk.getOffreur();
	}

	public void setOffreur(Offreur offreur) {
		this.pk.setOffreur(offreur);
	}

	public Signale_PK getPk() {
		return pk;
	}

	public void setPk(Signale_PK pk) {
		this.pk = pk;
	}

	public boolean isTraitee() {
		return traitee;
	}

	public void setTraitee(boolean traitee) {
		this.traitee = traitee;
	}

	public int getTypeSignale() {
		return typeSignale;
	}

	public void setTypeSignale(int typeSignale) {
		this.typeSignale = typeSignale;
	}

	public Superviseur getSuperviseur() {
		return superviseur;
	}

	public void setSuperviseur(Superviseur superviseur) {
		this.superviseur = superviseur;
	}

	public Set<Raison> getRaisons() {
		return raisons;
	}

	public void setRaisons(Set<Raison> raisons) {
		this.raisons = raisons;
	}
	
	
	
	
}