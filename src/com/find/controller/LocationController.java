package com.find.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.find.model.BaseResult;
import com.find.model.Location;
import com.find.service.LocationService;

@Controller
public class LocationController {
	@Autowired
	LocationService locationService;

	@RequestMapping({ "/location" })
	public @ResponseBody BaseResult addLocation(
			@ModelAttribute Location location) {
		BaseResult result = new BaseResult();
		result.setStatus(200);
		result.setValue(location);
		int id = locationService.addLocation(location);
		// BaseResult result = new BaseResult();
		if (id > 0) {
			result.setStatus(200);
			result.setValue("插入数据成功！");
		} else {
			result.setStatus(1001);
			result.setValue("插入数据失败！");
		}
		return result;
	}
}
