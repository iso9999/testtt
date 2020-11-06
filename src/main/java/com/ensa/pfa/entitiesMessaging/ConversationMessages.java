package com.ensa.pfa.entitiesMessaging;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class ConversationMessages implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long idMessage;
	private String msg;
	LocalDateTime dateMsg;
	private String senderUsername;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="client"),
		@JoinColumn(name="offreur")
	})
	private Messaging messaging;

	public ConversationMessages() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConversationMessages(String msg, LocalDateTime dateMsg,Messaging messaging, String senderUsername) {
		super();
		this.msg = msg;
		this.dateMsg = dateMsg;
		this.messaging = messaging;
		this.senderUsername = senderUsername;
	}

	public Long getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Messaging getMessaging() {
		return messaging;
	}

	public void setMessaging(Messaging messaging) {
		this.messaging = messaging;
	}

	public LocalDateTime getDateMsg() {
		return dateMsg;
	}

	public void setDateMsg(LocalDateTime dateMsg) {
		this.dateMsg = dateMsg;
	}

	public String getSenderUsername() {
		return senderUsername;
	}

	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}

	
	
	
}
