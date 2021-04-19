package com.tcs.tep.gech.cs03.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import com.tcs.tep.gech.cs03.bean.EventBean;
import com.tcs.tep.gech.cs03.bean.ForgottenBean;
import com.tcs.tep.gech.cs03.bean.LoginBean;
import com.tcs.tep.gech.cs03.service.SmartEventSystemServiceImpl;

@Controller
public class SmartEventSystemController {

	@Autowired
	 private SmartEventSystemServiceImpl ss; 
	
	
	@GetMapping("/")
	public String login(Model model) {
		LoginBean lb = new LoginBean();
		model.addAttribute("login", lb);
		return "login";
	}

	@GetMapping("/forgot")
	public String forgot(Model model) {
		ForgottenBean fb = new ForgottenBean();
		model.addAttribute("forgot", fb);
		return "forgot";
	}

	@PostMapping("/varify")
	public String loginVarify(@ModelAttribute("login") LoginBean login,@ModelAttribute("event") EventBean eb, HttpServletRequest request) {
		String username = login.getUserName();
		String password = login.getPassword();
//		System.out.println(username +"   "+password);
		ss.loginCheck(username,password);
		boolean validUser = login.isValid();
		boolean Admin = login.isAdmin();
		String role = login.getRole();
		System.out.println(validUser+"        "+Admin);
		HttpSession session = request.getSession();
		if (validUser ==  true && Admin == true) {
			session.setAttribute("adminUser", role);
			return "AdminHome";
		} else if (validUser == true) {
			session.setAttribute("normUser", role);
			return "UserHome";
		} else {
			return "login";
		}
	}
	@RequestMapping("/logout")
    public String destroySession(HttpServletRequest request) {
        //invalidate the session , this will clear the data from configured database (Mysql/redis/hazelcast)
        request.getSession().invalidate();
        return "redirect: ";
    } 
	@PostMapping("/reset")
	public String resetPassword(@ModelAttribute("forgot") ForgottenBean fb,HttpServletRequest request) {
		String username = fb.getUserName();
		String password = fb.getPassword();
		String confirmpassword = fb.getConfirmPassword();
		String email = fb.getEmail();
//		System.out.println(username +"   "+password+"    "+confirmpassword+"     "+email);
		if(password.equals(confirmpassword)) {
			ss.updatePassword(username,password,email);
		}else {
			return "forgot";	
		}
		boolean confirm = fb.getConfirm();
		if(confirm == true) {
			 return "redirect: ";
		}else {
		return "forgot";
		}
	}
}
