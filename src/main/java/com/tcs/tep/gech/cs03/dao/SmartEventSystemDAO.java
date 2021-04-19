package com.tcs.tep.gech.cs03.dao;

public interface SmartEventSystemDAO {
	public void LoginCheck(String username, String Password);
	public void  updatePassword(String username,String password,String email);
}
