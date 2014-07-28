package com.find.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.find.model.JsonResult;
import com.find.model.UserInfo;
import com.find.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody Object register(@ModelAttribute("user") UserInfo user) {
		JsonResult result = new JsonResult();
		try {
			if (user != null) {
				if (!userService.checkUserName(user.getUsername())) {
					userService.register(user);
					if (user.getId() > 0) {
						result.setStatus(200);
						result.setValue(user);
						result.setMsg("ע��ɹ���");
					}
				} else {
					result.setStatus(500);
					result.setValue(user);
					result.setMsg("�û����Ѵ��ڣ�");
					return result;
				}
			}
		} catch (Exception e) {
			result.setStatus(500);
			result.setValue(user);
			result.setMsg("ע��ʧ�ܣ�");
			return result;
		}
		return result;
	}

	// "/user/userLogin/{loginname},{loginpassword}?format=json";
	@RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.GET)
	public @ResponseBody Object login(
			@PathVariable("username") String username,
			@PathVariable("password") String password) {
		JsonResult result = new JsonResult();
		try {
			UserInfo user = userService.login(username, password);
			if (user != null) {
				result.setStatus(200);
				result.setValue(user);
				result.setMsg("��¼�ɹ���");
			} else {
				result.setMsg("�û��������벻��ȷ��");
				result.setValue(username + password);
				result.setStatus(500);
			}
		} catch (Exception e) {
			result.setMsg("��¼�쳣��");
			result.setValue(null);
			result.setStatus(500);
		}
		return result;
	}
}
