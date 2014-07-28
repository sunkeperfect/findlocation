package com.find.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.find.dao.UserDao;
import com.find.model.UserInfo;

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
		userDao.add(user);
	}
}
