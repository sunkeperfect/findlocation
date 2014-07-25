package com.find.dao;

import org.springframework.stereotype.Repository;

import com.find.model.UserInfo;

@Repository("userDao")
public class UserDao extends BaseDao {
	public UserInfo add(UserInfo userinfo) {
		userinfo.setId(super.saveAndReturnKey(userinfo).intValue());
		return userinfo;
	}
}
