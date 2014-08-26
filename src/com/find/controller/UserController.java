package com.find.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.find.common.CheckCodeManager;
import com.find.model.JsonResult;
import com.find.model.UserInfo;
import com.find.service.UserService;
import com.find.util.Utils;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/")
	public String index() {
		System.out.println("服务器启动成功！");
		return "index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody Object register(@ModelAttribute("user") UserInfo user,
			int type, String checkcode) {
		JsonResult result = new JsonResult();
		try {
			if (user != null) {
				if (!userService.checkUserName(user.getUsername())) {
					if (type == 0) {
						/**
						 * 手机验证码 验证
						 */
						String code = CheckCodeManager.getInstance()
								.getCheckCode(user.getUsername());
						if (!code.isEmpty() && code.equals(checkcode)) {
							userService.mobileRegister(user);
						}
					} else {
						userService.register(user);
					}
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

	// "/user/userLogin/{loginname},{loginpassword}?format=json";
	/**
	 * @param username
	 * @param password
	 * @return
	 */
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
				result.setMsg("登录成功！");
			} else {
				result.setMsg("用户名或密码不正确！");
				result.setValue(null);
				result.setStatus(500);
			}
		} catch (Exception e) {
			result.setMsg("登录异常！");
			result.setValue(null);
			result.setStatus(500);
		}
		return result;
	}

	/**
	 * 修改密码
	 */
	@RequestMapping(value = "/modify_password/{username}/{password}/{newpassword}", method = RequestMethod.PUT)
	public @ResponseBody Object modifyPassword(
			@PathVariable("username") String username,
			@PathVariable("password") String password,
			@PathVariable("newpassword") String newpassword) {
		JsonResult result = new JsonResult();
		result.setMsg("修改密码失败！");
		result.setStatus(500);
		try {
			if (userService.login(username, password) != null) {
				boolean flag = userService
						.modifyPassword(username, newpassword);
				if (flag) {
					result.setMsg("修改密码成功！");
					result.setStatus(200);
				} else {
					result.setMsg("修改密码失败!!");
					result.setStatus(500);
				}
			} else {
				result.setMsg("没有找到该用户！");
				result.setStatus(200);
			}
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * 取回密码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/retrieve_password", method = RequestMethod.POST)
	public @ResponseBody Object retrievePassword(String email) {
		JsonResult result = new JsonResult();
		result.setMsg("找回密码失败！");
		result.setStatus(500);
		try {
			if (userService.retrievePassword(email)) {
				result.setMsg("新密码已经至您的邮箱！");
				result.setStatus(200);
			} else {
				result.setMsg("找回密码失败！");
				result.setStatus(200);
			}
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * 短信验证码获取
	 */
	@RequestMapping(value = "/checkcode/{mobile}", method = RequestMethod.GET)
	public @ResponseBody Object getCheckCode(
			@PathVariable("mobile") String mobile) {
		JsonResult result = new JsonResult();
		try {
			System.out.println("mobile:" + mobile + "请求获取验证码！");
			if (!Utils.checkMobile(mobile)) {
				result.setMsg("手机号格式错误！");
				result.setStatus(500);
			}
			if (userService.getCheckCode(mobile)) {
				result.setMsg("请求成功");
				result.setStatus(200);
			} else {
				result.setMsg("请求失败");
				result.setStatus(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
