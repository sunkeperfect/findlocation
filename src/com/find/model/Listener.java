package com.find.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.find.dao.Saveable;

public class Listener implements Saveable {
	int id;
	String username;
	String device_id;
	String device_token;
	String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static final String TABLE_NAME = "listeners";
	public static String[] keyColumns = { "username", "device_id",
			"device_token", "name" };

	@Override
	@JsonIgnore
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
