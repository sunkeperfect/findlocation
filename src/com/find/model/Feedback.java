package com.find.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.find.dao.Saveable;

public class Feedback implements Saveable {
	int id;
	String contact_info;
	String content;
	long datetime;
	String device_id;
	String app_version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContact_info() {
		return contact_info;
	}

	public void setContact_info(String contact_info) {
		this.contact_info = contact_info;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getDatetime() {
		return datetime;
	}

	public void setDatetime(long datetime) {
		this.datetime = datetime;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getApp_version() {
		return app_version;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}

	private static final String TABLE_NAME = "feedback";
	private static String[] keyColumns = { "contact_info", "content",
			"datetime", "device_id", "app_version" };

	@JsonIgnore
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return TABLE_NAME;
	}

	@JsonIgnore
	@Override
	public String[] getKeyColumns() {
		// TODO Auto-generated method stub
		return keyColumns;
	}
}
