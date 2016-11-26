package com.gzzhwl.core.data.extdao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.data.extdao.RealVehicleDriverInfoExtDao;
import com.gzzhwl.core.data.model.RealVehicleDriverInfo;
import com.gzzhwl.core.mybatis.support.DaoSupport;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class RealVehicleDriverInfoExtDaoImpl implements RealVehicleDriverInfoExtDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public RealVehicleDriverInfo get(String accountId, String vehicleInfoId, String driverInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("vehicleInfoId", vehicleInfoId);
		params.put("driverInfoId", driverInfoId);
		return dao.get(PREFIX + ".get", params);
	}


	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(String accountId, String vehicleInfoId, String driverInfoId) {
		RealVehicleDriverInfo realVehicleDriverInfo = new RealVehicleDriverInfo();
		realVehicleDriverInfo.setAccountId(accountId);
		realVehicleDriverInfo.setDriverInfoId(driverInfoId);
		realVehicleDriverInfo.setVehicleInfoId(vehicleInfoId);
		return dao.insert(PREFIX + ".insert", realVehicleDriverInfo);
	}

	@Override
	public int delete(String accountId, String vehicleInfoId, String driverInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("vehicleInfoId", vehicleInfoId);
		params.put("driverInfoId", driverInfoId);
		return dao.delete(PREFIX + ".delete", params);
	}


	@Override
	public <T, K, V> List<T> getRealDriverAndVehicleListByCondition(Map<K, V> params) {
		
		return dao.find(PREFIX + ".getRealDriverAndVehicleByCondition", params);
	}


	@Override
	public <T, K, V> List<T> getDriverAndVehicleListByAccountId(String accountId) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		
		return dao.find(PREFIX + ".getDriverAndVehicleList", params);
	}
}


