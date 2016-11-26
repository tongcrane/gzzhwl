package com.gzzhwl.core.data.extdao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gzzhwl.core.constant.DataSource;
import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.extdao.DriverInfoExtDao;
import com.gzzhwl.core.mybatis.support.DaoSupport;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 * @author mew
 *
 */
@Repository
public class DriverInfoExtDaoImpl implements DriverInfoExtDao {
	@Autowired	
	private DaoSupport dao;
	
	@Override
	public <E, K, V> Page<E> pageDriverAndVehicleList(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".pageDriverAndVehicleList", params, current, pagesize);
	}

	@Override
	public <K, V> Map<K, V> getDriverInfo(String driverInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("driverInfoId", driverInfoId);
		return dao.get(PREFIX + ".getDriverInfo", params);
	}

	@Override
	public <E, K, V> Page<E> pageDriverList(Map<K, V> params, int current, int pagesize) {
		return dao.page(PREFIX + ".pageDriverList", params, current, pagesize);
	}

	@Override
	public <K, V> Map<K, V> getOtherDriverInfo(Map<K, V> params) {
		return dao.get(PREFIX + ".getDriverInfoByCondition", params);
	}

	@Override
	public <K, V> Map<K, V> queryDriverandVehicleDetail(String driverInfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("driverInfoId", driverInfoId);
		return dao.get(PREFIX + ".queryDriverandVehicleDetail", params);
	}

	@Override
	public <T, K, V> List<T> getDriverListByCondition(Map<K, V> params) {
		return dao.find(PREFIX + ".getDriverListByCondition", params);
	}

	@Override
	//内部系统用
	public <T, K, V> List<T> getDriverInfoByCondition(String accountId, String driverInfoId, String idno, String qcNo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId",accountId);
		params.put("driverInfoId", driverInfoId);
		params.put("idno",idno);
		params.put("qcNo",qcNo);
		params.put("source", DataSource.PLATFORM.getCode());
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		return dao.find(PREFIX + ".getDriverInfoByCondition", params);
	}

	@Override
	public <T, K, V> List<T> getYSJDriverInfo(String accountId, String driverInfoId, String idno) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId",accountId);
		params.put("driverInfoId", driverInfoId);
		params.put("idno",idno);
		params.put("source", DataSource.VLORRY.getCode());
		params.put("status", Global.STATUS_ENTRY_NOT_FINISHED.toString());
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		return dao.find(PREFIX + ".getDriverInfoByCondition", params);
	}

	
	@Override
	public <T, K, V> List<T> getDriverList(String vehicleInfoId, String accountId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("vehicleInfoId", vehicleInfoId);
		params.put("accountId", accountId);
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		return dao.find(PREFIX + ".getDriverList", params);
	}

	@Override
	public <T, K, V> List<T> getDriverWaitList(String plateNumber, String accountId, String idno) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("plateNumber", plateNumber);
		params.put("accountId", accountId);
		params.put("idno", idno);
		params.put("status", Global.STATUS_WAIT.toString());
		params.put("isDeleted", Global.ISDEL_NORMAL.toString());
		return dao.find(PREFIX + ".getDriverWaitList", params);
	}
}


