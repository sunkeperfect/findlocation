package com.find.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.find.dao.LocationDao;
import com.find.model.Location;

@Service("locationService")
public class LocationService {
	@Autowired
	LocationDao locationDao;

	public int addLocation(Location location) {
		location = locationDao.addLocation(location);
		return location.getId();
	}

	public List<Location> getLocations(String device_token) {
		return locationDao.getLocations(device_token);
	}
}
