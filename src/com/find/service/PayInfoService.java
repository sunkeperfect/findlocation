package com.find.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.find.dao.PayInfoDao;
import com.find.model.PayInfo;

@Service("payInfoService")
public class PayInfoService {
	@Autowired
	PayInfoDao payInfoDao;

	public final static long GIVE_DAY = 1000 * 60 * 60 * 24 * 7;

	/**
	 * 赠送 7天时间给device
	 * 
	 * @param user
	 */
	private void giveDateAdd(PayInfo payInfo) {
		payInfo.setDate(System.currentTimeMillis() + GIVE_DAY);
		payInfoDao.addPayInfo(payInfo);
	}

	public boolean increaseTime(String device_id, long time) {
		PayInfo payInfo = getPayInfo(device_id);
		payInfo.setDate(payInfo.getDate() + time);
		return payInfoDao.update(payInfo) > 0;
	}

	public PayInfo getPayInfo(String device_id) {
		PayInfo payInfo = payInfoDao.getPayInfoBydeviceId(device_id);
		if (payInfo == null) {
			/**
			 * 首次使用 添加
			 */
			payInfo = new PayInfo();
			payInfo.setDeviceId(device_id);
			giveDateAdd(payInfo);
		}
		return payInfo;
	}

	public PayInfo getPayInfoByUsername(String username) {
		return payInfoDao.getPayInfoByUsername(username);
	}
}
