package com.tcs.tep.gech.cs03.dao;

import com.tcs.tep.gech.cs03.bean.EventBean;
import com.tcs.tep.gech.cs03.bean.ParticipantBean;

public interface SmartEventSystemDAO {
	public void loginCheck(String username, String Password);
	public void  updatePassword(String username,String password,String email);
	public void createEvent(EventBean eb);
	public EventBean getEventDetail(String eventName);
	public void registerPart(ParticipantBean pb);
}
