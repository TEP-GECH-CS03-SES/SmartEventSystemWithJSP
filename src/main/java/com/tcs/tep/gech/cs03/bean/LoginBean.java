package com.tcs.tep.gech.cs03.bean;


public class LoginBean {

	private String UserName;
	private String Password;
	private static String role;
	private static boolean valid;
	private static boolean admin;
	
	
	
	@Override
	public String toString() {
		return "LoginBean [UserName=" + UserName + ", Password=" + Password + ", valid=" + valid + ", admin=" + admin
				+ "]";
	}
	public LoginBean() {
		super();
	}
	public LoginBean(String userName, String password) {
		super();
		UserName = userName;
		Password = password;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		LoginBean.valid = valid;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		LoginBean.admin = admin;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		LoginBean.role = role;
	}
	
}
