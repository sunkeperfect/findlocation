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
			email.addTo(toAddress);// Ҫ���͵ĵ�ַ
			email.setFrom("sunkeperfect@163.com");// �����Authenticationʹ�õ��û���ͬ������ʧ��
			email.setSubject("FindYou�������һء�");// Ҫ���͵�����
			email.setMsg("����������Ϊ��" + content);// Ҫ���͵�����
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();

		}
	}
}
