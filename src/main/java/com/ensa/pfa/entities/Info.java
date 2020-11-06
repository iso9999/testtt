package com.ensa.pfa.entities;

public class Info {
	
	
	private String role;
	private String username;
	private String email;
	private String first;
	private String last;
	private String city;
	private String address;
	private String password;
	private String title;
	private String summary;
	
	
	public Info() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Info(String role, String username, String email, String first, String last, String city, String address,
			String password, String title, String summary) {
		super();
		this.role = role;
		this.username = username;
		this.email = email;
		this.first = first;
		this.last = last;
		this.city = city;
		this.address = address;
		this.password = password;
		this.title = title;
		this.summary = summary;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirst() {
		return first;
	}


	public void setFirst(String first) {
		this.first = first;
	}


	public String getLast() {
		return last;
	}


	public void setLast(String last) {
		this.last = last;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
	
}
