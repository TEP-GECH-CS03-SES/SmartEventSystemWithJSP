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

import com.tcs.tep.gech.cs03.bean.AddUserBean;
import com.tcs.tep.gech.cs03.bean.EventBean;
import com.tcs.tep.gech.cs03.bean.ForgottenBean;
import com.tcs.tep.gech.cs03.bean.LoginBean;
import com.tcs.tep.gech.cs03.bean.ParticipantBean;
import com.tcs.tep.gech.cs03.bean.QrCodeBean;

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

			} else {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void createUser(AddUserBean ub) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String username = ub.getUserName();
			String email = ub.getEmail();
			String phone = ub.getPhone();
			String role = "user";
//			System.out.println(ub);
			String select = "select * from login";
			boolean present = false;
			PreparedStatement ps = conn.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("username").equals(username) && rs.getString("email").equals(email) && rs.getString("phone").equals(phone)){
					present = true;
					AddUserBean.setConfirm(true);
					break;
				}
			}
			if(!present) {
				String insertEvent = "insert into login(USERNAME,EMAIL,PHONE, ROLE ) values "
						+ "('" + username + "','" + email + "','" + phone + "','" + role + "')";
				stmt.execute(insertEvent);
				AddUserBean.setConfirm(true);
				System.out.println("Values inserted");	
			}else {
				AddUserBean.setConfirm(false);
				System.out.println("Values Already inserted");	
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
				eventDetail.setId(rs.getInt("sl_no"));
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

	public EventBean getEventDetail(String eventid) {
		EventBean event = new EventBean();
		try {
			System.out.println("event ID : "+eventid);
			String getallevent = "select * from event ";
//					+ "where EVENT_NAME='" + eventName.toLowerCase() + "'";

//			System.out.println(getallevent);
			PreparedStatement ps = conn.prepareStatement(getallevent);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("EVENT_NAME"));
//				System.out.println(rs.getString("EVENT_NAME").toLowerCase());
//				System.out.println(rs.getString("EVENT_NAME").toLowerCase().equalsIgnoreCase(eventName));
				System.out.println(rs.getString("EVENT_NAME").equals(eventid) + "     " + rs.getInt("sl_no"));
				if (rs.getString("sl_no").equals(eventid) || rs.getString("EVENT_NAME").equals(eventid)) {
					System.out.println(rs.getString("EVENT_NAME"));
					event.setId(rs.getInt("sl_no"));
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
			}
			System.out.println(event);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return event;
	}

	public void registerPart(ParticipantBean pb) {
//		System.out.println(pb);
		try {
			Statement stmt = conn.createStatement();
			String insert = "insert into PARTICIPANTS(FIRST_NAME,LAST_NAME,EVENT_NAME,EMAIL,PHONE) values('"
					+ pb.getFirst_name() + "','" + pb.getLast_name() + "','" + pb.getEvent_name() + "','"
					+ pb.getEmail() + "','" + pb.getPhone() + "')";
			stmt.execute(insert);
			System.out.println("inserted");
			pb.setRegistered(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertQrData(QrCodeBean qrb) {
//		System.out.println(qrb);
		try {
			Statement stmt = conn.createStatement();
			String insertQrcode = "insert into QRCODE( FIRST_NAME,PHONE,EVENT_NAME,TEXT,INQRCODE_NAME,OUTQRCODE_NAME,STATUS) values('"
					+ qrb.getFIRST_NAME() + "','" + qrb.getPHONE() + "','" + qrb.getEVENT_NAME() + "','" + qrb.getTEXT()
					+ "','" + qrb.getINQRCODE_NAME() + "','" + qrb.getOUTQRCODE_NAME() + "'," + 1 + ")";
			stmt.execute(insertQrcode);
			System.out.println("qr code inserted");
			qrb.setInserted(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteEvent(String id) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String delete = "delete from event where sl_no ='" + id + "'";
			stmt.executeUpdate(delete);
			System.out.println("Deleted");
			stmt.closeOnCompletion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateEvent(String id, EventBean eb) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String select = "select * from event where sl_no =" + id + "";
			PreparedStatement ps = conn.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			Time event_Start_Time = null;
			Time event_start_time;
			Time event_End_Time = null;
			Time event_end_time;
			while (rs.next()) {
				event_Start_Time = rs.getTime("EVENT_START_TIME");
				event_End_Time = rs.getTime("EVENT_END_TIME");
			}
			String event_name = eb.getEvent_name();
			String event_type = eb.getEvent_type();
			Date event_start_date = eb.getEvent_start_date();
			Date event_end_date = eb.getEvent_end_date();
			if (eb.getEvent_start_time().equals("00:00")) {
				event_start_time = event_Start_Time;
			} else {
				System.out.println(eb.getEvent_start_time());
				event_start_time = Time.valueOf(eb.getEvent_start_time());
			}
			if (eb.getEvent_end_time().equals("00:00")) {
				event_end_time = event_End_Time;
			} else {
				event_end_time = Time.valueOf(eb.getEvent_end_time());
			}
			int participents_count = eb.getParticipents_count();
			String event_location = eb.getEvent_loacation();
			System.out.println(eb);

			String update = "update event set EVENT_NAME = '" + event_name + "',EVENT_TYPE='" + event_type
					+ "', EVENT_START_DATE='" + event_start_date + "', EVENT_END_DATE ='" + event_end_date
					+ "', EVENT_START_TIME='" + event_start_time + "', EVENT_END_TIME='" + event_end_time
					+ "',PARTICIPENTS_COUNT='" + participents_count + "', EVENT_LOACATION ='" + event_location
					+ "' where sl_no =" + id + "";
			stmt.executeUpdate(update);
			System.out.println("updated");
			stmt.closeOnCompletion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ParticipantBean> getAllParticipant() {
		ArrayList<ParticipantBean> allParticipants = new ArrayList<ParticipantBean>();
		try {
			String getallevent = "SELECT * FROM PARTICIPANTS ";
			PreparedStatement ps = conn.prepareStatement(getallevent);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ParticipantBean participants = new ParticipantBean();
				participants.setSlno(rs.getInt("SLNO"));
				participants.setFirst_name(rs.getString("FIRST_NAME"));
				participants.setLast_name(rs.getString("LAST_NAME"));
				participants.setEvent_name(rs.getString("EVENT_NAME"));
				participants.setEmail(rs.getString("EMAIL"));
				participants.setPhone(rs.getString("PHONE"));
//				System.out.println(participants);
				allParticipants.add(participants);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		System.out.println(allParticipants);
		return allParticipants;
	}

	public ArrayList<QrCodeBean> getAllQrcodeDetail() {
		ArrayList<QrCodeBean> allQrcodeDetail = new ArrayList<QrCodeBean>();
		try {
			String getallevent = "select * from QRCODE";
			PreparedStatement ps = conn.prepareStatement(getallevent);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				QrCodeBean qrcodeDetail = new QrCodeBean();
				qrcodeDetail.setSlno(rs.getInt("SLNO"));
				qrcodeDetail.setFIRST_NAME(rs.getString("FIRST_NAME"));
				qrcodeDetail.setPHONE(rs.getString("PHONE"));
				qrcodeDetail.setEVENT_NAME(rs.getString("EVENT_NAME"));
				qrcodeDetail.setTEXT(rs.getString("TEXT"));
				qrcodeDetail.setINQRCODE_NAME(rs.getString("INQRCODE_NAME"));
				qrcodeDetail.setOUTQRCODE_NAME(rs.getString("OUTQRCODE_NAME"));
				qrcodeDetail.setSTATUS(rs.getInt("STATUS"));
//				System.out.println(qrcodeDetail);
				allQrcodeDetail.add(qrcodeDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		System.out.println(allQrcodeDetail);
		return allQrcodeDetail;
	}
}
