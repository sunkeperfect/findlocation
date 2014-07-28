package com.find.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.find.model.BaseResult;
import com.find.model.UserInfo;
import com.find.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody Object register(@ModelAttribute("user") UserInfo user) {
		BaseResult result = new BaseResult();
		try {
			if (user != null) {
				if (!userService.checkUserName(user.getUsername())) {
					userService.register(user);
					if (user.getId() > 0) {
						result.setStatus(200);
						result.setValue(user);
						result.setMsg("注册成功！");
					}
				} else {
					result.setStatus(500);
					result.setValue(user);
					result.setMsg("用户名已存在！");
					return result;
				}
			}
		} catch (Exception e) {
			result.setStatus(500);
			result.setValue(user);
			result.setMsg("注册失败！");
			return result;
		}
		return result;
	}
}
