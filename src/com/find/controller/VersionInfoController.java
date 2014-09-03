package com.find.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.find.model.JsonResult;
import com.find.model.UserInfo;
import com.find.model.VersionInfo;
import com.find.service.VersionInfoService;

@Controller
public class VersionInfoController {
	@Autowired
	VersionInfoService versionInfoService;

	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public @ResponseBody Object getVersion() {
		JsonResult result = new JsonResult();
		try {
			VersionInfo versionInfo = versionInfoService.getVersion();
			if (versionInfo != null) {
				result.setStatus(200);
				result.setValue(versionInfo);
				result.setMsg("获取成功");
			} else {
				result.setMsg("获取信息失败");
				result.setValue(null);
				result.setStatus(500);
			}
		} catch (Exception e) {
			result.setMsg("获取信息异常！");
			result.setValue(null);
			result.setStatus(500);
		}
		return result;
	}
}
