package com.find.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		result.setStatus(1001);
		result.setMsg("插入数据失败！");
		if (location != null && !StringUtils.isEmpty(location.getDevice_id())
				&& !StringUtils.isEmpty(location.getLongitude())
				&& !StringUtils.isEmpty(location.getDevice_token())
				&& !StringUtils.isEmpty(location.getLatitude())) {
			result.setStatus(200);
			int id = locationService.addLocation(location);
			if (id > 0) {
				result.setStatus(200);
				System.out.println(location.getAddress());
				result.setMsg("插入数据成功！");
			} else {
				result.setStatus(500);
				result.setMsg("插入数据失败！");
			}
		} else {
			result.setMsg("参数异常！");
		}
		return result;
	}

	@RequestMapping(value = "/locations/{device_token}/{username}/{device_id}", method = RequestMethod.GET)
	public @ResponseBody JsonResult getLocationList(
			@PathVariable("device_token") String device_token,
			@PathVariable("username") String username,
			@PathVariable("device_id") String device_id) {
		System.out.println("execute locations device_token:" + device_token
				+ "  device_id:" + device_id + "username:" + username);
		JsonResult result = new JsonResult();
		result.setMsg("获取数据异常！");
		result.setStatus(500);
		List<Location> list = locationService.getLocations(device_token,
				device_id);
		//System.out.println("list:"+list.toString());
		try {
			result.setMsg("获取数据成功！");
			result.setStatus(200);
			result.setValue(list);
		} catch (Exception e) {
			// TODO: handle exception

		}
		return result;
	}
}
