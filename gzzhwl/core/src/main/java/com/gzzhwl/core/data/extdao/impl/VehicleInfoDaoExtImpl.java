package com.gzzhwl.core.data.extdao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.extdao.VehicleInfoExtDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 * @param <V>
 *
 */
@Repository
public class VehicleInfoDaoExtImpl implements VehicleInfoExtDao {
	@Autowired	
	private DaoSupport dao;

	@Override
	public <T, K, V> List<T> getTmpVehicleInfoByTmpDriverInfoId(String tmpDriverInfoId, String accountId) {
		
		Map<String,Object> paraMap = new HashMap<>();
		paraMap.put("driverInfoId", tmpDriverInfoId);
		paraMap.put("accountId", accountId);
		
		return dao.find(PREFIX + ".getTmpVehicleByTmpDriverId", paraMap);
	}
	
	
	@Override
	public <T, K, V> List<T> find(Map<K, V> params) {
		return dao.find(PREFIX + ".find", params);
	}
	

	@Override
	public List<Map<String, Object>> getAuditDriverAndVehicleListByVehicleId(String vehicleInfoId, String accountId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("vehicleInfoId",vehicleInfoId);
		params.put("accountId", accountId);
		params.put("status", Global.STATUS_WAIT.toString());
		
		return dao.find(PREFIX + ".getDriverAndVehicleByCondition", params);
	}


	@Override
	public <T, K, V> List<T> getTmpDriverAndVehicleListByCondition(Map<K, V> params) {
		
		return dao.find(PREFIX + ".getDriverAndVehicleByCondition", params);
	}


	@Override
	public <T, K, V> List<T> getDriverandVehicleCheckDetail(Map<K, V> params) {
		
		return dao.find(PREFIX + ".getDriverandVehicleCheckDetail", params);
	}
	
	@Override
	public <E, K, V> Page<E> getDriverAndVehicleCheckList(Map<K, V> params, int current, int pagesize) {
		
		return dao.page(PREFIX + ".getDriverAndVehicleCheckList", params, current, pagesize);
	}


	@Override
	public <E, K, V> Page<E> pageVehicleList(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".pageVehicleList", params, current, pagesize);
	}
	
}


