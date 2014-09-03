package com.find.dao;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.find.model.VersionInfo;

@Repository("versionInfoDao")
public class VersionInfoDao extends BaseDao {
	public VersionInfo getVersion() {
		VersionInfo versionInfo = null;
		try {
			RowMapper<VersionInfo> rm = ParameterizedBeanPropertyRowMapper
					.newInstance(VersionInfo.class);
			String sql = "select * from " + VersionInfo.TABLE_NAME
					+ " order by id desc limit 1  ";
			versionInfo = getJdbcTemplate().queryForObject(sql, rm);
		} catch (Exception e) {
			if ((e instanceof IncorrectResultSizeDataAccessException)
					&& ((IncorrectResultSizeDataAccessException) e)
							.getActualSize() == 0)
				return null;
			e.printStackTrace();
			return null;
		}
		return versionInfo;
	}
}
