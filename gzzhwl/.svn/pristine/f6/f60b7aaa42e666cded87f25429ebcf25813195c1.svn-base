package com.gzzhwl.core.data.extdao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.gzzhwl.core.data.model.VehicleDriverInfo;
import com.gzzhwl.core.data.extdao.VehicleDriverExtInfoDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class VehicleDriverExtInfoDaoImpl implements VehicleDriverExtInfoDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public VehicleDriverInfo get(String accountId, String vehicleInfoId, String driverInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("vehicleInfoId", vehicleInfoId);
		params.put("driverInfoId", driverInfoId);
		return dao.get(PREFIX + ".get", params);
	}
	
	@Override
	public <T, K, V> List<T> find(String accountId, String vehicleInfoId, String driverInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("vehicleInfoId", vehicleInfoId);
		params.put("driverInfoId", driverInfoId);
		return dao.find(PREFIX + ".find", params);
	}

	@Override
	public int insert(String accountId, String vehicleInfoId, String driverInfoId) {
		VehicleDriverInfo info = new VehicleDriverInfo();
		info.setAccountId(accountId);
		info.setDriverInfoId(driverInfoId);
		info.setVehicleInfoId(vehicleInfoId);
		return dao.insert(PREFIX + ".insert", info);
	}


	@Override
	public int delete(String accountId, String vehicleInfoId, String driverInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("vehicleInfoId", vehicleInfoId);
		params.put("driverInfoId", driverInfoId);
		return dao.delete(PREFIX + ".delete", params);
	}
	
}


