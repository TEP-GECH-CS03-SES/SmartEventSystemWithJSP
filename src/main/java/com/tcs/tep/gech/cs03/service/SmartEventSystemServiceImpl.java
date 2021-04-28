package com.tcs.tep.gech.cs03.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.tep.gech.cs03.bean.EventBean;
import com.tcs.tep.gech.cs03.dao.SmartEventSystemDAOImpl;

@Service
public class SmartEventSystemServiceImpl implements SmartEventSystemService {

	@Autowired
	private SmartEventSystemDAOImpl sdao;

	public void loginCheck(String username, String password) {
//	System.out.println(username+"   "+password);
	sdao.loginCheck(username,password);
	}

	public void updatePassword(String username, String password, String email) {
	sdao.updatePassword(username,password,email);
	}

	public void createEvent(EventBean eb) {
		sdao.createEvent(eb);
	}

	public ArrayList<EventBean> getAllEventDetail() {
		return sdao.getAllEventDetail();
	}

	public EventBean getEventDetail(String eventName) {
		return sdao.getEventDetail(eventName);
	}
}
