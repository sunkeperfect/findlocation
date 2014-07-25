package com.find.dao;

import org.springframework.stereotype.Repository;

import com.find.model.Device;

@Repository("DeviceDao")
public class DeviceDao extends BaseDao {
	public int add(Device device) {
		return super.saveAndReturnKey(device).intValue();
	}

	// public Device getDeviceByToken(String device_token) {
	// getJdbcTemplate().queryForObject(
	// "select * from device where device_token=", requiredType);
	// return device_token;
	// }
	/**
	 * public List<SUser> getUsers() { return
	 * getJdbcTemplate().query("select *  from suser", new
	 * BeanPropertyRowMapper(SUser.class)); } public SUser
	 * getUserByColunm(String columnName, Object value) { return
	 * getJdbcTemplate(
	 * ).queryForObject("select *  from suser where "+columnName+"=?", new
	 * BeanPropertyRowMapper(SUser.class),value); }
	 */
}
