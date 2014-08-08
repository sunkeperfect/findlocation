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

	public List<Location> getLocations() {
		// return getJdbcTemplate().query("select * from location where  ",
		// new BeanPropertyRowMapper(Location.class));
		RowMapper<Location> rm = ParameterizedBeanPropertyRowMapper
				.newInstance(Location.class);
		String sql = "select * from location";
		return getJdbcTemplate().query(sql, rm);
	}

	// public Location getLocationByColunm(String columnName, Object value) {
	// return getJdbcTemplate().queryForObject(
	// "select * from location where" + columnName + "=?",
	// new BeanPropertyRowMapper(Location.class), value);
	// }
}
