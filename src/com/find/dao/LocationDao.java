package com.find.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.find.model.Location;

@Repository("locationDao")
public class LocationDao extends BaseDao {
	public Location addLocation(Location location) {
		location.setId(super.saveAndReturnKey(location).intValue());
		return location;
	}

	public List<Location> getLocations() {
		return getJdbcTemplate().query("select * from location ",
				new BeanPropertyRowMapper(Location.class));
	}

	public Location getLocationByColunm(String columnName, Object value) {
		return getJdbcTemplate().queryForObject(
				"select * from location where" + columnName + "=?",
				new BeanPropertyRowMapper(Location.class), value);
	}
}