package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.RealVehicleDriverInfo;
import com.gzzhwl.core.data.dao.RealVehicleDriverInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class RealVehicleDriverInfoDaoImpl implements RealVehicleDriverInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public RealVehicleDriverInfo get() {
		Map<String, Object> params = new HashMap<String, Object>();
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne() {
		Map<String, Object> params = new HashMap<String, Object>();
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(RealVehicleDriverInfo realVehicleDriverInfo) {
		return dao.insert(PREFIX + ".insert", realVehicleDriverInfo);
	}

	@Override
	public int update(RealVehicleDriverInfo realVehicleDriverInfo) {
		return dao.update(PREFIX + ".update", realVehicleDriverInfo);
	}
	
	@Override
	public int updateSelective(RealVehicleDriverInfo realVehicleDriverInfo) {
		return dao.update(PREFIX + ".updateSelective", realVehicleDriverInfo);
	}

	@Override
	public int delete() {
		Map<String, Object> params = new HashMap<String, Object>();
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}
}


