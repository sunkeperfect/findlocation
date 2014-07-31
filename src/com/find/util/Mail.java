package com.find.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;


public class Mail {
	public static void sentEmail(String content, String toAddress) {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.163.com");
		email.setAuthentication("sunkeperfect@163.com", "sun7026958");
		email.setCharset("UTF-8");
		try {
			email.addTo(toAddress);// 要发送的地址
			email.setFrom("sunkeperfect@163.com");// 必须和Authentication使用的用户相同，否则失败
			email.setSubject("FindYou【密码找回】");// 要发送的主题
			email.setMsg("您的新密码为：" + content);// 要发送的内容
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();

		}
	}
}
