package com.find.util;

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
}
