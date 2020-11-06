package com.ensa.pfa.entitiesMessaging;

import java.time.LocalDateTime;

public class UserConversations {
	private String username;
	private String name;
	private String lastMessage;
	private String imageProfile;
	private LocalDateTime dateMsg;
	
	
	public UserConversations() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserConversations(String username, String name, String lastMessage, String imageProfile,LocalDateTime dateMsg) {
		super();
		this.username = username;
		this.name = name;
		this.lastMessage = lastMessage;
		this.imageProfile = imageProfile;
		this.dateMsg = dateMsg;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastMessage() {
		return lastMessage;
	}


	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}


	public String getImageProfile() {
		return imageProfile;
	}


	public void setImageProfile(String imageProfile) {
		this.imageProfile = imageProfile;
	}


	public LocalDateTime getDateMsg() {
		return dateMsg;
	}


	public void setDateMsg(LocalDateTime dateMsg) {
		this.dateMsg = dateMsg;
	}


	
	
	
}
