package com.find.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.find.dao.Saveable;

/**
 * 
 * @author frank.sun
 *
 */
public class PayInfo implements Saveable {
	int id;
	String username;
	String deviceId;
	Long expirationdate;

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

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Long getExpirationdate() {
		return expirationdate;
	}

	public void setExpirationdate(Long expirationdate) {
		this.expirationdate = expirationdate;
	}

	public static final String TABLE_NAME = "pay_info";
	public static String[] keyColumns = { "username", "device_id",
			"expirationdate" };

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
