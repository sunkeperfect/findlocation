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
	 * Éú³ÉuserToken
	 */
	public String createDeviceToken(String device_id) {
		String deviceToken = "";
		String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
		for (int i = 0; i < 6; i++) {
			deviceToken += chars.charAt((int) (Math.random() * 36));
		}
		return deviceToken;
	}
	/**
	 * 
	 */
}
