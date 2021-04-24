package com.tcs.tep.gech.cs03.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
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
public class SmartEventSystemDAOImpl implements SmartEventSystemDAO{

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
		String getuser ="select * from login where username='"+username+"' and password='"+password+"'";
		String role=null;
		String dbusername=null;
		String dbpassword=null;
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
			if(username.equals(dbusername) && password.equals(dbpassword)) {
				lb.setValid(true);
				if(role.equals("admin")) {
					lb.setRole(role);
					lb.setAdmin(true);
				}else {
					lb.setRole(role);
					lb.setAdmin(false);
				}
			}else {
				lb.setValid(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatePassword(String username, String password, String email) {
		String getuser ="select * from login where username='"+username+"' and email='"+email+"'";
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
				 */		dbusername = rs.getString("username");
				dbemail = rs.getString("email");
			}
			if(username.equals(dbusername) && email.equals(dbemail)) {
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
			}else {
				fb.setConfirm(false);
			}
		}catch (SQLException e) {
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
			Time event_start_time = eb.getEvent_start_time();
			Time event_end_time = eb.getEvent_end_time();
			int event_status = eb.getEvent_status();
			int participents_count = eb.getParticipents_count();
//			System.out.println(eb);
			
			System.out.println(event_name + " " + event_type + "   " + event_start_date + "   " + event_end_date + "  "+ event_start_time + "  " + event_end_time + " " + event_status + " " + participents_count);
			String insertEvent = "insert into event(EVENT_NAME,EVENT_TYPE,EVENT_START_DATE,EVENT_END_DATE,EVENT_START_TIME,EVENT_END_TIME,EVENT_STATUS,PARTICIPENTS_COUNT) values "
					+ "('"+event_name+"','"+event_type+"',"+ event_start_date +","+event_end_date+","+ event_start_time+","+event_end_time+","+1+","+participents_count+")";
			stmt.execute(insertEvent);
			System.out.println("Values inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
