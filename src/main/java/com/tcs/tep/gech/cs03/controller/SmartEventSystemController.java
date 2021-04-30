package com.tcs.tep.gech.cs03.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tcs.tep.gech.cs03.bean.EventBean;
import com.tcs.tep.gech.cs03.bean.ForgottenBean;
import com.tcs.tep.gech.cs03.bean.LoginBean;
import com.tcs.tep.gech.cs03.bean.ParticipantBean;
import com.tcs.tep.gech.cs03.bean.QrCodeBean;
import com.tcs.tep.gech.cs03.service.SmartEventSystemServiceImpl;

@Controller
public class SmartEventSystemController {

	@Autowired
	private SmartEventSystemServiceImpl ss;

	HttpSession session;

	@GetMapping("/")
	public String login(Model model, HttpServletResponse response) {
		LoginBean lb = new LoginBean();
		model.addAttribute("login", lb);
		return "login";
	}

	@GetMapping("/AdminHome")
	public String adminHome(Model model, ModelMap modelmap) {
		EventBean eb = new EventBean();
		model.addAttribute("event", eb);
		ArrayList<EventBean> allEvent = ss.getAllEventDetail();
		modelmap.addAttribute("allevent", allEvent);
		return "AdminHome";
	}

	@GetMapping("/UserHome")
	public String userHome(Model model) {
		EventBean eb = new EventBean();
		model.addAttribute("event", eb);
		return "UserHome";
	}

	@GetMapping("/partRegi")
	public String participantRegister(Model model, ModelMap modelmap) {
		ParticipantBean pb = new ParticipantBean();
		ArrayList<EventBean> allEvent = ss.getAllEventDetail();
		modelmap.addAttribute("allevent", allEvent);
		model.addAttribute("partreg", pb);
		return "part_regis";
	}

	@GetMapping("/forgot")
	public String forgot(Model model) {
		ForgottenBean fb = new ForgottenBean();
		model.addAttribute("forgot", fb);
		return "forgot";
	}

	@PostMapping("/varify")
	public String loginVarify(@ModelAttribute("login") LoginBean login, Model model, ModelMap modelmap,
			HttpServletRequest request) throws IOException {
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
		session = request.getSession();
		if (validUser == true && Admin == true) {
			session.setAttribute("adminUser", role);
			ArrayList<EventBean> allEvent = ss.getAllEventDetail();
			modelmap.addAttribute("allevent", allEvent);
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
	public String createEvent(@ModelAttribute("event") EventBean eb, Model model, ModelMap modelmap,
			HttpServletRequest request) {
		model.addAttribute("event", eb);
		String event_name = eb.getEvent_name();
		/*
		 * String event_type = eb.getEvent_type(); Date event_start_date =
		 * eb.getEvent_start_date(); Date event_end_date = eb.getEvent_end_date();
		 * String event_start_time = eb.getEvent_start_time(); String event_end_time =
		 * eb.getEvent_end_time(); int event_status = eb.getEvent_status(); int
		 * participents_count = eb.getParticipents_count(); // System.out.println(eb);
		 * 
		 * System.out.println(event_name + " " + event_type + "   " + event_start_date +
		 * "   " + event_end_date + "  " + event_start_time + "  " + event_end_time +
		 * " " + event_status + " " + participents_count);
		 */
		ss.createEvent(eb);
		session = request.getSession();
		if (eb.isAlready_Present()) {
			session.setAttribute("eventName", event_name);
			EventBean Event = ss.getEventDetail(event_name);
			modelmap.addAttribute("event", Event);
			return "Event";
		} else {
			session.setAttribute("eventName", event_name);
			EventBean Event = ss.getEventDetail(event_name);
			modelmap.addAttribute("event", Event);
			return "Event";
		}
	}

	@RequestMapping(value = "/Event/{eventid}", method = RequestMethod.GET)
	public String event(Model model, ModelMap modelmap, HttpServletRequest request, @PathVariable String eventid) {
		EventBean eb = new EventBean();
		session = request.getSession();
		model.addAttribute("event", eb);
		System.out.println("controller event :" + eventid);
		EventBean Event = ss.getEventDetail(eventid);
		System.out.println(Event);
		modelmap.addAttribute("event", Event);
		session.setAttribute("eventName", eventid);
		return "Event";
	}

	@PostMapping("/register")
	public String partRegi(@ModelAttribute("partreg") ParticipantBean pb, Model model, ModelMap modelmap,
			HttpServletRequest request) {
//		System.out.println(pb);
		ss.registerPart(pb);
		return "succ";
	}

	@GetMapping("/succ")
	public String succ() {
		return "succ";
	}

	@GetMapping(value = "/Event/delete/{id}")
	public String deleteEvent(@PathVariable String id, @ModelAttribute("event") EventBean eb) throws SQLException {
		ss.deleteEvent(id);
		return "redirect:../../AdminHome";
	}

	@RequestMapping(value = "/updateEvent/{id}", method = RequestMethod.GET)
	public String updateEvent(@ModelAttribute("event") EventBean eb, @PathVariable String id) throws SQLException {
		System.out.println("id" + id);
		System.out.println("event : " + eb);
		ss.updateEvent(id, eb);
		return "redirect:/AdminHome";
	}

	@GetMapping("/eventDetail")
	public String eventDetail(Model model, ModelMap modelmap) {
		EventBean eb = new EventBean();
		model.addAttribute("event", eb);
		ArrayList<EventBean> allEvent = ss.getAllEventDetail();
		modelmap.addAttribute("allevent", allEvent);
		return "eventDetail";
	}

	@GetMapping("/PartDetail")
	public String PartDetail(Model model, ModelMap modelmap) {
		ArrayList<ParticipantBean> allParticipant = ss.getAllParticipant();
//		System.out.println(allParticipant);
		modelmap.addAttribute("allParticipant", allParticipant);
		return "PartDetail";
	}

	@GetMapping("/qrCodeDetail")
	public String qrCodeDetail(Model model, ModelMap modelmap) {
		ArrayList<QrCodeBean> allQrCode = ss.getAllQrcodeDetails();
//		System.out.println(allQrCode);
		modelmap.addAttribute("allQrCode", allQrCode);
		return "qrCodeDetail";
	}

}
