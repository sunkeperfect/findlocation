package com.find.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.find.model.JsonResult;
import com.find.model.Listener;
import com.find.service.ListenerService;

@Controller
public class ListenerController {
	@Autowired
	ListenerService listenerService;

	@RequestMapping(value = "listener", method = RequestMethod.POST)
	public @ResponseBody Object addListener(Listener listener) {
		JsonResult result = new JsonResult();
		listenerService.addListener(listener);
		if (listener.getId() > 0) {
			result.setMsg("添加成功！");
			result.setStatus(200);
			result.setValue(listener);
		} else {
			result.setMsg("添加失败！");
			result.setValue(null);
			result.setStatus(500);
		}
		return listener;
	}
}
