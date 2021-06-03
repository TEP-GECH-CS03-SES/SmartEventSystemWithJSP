package com.tcs.tep.gech.cs03.bean;

public class AddUserBean {

	private String UserName;
	private String Email;
	private String phone;
	private String role;
	private static Boolean confirm;
	
	public AddUserBean() {
		super();
	}
	public AddUserBean(String userName, String email, String phone, String role) {
		super();
		UserName = userName;
		Email = email;
		this.phone = phone;
		this.role = role;
	}
	@Override
	public String toString() {
		return "AddUserBean [UserName=" + UserName + ", Email=" + Email + ", phone=" + phone + ", role=" + role + "]";
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public static Boolean getConfirm() {
		return confirm;
	}
	public static void setConfirm(Boolean confirm) {
		AddUserBean.confirm = confirm;
	}
	
}
