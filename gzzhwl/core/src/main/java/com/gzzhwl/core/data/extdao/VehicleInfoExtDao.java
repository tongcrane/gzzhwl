package com.gzzhwl.core.data.extdao;


import java.util.List;
import java.util.Map;

import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface VehicleInfoExtDao {    
    public final static String PREFIX = VehicleInfoExtDao.class.getName();

    
	public <T, K, V> List<T> getTmpVehicleInfoByTmpDriverInfoId(String tmpDriverInfoId,String accountId);
	
	public <T, K, V> List<T> find(Map<K, V> params);

	/**
	 * 获取待审核司机车辆列表
	 * @param vehicleInfoId
	 * @param accountId
	 * @return
	 */
	public List<Map<String,Object>> getAuditDriverAndVehicleListByVehicleId(String vehicleInfoId,String accountId);
	
	/**
	 * 获取临时司机车辆列表
	 * @param vehicleInfoId
	 * @param accountId
	 * @return
	 */
	public <T, K, V> List<T> getTmpDriverAndVehicleListByCondition(Map<K, V> params);
	
	/**
	 * 获取临时表司机车辆查询明细
	 * @param params
	 * @return
	 */
	public <T, K, V> List<T> getDriverandVehicleCheckDetail(Map<K, V> params);
	
	/**
	 * 获取司机车辆审核列表
	 * @param params
	 * @param current
	 * @param pagesize
	 * @return
	 */
	public <E, K, V> Page<E> getDriverAndVehicleCheckList(Map<K, V> params, int current, int pagesize);
	
	/**
	 * 获取车辆列表
	 * @param params
	 * @param current
	 * @param pagesize
	 * @return
	 */
	public <E, K, V> Page<E> pageVehicleList(Map<K, V> params, int current, int pagesize);
	
}


