package com.tcs.tep.gech.cs03.bean;

public class ScanBean {
	private String data;
	private String firstname;
	private String lastname;
	private String eventname;
	private String phone;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public ScanBean(String data, String firstname, String lastname, String eventname, String phone) {
		super();
		this.data = data;
		this.firstname = firstname;
		this.lastname = lastname;
		this.eventname = eventname;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "ScanBean [data=" + data + ", firstname=" + firstname + ", lastname=" + lastname + ", eventname="
				+ eventname + ", phone=" + phone + "]";
	}

	public ScanBean() {
		super();
	}
	

}
