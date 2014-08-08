package com.find.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.find.dao.ListenerDao;
import com.find.model.Listener;

@Service("listenerService")
public class ListenerService {
	@Autowired
	ListenerDao listenerDao;

	public void addListener(Listener listener) {
		listener.setId(listenerDao.add(listener));
	}
}
