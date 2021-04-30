package com.tcs.tep.gech.cs03.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sinch.xms.ApiConnection;
import com.sinch.xms.SinchSMSApi;
import com.sinch.xms.api.MtBatchTextSmsResult;
import com.tcs.tep.gech.cs03.bean.EventBean;
import com.tcs.tep.gech.cs03.bean.ParticipantBean;
import com.tcs.tep.gech.cs03.bean.QrCodeBean;

public class QrCodeUtil {
	public void createQrCode(EventBean eventDetail, ParticipantBean pb, QrCodeBean qrb) {

		System.out.println(eventDetail);
		System.out.println(pb);
		System.out.println(qrb);
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath;
		try {
			fullPath = URLDecoder.decode(path, "UTF-8");
			System.out.println("Full Path : " + fullPath);
			fullPath = fullPath.replace("target/classes/", "");
			System.out.println("Full Path : " + fullPath);
			fullPath = fullPath + "src/main/resources/event/" + qrb.getEVENT_NAME().replaceAll(" ", "_") + "/";
			System.out.println("Full Path : " + fullPath);
			fullPath = fullPath.replace("\\", "/").substring(1);
			createFolder(fullPath);
			System.out.println("Full Path : " + fullPath);
			String inpath = fullPath + qrb.getINQRCODE_NAME();
			System.out.println("in Path : " + inpath);
			String outpath = fullPath + qrb.getOUTQRCODE_NAME();
			System.out.println("in Path : " + outpath);
			String data = qrb.getFIRST_NAME() + "_" + pb.getLast_name() + "_" + qrb.getEVENT_NAME() + "_"
					+ qrb.getPHONE();
			String charset = "UTF-8";
			int width = 200;
			int height = 200;
			Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();

			hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

			qrCodeGeneate(data, inpath, charset, hashMap, height, width);
			qrCodeGeneate(data, outpath, charset, hashMap, height, width);
			String RECIPIENTS = "91" + pb.getPhone();
			System.out.println("91" + pb.getPhone());
			System.out.println(RECIPIENTS);
			String SERVICE_PLAN_ID = "54accc1c898c48d5a9dda8c4c3aac103";
			String TOKEN = "2eb2d73aeec14b58a318122c15560800";
			try (ApiConnection apiconn = ApiConnection.builder().servicePlanId(SERVICE_PLAN_ID).token(TOKEN).start()) {
				// Sending a simple Text Message
				
				  MtBatchTextSmsResult batch =
				  apiconn.createBatch(SinchSMSApi.batchTextSms().sender("447537454625")
				  .addRecipient(RECIPIENTS) .body("Dear " + pb.getFirst_name() +
				  pb.getLast_name() + ",\r\n" + "\r\n" +
				  "We would like to take this opportunity to inform you that we are inviting you to the "
				  + eventDetail.getEvent_type() + " on " + eventDetail.getEvent_name() + " at "
				  + eventDetail.getEvent_loacation() + " and event start from " +
				  eventDetail.getEvent_start_date() + " at " +
				  eventDetail.getEvent_start_time() + " to " + eventDetail.getEvent_end_date()
				  + " at " + eventDetail.getEvent_end_time() +
				  ".We would be highly honored if you can spare some time from your busy schedule to attend the "
				  + eventDetail.getEvent_name() + ".\r\n" + "\r\n" +
				  "Your QrCode is for Event is "+inpath+ "\r\n" +
				  "We eagerly await your participation in the " + eventDetail.getEvent_name() +
				  ".\r\n" + "\r\n" + "Thanks") .build());
				  
				  System.out.println("Successfully sent batch " + batch.id());
				 
			} catch (Exception e) {
				System.out.println("Batch send failed: " + e.getMessage());
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}

	private void createFolder(String fullPath) {
		Path pathurl = Paths.get(fullPath);
		System.out.println(pathurl);
		try {
			Files.createDirectories(pathurl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Directory is created!");
	}

	private void qrCodeGeneate(String data, String fullPath, String charset,
			Map<EncodeHintType, ErrorCorrectionLevel> hashMap, int height, int width) {
		BitMatrix matrix;
		try {
			matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE,
					width, height);
			MatrixToImageWriter.writeToFile(matrix, fullPath.substring(fullPath.lastIndexOf('.') + 1),
					new File(fullPath));
		} catch (IOException | WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
