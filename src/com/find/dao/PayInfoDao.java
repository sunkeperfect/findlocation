package com.find.dao;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.find.model.PayInfo;
import com.find.model.UserInfo;

@Repository("payInfoDao")
public class PayInfoDao extends BaseDao {
	public PayInfo addPayInfo(PayInfo payInfo) {
		payInfo.setId(super.saveAndReturnKey(payInfo).intValue());
		return payInfo;
	}

	/**
	 *
	 */
	public int update(PayInfo payInfo) {
		String sql = "update " + PayInfo.TABLE_NAME
				+ " set expirationdate=? where device_id=? ";
		Object[] args = new Object[] { payInfo.getExpirationdate(),
				payInfo.getDeviceId() };
		int count = getJdbcTemplate().update(sql, args);
		return count;
	}

	/**
	 * 根据device_id获得当前到期时间
	 */
	public PayInfo getPayInfoBydeviceId(String device_id) {
		PayInfo payInfo;
		try {
			RowMapper<PayInfo> rm = ParameterizedBeanPropertyRowMapper
					.newInstance(PayInfo.class);
			payInfo = getJdbcTemplate().queryForObject(
					"select * from " + PayInfo.TABLE_NAME
							+ " where device_id='" + device_id + "'", rm);
		} catch (Exception e) {
			if ((e instanceof IncorrectResultSizeDataAccessException)
					&& ((IncorrectResultSizeDataAccessException) e)
							.getActualSize() == 0) {
				return null;
			}
			e.printStackTrace();
			return null;
		}
		return payInfo;
	}

	/**
	 * 根据username获得当前到期时间
	 */
	public PayInfo getPayInfoByUsername(String username) {
		PayInfo payInfo;
		try {
			RowMapper<PayInfo> rm = ParameterizedBeanPropertyRowMapper
					.newInstance(PayInfo.class);
			payInfo = getJdbcTemplate().queryForObject(
					"select * from " + PayInfo.TABLE_NAME + " where username='"
							+ username, rm);
		} catch (Exception e) {
			if ((e instanceof IncorrectResultSizeDataAccessException)
					&& ((IncorrectResultSizeDataAccessException) e)
							.getActualSize() == 0) {
				e.printStackTrace();
				return null;
			}
			e.printStackTrace();
			return null;
		}
		return payInfo;
	}

	/**
	 * 两个都传 取时间较长的
	 */
	public PayInfo getPayInfo(String username, String device_id) {
		return null;
	}
}
