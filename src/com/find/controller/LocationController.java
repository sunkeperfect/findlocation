package com.find.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.find.model.JsonResult;
import com.find.model.Location;
import com.find.service.LocationService;

@Controller
public class LocationController {
	@Autowired
	LocationService locationService;

	@RequestMapping(value = "/location", method = RequestMethod.POST)
	public @ResponseBody JsonResult addLocation(
			@ModelAttribute Location location) {
		JsonResult result = new JsonResult();
		result.setStatus(200);
		int id = locationService.addLocation(location);
		if (id > 0) {
			result.setStatus(200);
			result.setMsg("插入数据成功！");
		} else {
			result.setStatus(1001);
			result.setMsg("插入数据失败！");
		}
		return result;
	}

	@RequestMapping(value = "/locations/{device_token}/{username}/{device_id}", method = RequestMethod.GET)
	public @ResponseBody JsonResult getLocationList(
			@PathVariable("username") String username,
			@PathVariable("device_id") String device_id,
			@PathVariable("device_token") String device_token) {
		JsonResult result = new JsonResult();
		result.setMsg("获取数据异常！");
		result.setStatus(500);
		try {
			result.setMsg("获取数据成功！");
			result.setStatus(200);
			result.setValue(locationService.getLocations());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
