package com.find.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.find.model.BaseResult;
import com.find.service.DeviceService;

@Controller
@RequestMapping(value="/device")
public class DeviceController {
	@Autowired
	DeviceService deviceService;
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Object add(String device_id) {
		BaseResult result = new BaseResult();
		if (device_id == null || "".equals(device_id)) {
			result.setStatus(500);
			result.setMsg("���ʧ���ˣ�device_idΪ��!");
			return result;
		}
		int id = deviceService.add(device_id);
		if (id > 0) {
			result.setStatus(200);
			result.setMsg("��ӳɹ��ˣ�");
		} else {
			result.setStatus(500);
			result.setMsg("���ʧ���ˣ�");
		}
		return result;
	}
}
