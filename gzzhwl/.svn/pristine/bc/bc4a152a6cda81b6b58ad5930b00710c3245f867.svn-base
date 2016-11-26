package com.gzzhwl.core.data.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.VehiclePlusInfo;
import com.gzzhwl.core.data.dao.VehiclePlusInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class VehiclePlusInfoDaoImpl implements VehiclePlusInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public VehiclePlusInfo get(java.lang.String vehicleInfoId) {
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
	public int insert(VehiclePlusInfo vehiclePlusInfo) {
		return dao.insert(PREFIX + ".insert", vehiclePlusInfo);
	}

	@Override
	public int update(VehiclePlusInfo vehiclePlusInfo) {
		return dao.update(PREFIX + ".update", vehiclePlusInfo);
	}
	
	@Override
	public int updateSelective(VehiclePlusInfo vehiclePlusInfo) {
		return dao.update(PREFIX + ".updateSelective", vehiclePlusInfo);
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


