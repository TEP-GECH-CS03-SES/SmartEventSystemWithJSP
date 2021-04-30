package com.tcs.tep.gech.cs03.bean;

public class QrCodeBean {

	private String FIRST_NAME;
	private String PHONE;
	private String EVENT_NAME;
	private String TEXT;
	private String INQRCODE_NAME;
	private String OUTQRCODE_NAME;
	private int STATUS;
	private boolean inserted;
	
	public boolean isInserted() {
		return inserted;
	}
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}
	public QrCodeBean(String fIRST_NAME, String pHONE, String eVENT_NAME, String tEXT,
			String iNQRCODE_NAME, String oUTQRCODE_NAME, int sTATUS) {
		super();
		FIRST_NAME = fIRST_NAME;
		PHONE = pHONE;
		EVENT_NAME = eVENT_NAME;
		TEXT = tEXT;
		INQRCODE_NAME = iNQRCODE_NAME;
		OUTQRCODE_NAME = oUTQRCODE_NAME;
		STATUS = sTATUS;
	}
	public QrCodeBean() {
		super();
	}
	@Override
	public String toString() {
		return "QrCodeBean [FIRST_NAME=" + FIRST_NAME + ", PHONE=" + PHONE + ", EVENT_NAME=" + EVENT_NAME + ", TEXT="
				+ TEXT +  ", INQRCODE_NAME=" + INQRCODE_NAME + ", OUTQRCODE_NAME="
				+ OUTQRCODE_NAME + ", STATUS=" + STATUS + "]";
	}
	public String getFIRST_NAME() {
		return FIRST_NAME;
	}
	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public String getEVENT_NAME() {
		return EVENT_NAME;
	}
	public void setEVENT_NAME(String eVENT_NAME) {
		EVENT_NAME = eVENT_NAME;
	}
	public String getTEXT() {
		return TEXT;
	}
	public void setTEXT(String tEXT) {
		TEXT = tEXT;
	}
	public String getINQRCODE_NAME() {
		return INQRCODE_NAME;
	}
	public void setINQRCODE_NAME(String iNQRCODE_NAME) {
		INQRCODE_NAME = iNQRCODE_NAME;
	}
	public String getOUTQRCODE_NAME() {
		return OUTQRCODE_NAME;
	}
	public void setOUTQRCODE_NAME(String oUTQRCODE_NAME) {
		OUTQRCODE_NAME = oUTQRCODE_NAME;
	}
	public int getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	} 
	
}
