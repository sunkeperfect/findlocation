package com.find.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.find.dao.VersionInfoDao;
import com.find.model.VersionInfo;

@Service("versionInfoService")
public class VersionInfoService {
	@Autowired
	VersionInfoDao versionInfoDao;

	public VersionInfo getVersion() {
		return versionInfoDao.getVersion();
	}
}
