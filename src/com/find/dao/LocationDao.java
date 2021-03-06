package com.find.dao;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.find.model.Location;

@Repository("locationDao")
public class LocationDao extends BaseDao {
	public Location addLocation(Location location) {
		location.setDate_time(System.currentTimeMillis());
		location.setId(super.saveAndReturnKey(location).intValue());
		return location;
	}
	/**
	 * 查询位置信息
	 * @param device_token
	 * @param device_id
	 * @return
	 */
	public List<Location> getLocations(String device_token, String device_id) {
		// return getJdbcTemplate().query("select * from location where  ",
		// new BeanPropertyRowMapper(Location.class));
		RowMapper<Location> rm = ParameterizedBeanPropertyRowMapper
				.newInstance(Location.class);
		long currentTime = System.currentTimeMillis();
		String sql = "select * from location where device_token='"
				+ device_token + "' and " + currentTime

				+ " < (select expirationdate from pay_info where device_id='" + device_id
				+ "') ORDER BY id DESC  LIMIT 0,300";

		System.out.println("execute getLocations sql:" + sql);

		return getJdbcTemplate().query(sql, rm);
	}
	// public Location getLocationByColunm(String columnName, Object value) {
	// return getJdbcTemplate().queryForObject(
	// "select * from location where" + columnName + "=?",
	// new BeanPropertyRowMapper(Location.class), value);
	// }
}
