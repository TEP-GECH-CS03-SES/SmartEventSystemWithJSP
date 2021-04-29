package com.tcs.tep.gech.cs03.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.tep.gech.cs03.bean.EventBean;
import com.tcs.tep.gech.cs03.bean.ParticipantBean;

public interface SmartEventSystemService{

	public void loginCheck(String username,String password);
	public void  updatePassword(String username,String password,String email);
	public void createEvent(EventBean eb);
	public ArrayList<EventBean> getAllEventDetail();
	public EventBean getEventDetail(String eventName);
	public void registerPart(ParticipantBean pb) throws ClassNotFoundException, UnsupportedEncodingException, SQLException;
}
