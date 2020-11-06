package com.ensa.pfa.entitiesMessaging;

public class Message {
	
	
	private String to;
	private String msg;
	private String from;
	
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Message(String to, String msg, String from) {
		super();
		this.to = to;
		this.msg = msg;
		this.from = from;
	}


	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}
	
	
	
	
}
