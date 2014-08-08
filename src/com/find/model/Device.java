package com.find.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.find.dao.Saveable;

public class Device implements Saveable {
	int id;
	String device_id;
	String device_token;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", device_id=" + device_id
				+ ", device_token=" + device_token + "]";
	}

	private static final String TABLE_NAME = "device";
	private static String[] keyColumns = { "device_id", "device_token" };

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
