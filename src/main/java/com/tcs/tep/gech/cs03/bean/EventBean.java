package com.tcs.tep.gech.cs03.bean;

import java.sql.Date;
import java.sql.Time;

public class EventBean {

	private String event_name;
	private String event_type;
	private Date event_start_date ;
	private Date event_end_date ;
	private Time event_start_time;
	private Time event_end_time ;
	private int event_status;
	private int participents_count;

	public EventBean() {
		super();
	}
	public EventBean(String event_name, String event_type, Date event_start_date, Date event_end_date,
			Time event_start_time, Time event_end_time, int event_status, int participents_count) {
		super();
		this.event_name = event_name;
		this.event_type = event_type;
		this.event_start_date = event_start_date;
		this.event_end_date = event_end_date;
		this.event_start_time = event_start_time;
		this.event_end_time = event_end_time;
		this.event_status = event_status;
		this.participents_count = participents_count;
	}
	
	@Override
	public String toString() {
		return "EventBean [event_name=" + event_name + ", event_type=" + event_type + ", event_start_date="
				+ event_start_date + ", event_end_date=" + event_end_date + ", event_start_time=" + event_start_time
				+ ", event_end_time=" + event_end_time + ", event_status=" + event_status + ", participents_count="
				+ participents_count + "]";
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	public Date getEvent_start_date() {
		return event_start_date;
	}
	public void setEvent_start_date(Date event_start_date) {
		this.event_start_date = event_start_date;
	}
	public Date getEvent_end_date() {
		return event_end_date;
	}
	public void setEvent_end_date(Date event_end_date) {
		this.event_end_date = event_end_date;
	}
	public Time getEvent_start_time() {
		return event_start_time;
	}
	public void setEvent_start_time(Time event_start_time) {
		this.event_start_time = event_start_time;
	}
	public Time getEvent_end_time() {
		return event_end_time;
	}
	public void setEvent_end_time(Time event_end_time) {
		this.event_end_time = event_end_time;
	}
	public int getEvent_status() {
		return event_status;
	}
	public void setEvent_status(int event_status) {
		this.event_status = event_status;
	}
	public int getParticipents_count() {
		return participents_count;
	}
	public void setParticipents_count(int participents_count) {
		this.participents_count = participents_count;
	}

}
