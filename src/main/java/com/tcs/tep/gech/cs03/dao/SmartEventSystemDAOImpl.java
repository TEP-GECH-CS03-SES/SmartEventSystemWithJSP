package com.tcs.tep.gech.cs03.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.tcs.tep.gech.cs03.bean.EventBean;
import com.tcs.tep.gech.cs03.bean.ForgottenBean;
import com.tcs.tep.gech.cs03.bean.LoginBean;

@Repository
public class SmartEventSystemDAOImpl implements SmartEventSystemDAO {

	private Connection conn;
	LoginBean lb = new LoginBean();
	ForgottenBean fb = new ForgottenBean();

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public SmartEventSystemDAOImpl() throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
		String rootPath = null;
		rootPath = getPath();
//		System.out.println("root path :"+ rootPath );
		rootPath = rootPath + "src/main/webapp/WEB-INF/";
//		System.out.println("root path :"+ rootPath );
		rootPath = rootPath + "MyDB";
//		System.out.println("Root Path" + rootPath);
		Class.forName("org.h2.Driver");
		this.conn = DriverManager.getConnection("jdbc:h2:" + rootPath + ";create=true", "sesuser", "sespassword");
		System.out.println(conn);
		return;
	}

	private String getPath() throws UnsupportedEncodingException {
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
//        System.out.println("Full Path : " + fullPath);
		fullPath = fullPath.replace("target/classes/", "");
//		System.out.println("Full Path : " + fullPath);
		return fullPath;
	}

	public void loginCheck(String username, String password) {
//		System.out.println(username+"   "+password);
		String getuser = "select * from login where username='" + username + "' and password='" + password + "'";
		String role = null;
		String dbusername = null;
		String dbpassword = null;
		try {
			PreparedStatement ps = conn.prepareStatement(getuser);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				/*
				 * System.out.println(rs.getString("username"));
				 * System.out.println(rs.getString("password"));
				 * System.out.println(rs.getString("role"));
				 */
				role = rs.getString("role");
				dbusername = rs.getString("username");
				dbpassword = rs.getString("password");
			}
//			System.out.println(username +"  "+dbusername+"     "+password+"        "+dbpassword);
			if (username.equals(dbusername) && password.equals(dbpassword)) {
				lb.setValid(true);
				if (role.equals("admin")) {
					lb.setRole(role);
					lb.setAdmin(true);
				} else {
					lb.setRole(role);
					lb.setAdmin(false);
				}
			} else {
				lb.setValid(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatePassword(String username, String password, String email) {
		String getuser = "select * from login where username='" + username + "' and email='" + email + "'";
		String dbusername = null;
		String dbemail = null;
		Statement stmt = null;
		try {
			PreparedStatement ps = conn.prepareStatement(getuser);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				/*
				 * System.out.println(rs.getString("username"));
				 * System.out.println(rs.getString("email"));
				 */ dbusername = rs.getString("username");
				dbemail = rs.getString("email");
			}
			if (username.equals(dbusername) && email.equals(dbemail)) {
				String upquery = "update login set password='" + password + "'where username='" + username
						+ "'and email ='" + email + "'";
				stmt = conn.createStatement();
				int num = stmt.executeUpdate(upquery);
				System.out.println("Number of records updated are: " + num);
				if (num == 1) {
					fb.setConfirm(true);
				} else if (num > 1) {

				} else {
					System.out.println("Not Updated Successfully");
				}
			} else {
				fb.setConfirm(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createEvent(EventBean eb) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String event_name = eb.getEvent_name();
			String event_type = eb.getEvent_type();
			Date event_start_date = eb.getEvent_start_date();
			Date event_end_date = eb.getEvent_end_date();
			Time event_start_time = Time.valueOf(eb.getEvent_start_time() + ":00");
			Time event_end_time = Time.valueOf(eb.getEvent_end_time() + ":00");
			int event_status = eb.getEvent_status();
			int participents_count = eb.getParticipents_count();
			String event_location = eb.getEvent_loacation();
			System.out.println(eb);
			String select = "select * from event";
			boolean present = false;
			PreparedStatement ps = conn.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("EVENT_NAME").equals(event_name)) {
					present = true;
					eb.setAlready_Present(true);
					break;
				}
			}
			if (!present) {
				System.out.println(event_name + " " + event_type + "   " + event_start_date + "   " + event_end_date
						+ "  " + event_start_time + "  " + event_end_time + " " + event_status + " "
						+ participents_count + " " + event_location);
				String insertEvent = "insert into event(EVENT_NAME,EVENT_TYPE,EVENT_START_DATE,EVENT_END_DATE,EVENT_START_TIME,EVENT_END_TIME,EVENT_STATUS,PARTICIPENTS_COUNT,EVENT_LOACATION) values "
						+ "('" + event_name + "','" + event_type + "','" + event_start_date + "','" + event_end_date
						+ "','" + event_start_time + "','" + event_end_time + "'," + event_status + ","
						+ participents_count + ",'" + event_location + "')";
				stmt.execute(insertEvent);

				System.out.println("Values inserted");

			}else {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<EventBean> getAllEventDetail() {
		ArrayList<EventBean> allEvent = new ArrayList<EventBean>();
			try {
				String getallevent = "select * from event";
				PreparedStatement ps = conn.prepareStatement(getallevent);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					EventBean eventDetail = new EventBean();
					eventDetail.setEvent_name(rs.getString("EVENT_NAME"));
					eventDetail.setEvent_type(rs.getString("EVENT_TYPE"));
					eventDetail.setEvent_start_date(rs.getDate("EVENT_START_DATE"));
					eventDetail.setEvent_end_date(rs.getDate("EVENT_END_DATE"));
					eventDetail.setEvent_start_time(rs.getTime("EVENT_START_TIME").toString());
					eventDetail.setEvent_end_time(rs.getTime("EVENT_END_TIME").toString());
					eventDetail.setEvent_status(rs.getInt("EVENT_STATUS")); 
					eventDetail.setParticipents_count(rs.getInt("PARTICIPENTS_COUNT"));
					eventDetail.setEvent_loacation(rs.getString("EVENT_LOACATION"));
					allEvent.add(eventDetail);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		return allEvent;
	}

	public EventBean getEventDetail(String eventName) {
		EventBean event = new EventBean();
		try {
			String getallevent = "select * from event where EVENT_NAME='"+ eventName+"'";
			PreparedStatement ps = conn.prepareStatement(getallevent);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				event.setEvent_name(rs.getString("EVENT_NAME"));
				event.setEvent_type(rs.getString("EVENT_TYPE"));
				event.setEvent_start_date(rs.getDate("EVENT_START_DATE"));
				event.setEvent_end_date(rs.getDate("EVENT_END_DATE"));
				event.setEvent_start_time(rs.getTime("EVENT_START_TIME").toString());
				event.setEvent_end_time(rs.getTime("EVENT_END_TIME").toString());
				event.setEvent_status(rs.getInt("EVENT_STATUS")); 
				event.setParticipents_count(rs.getInt("PARTICIPENTS_COUNT"));
				event.setEvent_loacation(rs.getString("EVENT_LOACATION"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	return event;
	}

}
