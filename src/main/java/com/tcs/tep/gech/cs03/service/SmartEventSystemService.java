package com.tcs.tep.gech.cs03.service;

import com.tcs.tep.gech.cs03.bean.EventBean;

public interface SmartEventSystemService{

	public void loginCheck(String username,String password);
	public void  updatePassword(String username,String password,String email);
	public void createEvent(EventBean eb);
}
