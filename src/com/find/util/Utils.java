package com.find.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	public static String randomString(int count) {
		String str = "";
		String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
		for (int i = 0; i < count; i++) {
			str += chars.charAt((int) (Math.random() * 36));
		}
		return str;
	}

	// 4位验证码
	public static String randomCode(int count) {
		String str = "";
		String chars = "1234567890";
		for (int i = 0; i < count; i++) {
			str += chars.charAt((int) (Math.random() * 10));
		}
		return str;
	}

	// 简单判断手机号码正确性
	public static boolean checkMobile(String phone) {
		Pattern pattern = Pattern.compile("1\\d{10}");
		Matcher matcher = pattern.matcher(phone);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
}
