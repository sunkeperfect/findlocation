package com.find.dao;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.find.model.UserInfo;

@Repository("userDao")
public class UserDao extends BaseDao {
	public void add(UserInfo userinfo) {
		userinfo.setId(super.saveAndReturnKey(userinfo).intValue());
	}

	public UserInfo getUserByName(String username) {
		UserInfo user = null;
		try {
			RowMapper<UserInfo> rm = ParameterizedBeanPropertyRowMapper
					.newInstance(UserInfo.class);
			user = getJdbcTemplate()
					.queryForObject(
							"select * from user_info where username='"
									+ username + "'", rm);
		} catch (Exception e) {
			if ((e instanceof IncorrectResultSizeDataAccessException)
					&& ((IncorrectResultSizeDataAccessException) e)
							.getActualSize() == 0)
				return null;
			e.printStackTrace();
			return null;
		}
		return user;
	}

	public UserInfo login(String username, String password) {
		UserInfo user;
		try {
			RowMapper<UserInfo> rm = ParameterizedBeanPropertyRowMapper
					.newInstance(UserInfo.class);
			user = getJdbcTemplate().queryForObject(
					"select * from user_info where username='" + username
							+ "' and password ='" + password + "'", rm);
		} catch (Exception e) {
			if ((e instanceof IncorrectResultSizeDataAccessException)
					&& ((IncorrectResultSizeDataAccessException) e)
							.getActualSize() == 0)
				return null;
			e.printStackTrace();
			return null;
		}
		return user;
	}

	public int update(UserInfo user) {
		String sql = "update " + UserInfo.TABLE_NAME
				+ " set username=?,password=?,email=?,user_token=? where id=?";
		Object[] args = new Object[] { user.getUsername(), user.getPassword(),
				user.getEmail(), user.getUser_token(), user.getId() };
		int count = getJdbcTemplate().update(sql, args);
		return count;
	}
}
