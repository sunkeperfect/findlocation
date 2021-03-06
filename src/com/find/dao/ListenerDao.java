package com.find.dao;

import java.util.List;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.find.model.Listener;

@Repository("listenerDao")
public class ListenerDao extends BaseDao {
	public int add(Listener listener) {
		return super.saveAndReturnKey(listener).intValue();
	}

	public int update(Listener listener) {
		String sql = "update " + Listener.TABLE_NAME
				+ " set username=?,device_id=?,"
				+ "device_token=?,name=? where id=?";
		Object[] args = new Object[] { listener.getUsername(),
				listener.getDevice_id(), listener.getDevice_token(),
				listener.getName(), listener.getId() };
		int count = getJdbcTemplate().update(sql, args);
		return count;
	}

	public Listener getListenerById(int id) {
		Listener listener = null;
		try {
			RowMapper<Listener> rm = ParameterizedBeanPropertyRowMapper
					.newInstance(Listener.class);
			String sql = "select * from " + Listener.TABLE_NAME + " where id="
					+ id;
			listener = getJdbcTemplate().queryForObject(sql, rm);
		} catch (Exception e) {
			if ((e instanceof IncorrectResultSizeDataAccessException)
					&& ((IncorrectResultSizeDataAccessException) e)
							.getActualSize() == 0)
				return null;
			e.printStackTrace();
			return null;
		}
		return listener;
	}

	public Listener getListenerByToken(String device_id, String device_token) {
		Listener listener = null;
		try {
			RowMapper<Listener> rm = ParameterizedBeanPropertyRowMapper
					.newInstance(Listener.class);
			String sql = "select * from " + Listener.TABLE_NAME
					+ " where device_id='" + device_id
					+ "' and device_token = '" + device_token + "'";
			listener = getJdbcTemplate().queryForObject(sql, rm);
		} catch (Exception e) {
			if ((e instanceof IncorrectResultSizeDataAccessException)
					&& ((IncorrectResultSizeDataAccessException) e)
							.getActualSize() == 0)
				return null;
			e.printStackTrace();
			return null;
		}
		return listener;
	}

	public List<Listener> getListenersByUsername(String username) {
		List<Listener> listeners = null;
		try {
			RowMapper<Listener> rm = ParameterizedBeanPropertyRowMapper
					.newInstance(Listener.class);
			String sql = "select * from " + Listener.TABLE_NAME
					+ " where username='" + username + "'";
			listeners = getJdbcTemplate().query(sql, rm);
		} catch (Exception e) {
			if ((e instanceof IncorrectResultSizeDataAccessException)
					&& ((IncorrectResultSizeDataAccessException) e)
							.getActualSize() == 0)
				return null;
			e.printStackTrace();
			return null;
		}
		return listeners;
	}

	public List<Listener> getListenersByDeviceId(String device_id) {
		List<Listener> listeners = null;
		try {
			RowMapper<Listener> rm = ParameterizedBeanPropertyRowMapper
					.newInstance(Listener.class);
			String sql = "select * from " + Listener.TABLE_NAME
					+ " where device_id='" + device_id + "'";
			listeners = getJdbcTemplate().query(sql, rm);
		} catch (Exception e) {
			if ((e instanceof IncorrectResultSizeDataAccessException)
					&& ((IncorrectResultSizeDataAccessException) e)
							.getActualSize() == 0)
				return null;
			e.printStackTrace();
			return null;
		}
		return listeners;
	}

	public int deleteById(int id) {
		String sql = "delete from " + Listener.TABLE_NAME + " where id =?";
		return getJdbcTemplate().update(sql, new Object[] { id });
	}
	/**
	 * UserInfo user = null; try { RowMapper<UserInfo> rm =
	 * ParameterizedBeanPropertyRowMapper .newInstance(UserInfo.class); user =
	 * getJdbcTemplate() .queryForObject(
	 * "select * from user_info where username='" + username + "'", rm); } catch
	 * (Exception e) { if ((e instanceof IncorrectResultSizeDataAccessException)
	 * && ((IncorrectResultSizeDataAccessException) e) .getActualSize() == 0)
	 * return null; e.printStackTrace(); return null; } return user;
	 */

}
