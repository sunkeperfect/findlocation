package com.find.model;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Location implements Saveable {
	int id;
	String longitude;
	String latitude;
	String device_id;

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


	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}


	static final String TABLE_NAME = "location";
	private static String[] keyColumns = { "longitude", "latitude", "device_id" };

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
