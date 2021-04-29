package com.tcs.tep.gech.cs03.bean;

public class ForgottenBean {
	
	private String UserName;
	private String Email;
	private String Password;
	private String ConfirmPassword;
	private static Boolean confirm;
	
	public ForgottenBean() {
		super();
	}
	public ForgottenBean(String userName, String email, String password, String confirmPassword) {
		super();
		UserName = userName;
		Email = email;
		Password = password;
		ConfirmPassword = confirmPassword;
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
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getConfirmPassword() {
		return ConfirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		ConfirmPassword = confirmPassword;
	}
	public Boolean getConfirm() {
		return confirm;
	}
	public void setConfirm(Boolean confirm) {
		ForgottenBean.confirm = confirm;
	}

}
