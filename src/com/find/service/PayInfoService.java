package com.find.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.find.dao.PayInfoDao;
import com.find.model.PayInfo;

@Service("payInfoService")
public class PayInfoService {
	@Autowired
	PayInfoDao payInfoDao;
	/**
	 * 免费30天
	 */
	public final static long GIVE_DAY = 1000L * 60L * 60L * 24L * 30L;
	/**
	 * 赠送1天时间给device
	 * 
	 * @param user
	 */
	private void giveDateAdd(PayInfo payInfo) {
		payInfo.setExpirationdate(System.currentTimeMillis() + GIVE_DAY);
		payInfoDao.addPayInfo(payInfo);
	}

	public PayInfo increaseTime(String device_id, long time) {
		PayInfo payInfo = getPayInfo(device_id);
		if (payInfo.getExpirationdate() < System.currentTimeMillis()) {
			payInfo.setExpirationdate(System.currentTimeMillis() + time);
		} else {
			payInfo.setExpirationdate(payInfo.getExpirationdate() + time);
		}
		if (payInfoDao.update(payInfo) > 0) {
			return payInfo;
		}
		return null;
	}

	public PayInfo getPayInfo(String device_id) {
		PayInfo payInfo = payInfoDao.getPayInfoBydeviceId(device_id);
		/**
		 * 免费模式关键
		 */
		if (payInfo == null) {
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
