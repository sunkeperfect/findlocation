package com.find.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
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
		return result;
	}

	@RequestMapping(value = "listener/{id}/{name}", method = RequestMethod.PUT)
	public @ResponseBody Object updateListener(@PathVariable("id") int id,
			@PathVariable("name") String name) {
		JsonResult result = new JsonResult();
		try {
			if (id == 0 || StringUtils.isEmpty(name)) {
				result.setMsg("参数错误!");
				result.setValue(null);
				result.setStatus(500);
				return result;
			}
			int count = listenerService.updateName(id, name);
			if (count > 0) {
				result.setMsg("修改成功！");
				result.setStatus(200);
				result.setValue(null);
			} else {
				result.setMsg("修改失败！");
				result.setStatus(500);
				result.setValue(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取列表数据
	 * 
	 * @param username
	 * @param device_id
	 * @return
	 */
	@RequestMapping(value = "listeners/{username}/{device_id}", method = RequestMethod.GET)
	public @ResponseBody Object getListeners(
			@PathVariable("username") String username,
			@PathVariable("device_id") String device_id) {
		JsonResult result = new JsonResult();
		try {
			List<Listener> list = listenerService.getListeners(username,
					device_id);
			if (list != null) {
				result.setMsg("获取数据成功！");
				result.setValue(list);
				result.setStatus(200);
			} else {
				result.setMsg("获取数据异常！");
				result.setValue(null);
				result.setStatus(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "listener/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteListener(@PathVariable("id") int id) {
		JsonResult result = new JsonResult();
		try {
			if (listenerService.delete(id)) {
				result.setMsg("删除成功！");
				result.setStatus(200);
				result.setValue(null);
			} else {
				result.setMsg("删除失败");
				result.setStatus(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
