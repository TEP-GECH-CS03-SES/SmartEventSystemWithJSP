package com.tcs.tep.gech.cs03.bean;

import java.sql.Date;
import java.util.Map;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class EventBean {

	@NotEmpty(message = "Event Name cannot be null")
	private String event_name;
	@NotEmpty(message = "Event Type cannot be null")
	private String event_type;
	@Future(message = "Date Must be in Future")
	private Date event_start_date;
	@Future(message = "Date Must be in Future")
	private Date event_end_date;
	
	@FutureOrPresent
	private String event_start_time;
	@FutureOrPresent
	private String event_end_time;
	

	private int event_status;
	
	@Min(value=30,message = "Count Must be Greater than 30")
	private int participents_count;

	
	public EventBean() {
		super();
	}

	public EventBean(@NotEmpty(message = "Event Name cannot be null") String event_name,
			@NotEmpty(message = "Event Type cannot be null") @NotEmpty(message = "Event Type cannot be null") String event_type,
			@Future(message = "Date Must be in Future") Date event_start_date,
			@Future(message = "Date Must be in Future") Date event_end_date, @FutureOrPresent String event_start_time,
			@FutureOrPresent String event_end_time, int event_status,
			@Min(value = 30, message = "Count Must be Greater than 30") int participents_count) {
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

	public @NotEmpty(message = "Event Type cannot be null") String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(@NotEmpty(message = "Event Type cannot be null") String event_type) {
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

	public String getEvent_start_time() {
		return event_start_time;
	}

	public void setEvent_start_time(String event_start_time) {
		this.event_start_time = event_start_time;
	}

	public String getEvent_end_time() {
		return event_end_time;
	}

	public void setEvent_end_time(String event_end_time) {
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
