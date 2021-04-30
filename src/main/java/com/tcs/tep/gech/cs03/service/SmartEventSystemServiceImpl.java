package com.tcs.tep.gech.cs03.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.tep.gech.cs03.bean.EventBean;
import com.tcs.tep.gech.cs03.bean.ParticipantBean;
import com.tcs.tep.gech.cs03.bean.QrCodeBean;
import com.tcs.tep.gech.cs03.dao.SmartEventSystemDAOImpl;
import com.tcs.tep.gech.cs03.util.QrCodeUtil;

@Service
public class SmartEventSystemServiceImpl implements SmartEventSystemService {

	@Autowired
	private SmartEventSystemDAOImpl sdao;

	public void loginCheck(String username, String password) {
//	System.out.println(username+"   "+password);
		sdao.loginCheck(username, password);
	}

	public void updatePassword(String username, String password, String email) {
		sdao.updatePassword(username, password, email);
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

	public void registerPart(ParticipantBean pb) {
		QrCodeUtil qru = new QrCodeUtil();
		sdao.registerPart(pb);
		if (pb.isRegistered()) {
			System.out.println(pb.getEvent_name() + pb.getPhone());
			EventBean eventDetail = sdao.getEventDetail(pb.getEvent_name());
			System.out.println("registered");
			QrCodeBean qrb = new QrCodeBean();
			qrb.setEVENT_NAME(eventDetail.getEvent_name());
			qrb.setFIRST_NAME(pb.getFirst_name());
			qrb.setPHONE(pb.getPhone());
			qrb.setSTATUS(1);
			String inqrcodename = pb.getEvent_name() + pb.getFirst_name() + pb.getLast_name() + pb.getPhone()
					+ "in.png";
			inqrcodename = inqrcodename.replaceAll(" ", "_");
			qrb.setINQRCODE_NAME(inqrcodename);
			String outqrcodename = pb.getEvent_name() + pb.getFirst_name() + pb.getLast_name() + pb.getPhone()
					+ "out.png";
			outqrcodename = outqrcodename.replaceAll(" ", "_");
			qrb.setOUTQRCODE_NAME(outqrcodename);
			qrb.setTEXT("Dear " + pb.getFirst_name() + pb.getLast_name() + ",\r\n"
					+ "We would like to take this opportunity to inform you that we are inviting you to the "
					+ eventDetail.getEvent_type() + " on " + eventDetail.getEvent_name() + " at "
					+ eventDetail.getEvent_loacation() + " and event start from " + eventDetail.getEvent_start_date()
					+ " at " + eventDetail.getEvent_start_time() + " to " + eventDetail.getEvent_end_date() + " at "
					+ eventDetail.getEvent_end_time()
					+ ".We would be highly honored if you can spare some time from your busy schedule to attend the "
					+ eventDetail.getEvent_name() + ".\r\n" + "\r\n" + "We eagerly await your participation in the "
					+ eventDetail.getEvent_name() + ".\r\n" + "\r\n" + "Thanks");
			sdao.insertQrData(qrb);
			if(qrb.isInserted()) {
				qru.createQrCode(eventDetail, pb,qrb);	
			}
			
		} else
			System.out.println("Not Registered");
	}

	public void deleteEvent(String id) {
		sdao.deleteEvent(id);
	}

	public void updateEvent(String id, EventBean eb) {
		sdao.updateEvent(id,eb);
	}

	public ArrayList<ParticipantBean> getAllParticipant() {
		return sdao.getAllParticipant();
	}

	public ArrayList<QrCodeBean> getAllQrcodeDetails() {
		return sdao.getAllQrcodeDetail();
	}
}
