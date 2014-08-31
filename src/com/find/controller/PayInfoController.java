package com.find.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.find.model.JsonResult;
import com.find.model.PayInfo;
import com.find.service.PayInfoService;

@Controller
public class PayInfoController {
	@Autowired
	PayInfoService payInfoService;

	/**
	 * 
	 * @param type
	 *            0 deviceId，1 username;
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value = "/payinfo/{type}/{parameter}", method = RequestMethod.GET)
	public @ResponseBody Object getPayInfo(@PathVariable("type") int type,
			@PathVariable("parameter") String parameter) {
		JsonResult result = new JsonResult();
		PayInfo info = null;
		try {
			// 判断参数 是否
			if (type == 0) {
				/**
				 * deviceId
				 */
				info = payInfoService.getPayInfo(parameter);
				// payInfoService.
				result.setMsg("请求成功！");
				result.setValue(info);
				result.setStatus(200);
			} else if (type == 1) {
				/**
				 * 
				 */
				info = payInfoService.getPayInfo(parameter);
				result.setMsg("请求成功！");
				result.setValue(info);
				result.setStatus(200);

			}
		} catch (Exception e) {
			result.setMsg("失败！");
			result.setValue(null);
			result.setStatus(500);
		}
		return result;
	}

	@RequestMapping(value = "/payinfo/{device_id}/{time}", method = RequestMethod.PUT)
	public @ResponseBody Object increaseTime(
			@PathVariable("device_id") String device_id,
			@PathVariable("time") long time) {
		JsonResult result = new JsonResult();
		try {
			if (payInfoService.increaseTime(device_id, time)) {
				result.setMsg("增加时间成功！");
				result.setValue(null);
				result.setStatus(200);
			} else {
				result.setMsg("添加时间失败");
				result.setValue(null);
				result.setStatus(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("出现异常！");
			result.setValue(null);
			result.setStatus(500);
		}
		return result;
	}
}
