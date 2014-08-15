package com.find.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.find.dao.ListenerDao;
import com.find.model.Listener;

@Service("listenerService")
public class ListenerService {
	@Autowired
	ListenerDao listenerDao;

	public void addListener(Listener listener) {
		listener.setId(listenerDao.add(listener));
	}

	public int updateName(int id, String name) {
		Listener listener = listenerDao.getListenerById(id);
		listener.setName(name);
		return listenerDao.update(listener);
	}

	public List<Listener> getListeners(String username, String device_id) {
		if (!StringUtils.isEmpty(username) && !username.equals("-1")) {
			return listenerDao.getListenersByUsername(username);
		} else if (!StringUtils.isEmpty(device_id) && !device_id.equals("-1")) {
			return listenerDao.getListenersByDeviceId(device_id);
		}
		return null;
	}

	public boolean delete(int id) {
		return listenerDao.deleteById(id) > 0;
	}
}
