package com.find.dao;

import org.springframework.stereotype.Repository;

import com.find.model.Listener;
@Repository("listenerDao")
public class ListenerDao extends BaseDao {
	public int add(Listener listener) {
		return super.saveAndReturnKey(listener).intValue();
	}
}
