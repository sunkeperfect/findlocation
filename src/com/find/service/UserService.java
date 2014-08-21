package com.find.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.find.dao.UserDao;
import com.find.model.UserInfo;
import com.find.util.Mail;
import com.find.util.Utils;

@Service("userService")
public class UserService {
	private static final String String = null;
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
		if (user != null) {
			user.setUser_token(Utils.randomString(16));
			userDao.update(user);
		}
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
				Mail.sentEmail(str, user.getUsername());
				return true;
			}
		}
		return false;
	}

	/**
	 * 发送短信至手机
	 * 
	 * @return true 发送成功 false 发送失败
	 */
	public boolean getCheckCode(String mobile) {
		boolean connect = false;
		try {
			String corpId = "YXS02649";
			String pwd = "654321";
			String checkCode = Utils.randomCode(4);
			String msg = "注册的验证码:" + checkCode + "。2分钟内有效！";
			String url = "http://www.106551.com/ws/Send.aspx?";
			String urlBody = "CorpID=" + URLEncoder.encode(corpId, "gbk")
					+ "&Pwd=" + URLEncoder.encode(pwd, "gbk") + "&Mobile="
					+ URLEncoder.encode(mobile, "gbk") + "&Content="
					+ URLEncoder.encode(msg, "gbk");
			url += urlBody;
			System.out.println("url:" + url);
			URL u = new URL(url);
			try {
				System.out.println();
				HttpURLConnection uConnection = (HttpURLConnection) u
						.openConnection();
				try {
					uConnection.connect();
					System.out.println(uConnection.getResponseCode());
					connect = true;
					InputStream is = uConnection.getInputStream();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(is));
					StringBuilder sb = new StringBuilder();
					while (br.read() != -1) {
						sb.append(br.readLine());
					}
					String content = new String(sb);
					// content = new String(content.getBytes("UTF8"), "UTF8");
					System.out.println("success:" + content);
					br.close();
				} catch (Exception e) {
					connect = false;
					e.printStackTrace();
					System.out.println("connect failed");
				}

			} catch (IOException e) {
				System.out.println("build failed");
				e.printStackTrace();
			}

		} catch (MalformedURLException e) {
			System.out.println("build url failed");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			System.out.println("build url failed");
			e1.printStackTrace();
		}
		return connect;
	}
}
