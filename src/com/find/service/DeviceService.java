package com.find.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.find.dao.DeviceDao;
import com.find.model.Device;

@Service("deviceService")
public class DeviceService {
	@Autowired
	DeviceDao deviceDao;

	public Device add(String device_id) {
		Device device = new Device();
		device.setDevice_id(device_id);
		device.setDevice_token(createDeviceToken());
		device.setId(deviceDao.add(device));
		return device;
	}

	public String createDeviceToken() {
		String deviceToken = "";
		String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
		for (int i = 0; i < 6; i++) {
			deviceToken += chars.charAt((int) (Math.random() * 36));
		}
		// 需要去数据库验证唯一性
		if (checkToken(deviceToken)) {
			System.out.println("token already exist,recreate token!");
			return createDeviceToken();
		}
		System.out.println("create token success!");
		return deviceToken;
	}

	public boolean checkToken(String deviceToken) {
		return deviceDao.getDeviceByToken(deviceToken) != null;
	}
}
