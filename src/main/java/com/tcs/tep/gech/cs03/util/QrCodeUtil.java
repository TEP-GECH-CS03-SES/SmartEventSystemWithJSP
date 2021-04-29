package com.tcs.tep.gech.cs03.util;



import com.sinch.xms.ApiConnection;
import com.sinch.xms.SinchSMSApi;
import com.sinch.xms.api.MtBatchTextSmsResult;
import com.tcs.tep.gech.cs03.bean.EventBean;
import com.tcs.tep.gech.cs03.bean.ParticipantBean;

public class QrCodeUtil {
	public void createQrCode(EventBean eventDetail, ParticipantBean pb) {
		
		System.out.println(eventDetail);
		String RECIPIENTS = "91" + pb.getPhone();
		System.out.println("91" + pb.getPhone());
		System.out.println(RECIPIENTS);
		String SERVICE_PLAN_ID = "54accc1c898c48d5a9dda8c4c3aac103";
		String TOKEN = "2eb2d73aeec14b58a318122c15560800";
		try (ApiConnection apiconn = ApiConnection.builder().servicePlanId(SERVICE_PLAN_ID).token(TOKEN).start()) {
			// Sending a simple Text Message
	/*		MtBatchTextSmsResult batch = apiconn.createBatch(SinchSMSApi.batchTextSms().sender("447537454625")
					.addRecipient(RECIPIENTS)
					.body("Dear " + pb.getFirst_name() + pb.getLast_name() + ",\r\n" + "\r\n"
							+ "We would like to take this opportunity to inform you that we are inviting you to the "
							+ eventDetail.getEvent_type() + " on " + eventDetail.getEvent_name() + " at "
							+ eventDetail.getEvent_loacation() + " and event start from "
							+ eventDetail.getEvent_start_date() + " at " + eventDetail.getEvent_start_time() + " to "
							+ eventDetail.getEvent_end_date() + " at " + eventDetail.getEvent_end_time()
							+ ".We would be highly honored if you can spare some time from your busy schedule to attend the "
							+ eventDetail.getEvent_name() + ".\r\n" + "\r\n"
							+ "We eagerly await your participation in the " + eventDetail.getEvent_name() + ".\r\n"
							+ "\r\n" + "Thanks")
					.build());

			System.out.println("Successfully sent batch " + batch.id());*/
		} catch (Exception e) {
			System.out.println("Batch send failed: " + e.getMessage());
		}
	}

}
