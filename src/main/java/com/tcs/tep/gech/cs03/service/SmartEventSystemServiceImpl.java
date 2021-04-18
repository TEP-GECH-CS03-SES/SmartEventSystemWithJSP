package com.tcs.tep.gech.cs03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.tep.gech.cs03.dao.SmartEventSystemDAOImpl;

@Service
public class SmartEventSystemServiceImpl implements SmartEventSystemService {

	@Autowired
	private SmartEventSystemDAOImpl sdao;

	public void LoginCheck(String username, String password) {
//	System.out.println(username+"   "+password);
	sdao.LoginCheck(username,password);
	}

}
