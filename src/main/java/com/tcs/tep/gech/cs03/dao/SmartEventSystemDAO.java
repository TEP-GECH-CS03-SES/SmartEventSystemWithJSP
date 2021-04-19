package com.tcs.tep.gech.cs03.dao;

public interface SmartEventSystemDAO {
	public void loginCheck(String username, String Password);
	public void  updatePassword(String username,String password,String email);
}
