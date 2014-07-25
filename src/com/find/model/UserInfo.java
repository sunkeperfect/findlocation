package com.find.model;

import org.codehaus.jackson.annotate.JsonIgnore;

public class UserInfo implements Saveable {
	int id;
	String username;
	String password;
	String device_id;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	private static final String TABLE_NAME = "user_info";
	private static String[] keyColumns = { "username", "password", "device_id", };

	@Override
	@JsonIgnore
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	@JsonIgnore
	public String[] getKeyColumns() {
		return keyColumns;
	}

}
