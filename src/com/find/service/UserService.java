package com.find.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.find.dao.UserDao;
import com.find.model.UserInfo;
import com.find.util.MD5;
import com.find.util.Mail;

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

	/**
	 * ��¼
	 */
	public UserInfo login(String username, String password) {
		return userDao.login(username, password);
	}

	/**
	 * �޸�����
	 * 
	 * @return true �޸ĳɹ� false ʧ��
	 */
	public boolean modifyPassword(String username, String password) {
		UserInfo user = userDao.getUserByName(username);
		user.setPassword(password);
		return userDao.update(user) > 0;
	}

	/**
	 * ȡ������
	 */
	public boolean retrievePassword(String username) {
		UserInfo user = userDao.getUserByName(username);
		if (user != null) {
			// ����������
			String str = "";
			String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
			for (int i = 0; i < 6; i++) {
				str += chars.charAt((int) (Math.random() * 36));
			}
			if (modifyPassword(username, str)) { // md5���ܺ�洢
				Mail.sentEmail(str, user.getEmail());
				return true;
			}
		}
		return false;
	}
}
