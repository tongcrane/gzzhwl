package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.LoadDriverInfo;
import com.gzzhwl.core.data.dao.LoadDriverInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class LoadDriverInfoDaoImpl implements LoadDriverInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public LoadDriverInfo get(java.lang.String driverInfoId, java.lang.String loadId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("driverInfoId", driverInfoId);
		params.put("loadId", loadId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String driverInfoId, java.lang.String loadId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("driverInfoId", driverInfoId);
		params.put("loadId", loadId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(LoadDriverInfo loadDriverInfo) {
		return dao.insert(PREFIX + ".insert", loadDriverInfo);
	}

	@Override
	public int update(LoadDriverInfo loadDriverInfo) {
		return dao.update(PREFIX + ".update", loadDriverInfo);
	}
	
	@Override
	public int updateSelective(LoadDriverInfo loadDriverInfo) {
		return dao.update(PREFIX + ".updateSelective", loadDriverInfo);
	}

	@Override
	public int delete(java.lang.String driverInfoId, java.lang.String loadId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("driverInfoId", driverInfoId);
		params.put("loadId", loadId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}

	@Override
	public int delLoadDriver(String loadId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loadId", loadId);
		return dao.delete(PREFIX+".delLoadDriver", params);
	}
}


