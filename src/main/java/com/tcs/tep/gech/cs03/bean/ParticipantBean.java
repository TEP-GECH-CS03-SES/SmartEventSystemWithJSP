package com.tcs.tep.gech.cs03.bean;

public class ParticipantBean {
	
	private  String first_name ;
	private String last_name; 
	private String event_name;
	private String email;
	private String phone;
	
	private boolean registered;
	@Override
	public String toString() {
		return "ParticipantBean [first_name=" + first_name + ", last_name=" + last_name + ", event_name=" + event_name
				+ ", email=" + email + ", phone=" + phone + "]";
	}
	public ParticipantBean(String first_name, String last_name, String event_name, String email, String phone) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.event_name = event_name;
		this.email = email;
		this.phone = phone;
	}
	public ParticipantBean() {
		super();
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isRegistered() {
		return registered;
	}
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
}
