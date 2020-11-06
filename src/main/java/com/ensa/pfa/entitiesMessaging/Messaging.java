package com.ensa.pfa.entitiesMessaging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ensa.pfa.entities.Client;
import com.ensa.pfa.entities.Offreur;

@Entity
public class Messaging implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Messaging_PK pk;
	
	@OneToMany(mappedBy="messaging")
	private List<ConversationMessages> messages = new ArrayList<>();
	
	
	public Messaging() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Messaging(Client client, Offreur offreur ) {
		super();
		this.pk = new Messaging_PK(client, offreur);

	}


	public Messaging_PK getPk() {
		return pk;
	}


	public void setPk(Messaging_PK pk) {
		this.pk = pk;
	}

	public Client getClient() {
		return this.pk.getClient();
	}

	public void setClient(Client client) {
		this.setClient(client);
	}

	public Offreur getOffreur() {
		return this.pk.getOffreur();
	}

	public void setOffreur(Offreur offreur) {
		this.pk.setOffreur(offreur);
	}

	
	public List<ConversationMessages> getMessages() {
		return messages;
	}


	public void setMessages(List<ConversationMessages> messages) {
		this.messages = messages;
	}
	
	public void addMessage(ConversationMessages message) {
		this.messages.add(message);
	}
	
	
	
	
}
