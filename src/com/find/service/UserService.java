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
	 * ����û��Ƿ���� ���� true ������false
	 */

	public boolean checkUserName(String username) {
		return userDao.getUserByName(username) != null;
	}

	/**
	 * ע���ʺ�
	 */
	public void register(UserInfo user) {
		userDao.add(user);
	}
}
