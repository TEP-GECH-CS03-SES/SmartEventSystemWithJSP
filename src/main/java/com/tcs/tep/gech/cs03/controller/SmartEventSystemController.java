package com.tcs.tep.gech.cs03.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;

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

	@GetMapping("/AdminHome")
	public String adminHome(Model model) {
		EventBean eb = new EventBean();
		model.addAttribute("event", eb);
		return "AdminHome";
	}

	@GetMapping("/forgot")
	public String forgot(Model model) {
		ForgottenBean fb = new ForgottenBean();
		model.addAttribute("forgot", fb);
		return "forgot";
	}

	@PostMapping("/varify")
	public String loginVarify(@ModelAttribute("login") LoginBean login, Model model, HttpServletRequest request) {
		String username = login.getUserName();
		String password = login.getPassword();
		EventBean eb = new EventBean();
		model.addAttribute("event", eb);
//		System.out.println(username +"   "+password);
		ss.loginCheck(username, password);
		boolean validUser = login.isValid();
		boolean Admin = login.isAdmin();
		String role = login.getRole();
		System.out.println(validUser + "        " + Admin);
		HttpSession session = request.getSession();
		if (validUser == true && Admin == true) {
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
		// invalidate the session , this will clear the data from configured database
		// (Mysql/redis/hazelcast)
		request.getSession().invalidate();
		return "redirect: ";
	}

	@PostMapping("/reset")
	public String resetPassword(@ModelAttribute("forgot") ForgottenBean fb, HttpServletRequest request) {
		String username = fb.getUserName();
		String password = fb.getPassword();
		String confirmpassword = fb.getConfirmPassword();
		String email = fb.getEmail();
//		System.out.println(username +"   "+password+"    "+confirmpassword+"     "+email);
		if (password.equals(confirmpassword)) {
			ss.updatePassword(username, password, email);
		} else {
			return "forgot";
		}
		boolean confirm = fb.getConfirm();
		if (confirm == true) {
			return "redirect: ";
		} else {
			return "forgot";
		}
	}

	@PostMapping("/createEvent")
	public String createEvent(@ModelAttribute("event") EventBean eb, Model model, HttpServletRequest request) {
		model.addAttribute("event", eb);
		/*
		 * String event_name = eb.getEvent_name(); String event_type =
		 * eb.getEvent_type(); Date event_start_date = eb.getEvent_start_date(); Date
		 * event_end_date = eb.getEvent_end_date(); String event_start_time =
		 * eb.getEvent_start_time(); String event_end_time = eb.getEvent_end_time(); int
		 * event_status = eb.getEvent_status(); int participents_count =
		 * eb.getParticipents_count(); // System.out.println(eb);
		 * System.out.println(event_name + " " + event_type + "   " + event_start_date +
		 * "   " + event_end_date + "  " + event_start_time + "  " + event_end_time +
		 * " " + event_status + " " + participents_count);
		 */
		ss.createEvent(eb);
		return "AdminHome";
	}

}
