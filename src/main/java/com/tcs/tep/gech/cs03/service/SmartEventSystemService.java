package com.tcs.tep.gech.cs03.service;


public interface SmartEventSystemService{

	public void loginCheck(String username,String password);
	public void  updatePassword(String username,String password,String email);
}
