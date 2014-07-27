package com.find.dao;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.find.model.Device;

@Repository("DeviceDao")
public class DeviceDao extends BaseDao {
	public int add(Device device) {
		return super.saveAndReturnKey(device).intValue();
	}

	public Device getDeviceByToken(String device_token) {
		Device device = null;
		try {
			RowMapper<Device> rm = ParameterizedBeanPropertyRowMapper
					.newInstance(Device.class);
			device = getJdbcTemplate().queryForObject(
					"select * from device where device_token='" + device_token
							+ "'", rm);
			System.out.println("device:" + device.toString());
		} catch (Exception e) {
			if ((e instanceof IncorrectResultSizeDataAccessException)
					&& ((IncorrectResultSizeDataAccessException) e)
							.getActualSize() == 0)
				return null;
			e.printStackTrace();
			return null;
		}
		return device;
	}

	/**
	 * public List<SUser> getUsers() { return
	 * getJdbcTemplate().query("select *  from suser", new
	 * BeanPropertyRowMapper(SUser.class)); } public SUser
	 * getUserByColunm(String columnName, Object value) { return
	 * getJdbcTemplate(
	 * ).queryForObject("select *  from suser where "+columnName+"=?", new
	 * BeanPropertyRowMapper(SUser.class),value); }
	 * 
	 * 
	 * String username1 = (String)jdbcTemplate.queryForObject(
	 * "select teacher_name from teacher_info where teacher_id=?",new
	 * Object[]{userName},java.lang.String.class); ±¨³öString username1 =
	 * (String)jdbcTemplate.queryForObject(
	 * "select teacher_name from teacher_info where teacher_id=?",new
	 * Object[]{userName},java.lang.String.class);
	 */

}
