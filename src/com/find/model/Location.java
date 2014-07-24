package com.find.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.find.dao.Saveable;

public class Location implements Saveable {
	int id;
	String longitude;
	String latitude;
	String user_tag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getUser_tag() {
		return user_tag;
	}

	public void setUser_tag(String user_tag) {
		this.user_tag = user_tag;
	}

	static final String TABLE_NAME = "location";
	private static String[] keyColumns = { "longitude", "latitude", "user_tag" };

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
