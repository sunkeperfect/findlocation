package com.find.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.find.dao.Saveable;

public class Location implements Saveable {
	int id;
	String longitude;
	String latitude;
	String device_id;
	String device_token;
	String address;
	int source_flag;
	long date_time;

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

	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getDate_time() {
		return date_time;
	}

	public void setDate_time(long date_time) {
		this.date_time = date_time;
	}

	public int getSource_flag() {
		return source_flag;
	}

	public void setSource_flag(int source_flag) {
		this.source_flag = source_flag;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", device_id=" + device_id
				+ ", device_token=" + device_token + ", address=" + address
				+ ", source_flag=" + source_flag + ", date_time=" + date_time
				+ "]";
	}

	public static final String TABLE_NAME = "location";
	public static String[] keyColumns = { "longitude", "latitude", "address",
			"device_token", "device_id", "date_time", "source_flag" };

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
