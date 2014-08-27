package com.find.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.find.dao.DeviceDao;
import com.find.model.Device;
import com.find.util.Utils;

@Service("deviceService")
public class DeviceService {
	@Autowired
	DeviceDao deviceDao;

	public Device add(String device_id) {
		Device device = null;
		if (null == (device = deviceDao.getDeviceById(device_id))) {
			device = new Device();
			device.setDevice_id(device_id);
			device.setDevice_token(createDeviceToken());
			device.setId(deviceDao.add(device));
		}
		return device;
	}

	public String createDeviceToken() {
		String deviceToken = Utils.randomString(6);
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
