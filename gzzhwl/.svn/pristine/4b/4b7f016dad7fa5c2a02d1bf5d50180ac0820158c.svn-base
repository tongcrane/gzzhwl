package com.gzzhwl.core.data.extdao;


import java.util.List;
import java.util.Map;

import com.gzzhwl.core.data.model.RealVehicleDriverInfo;

/**
 * 数据访问接口
 *
 */
public interface RealVehicleDriverInfoExtDao {    
    public final static String PREFIX = RealVehicleDriverInfoExtDao.class.getName();
	
    public RealVehicleDriverInfo get(String accountId, String vehicleInfoId, String driverInfoId);

	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(String accountId, String vehicleInfoId, String driverInfoId);
	
	public int delete(String accountId, String vehicleInfoId, String driverInfoId);
	
	/**
	 * 获取正式司机车辆列表
	 * @param vehicleInfoId
	 * @param accountId
	 * @return
	 */
	public <T, K, V> List<T> getRealDriverAndVehicleListByCondition(Map<K, V> params);
	
	public <T, K, V> List<T> getDriverAndVehicleListByAccountId(String accountId);
	
}


