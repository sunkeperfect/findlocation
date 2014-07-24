package com.find.service;

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

}
