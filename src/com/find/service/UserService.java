package com.find.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.find.dao.UserDao;
import com.find.model.UserInfo;
import com.find.util.Mail;
import com.find.util.Utils;

@Service("userService")
public class UserService {
	@Autowired
	UserDao userDao;

	public void add(UserInfo user) {
		userDao.add(user);
	}

	/**
	 * 检查用户是否存在 存在 true 不存在false
	 */

	public boolean checkUserName(String username) {
		return userDao.getUserByName(username) != null;
	}

	/**
	 * 注册帐号
	 */
	public void register(UserInfo user) {
		user.setUser_token(Utils.randomString(16));
		userDao.add(user);
	}

	/**
	 * 登录
	 */
	public UserInfo login(String username, String password) {
		UserInfo user = userDao.login(username, password);
		user.setUser_token(Utils.randomString(16));
		userDao.update(user);
		return user;
	}

	/**
	 * 修改密码
	 * 
	 * @return true 修改成功 false 失败
	 */
	public boolean modifyPassword(String username, String password) {
		UserInfo user = userDao.getUserByName(username);
		user.setPassword(password);
		return userDao.update(user) > 0;
	}

	/**
	 * 取回密码
	 */
	public boolean retrievePassword(String username) {
		UserInfo user = userDao.getUserByName(username);
		if (user != null) {
			// 生成新密码
			String str = Utils.randomString(6);
			if (modifyPassword(username, str)) { // md5加密后存储
				Mail.sentEmail(str, user.getEmail());
				return true;
			}
		}
		return false;
	}
}
