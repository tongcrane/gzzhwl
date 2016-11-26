package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.RealVehicleInfo;
import com.gzzhwl.core.data.dao.RealVehicleInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class RealVehicleInfoDaoImpl implements RealVehicleInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public RealVehicleInfo get(java.lang.String vehicleInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("vehicleInfoId", vehicleInfoId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <K, V> Map<K, V> findOne(java.lang.String vehicleInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("vehicleInfoId", vehicleInfoId);
		return dao.get(PREFIX + ".findOne", params);
	}

	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(RealVehicleInfo realVehicleInfo) {
		return dao.insert(PREFIX + ".insert", realVehicleInfo);
	}

	@Override
	public int update(RealVehicleInfo realVehicleInfo) {
		return dao.update(PREFIX + ".update", realVehicleInfo);
	}
	
	@Override
	public int updateSelective(RealVehicleInfo realVehicleInfo) {
		return dao.update(PREFIX + ".updateSelective", realVehicleInfo);
	}

	@Override
	public int delete(java.lang.String vehicleInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("vehicleInfoId", vehicleInfoId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
	@Override
	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".page", params, current, pagesize);
	}
}


