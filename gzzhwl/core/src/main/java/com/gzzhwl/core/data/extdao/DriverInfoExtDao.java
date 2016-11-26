package com.gzzhwl.core.data.extdao;


import java.util.List;
import java.util.Map;


import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface DriverInfoExtDao {    

	 public final static String PREFIX = DriverInfoExtDao.class.getName();
	
	 public <E, K, V> Page<E> pageDriverAndVehicleList(Map<K, V> params, int current, int pagesize);
	
	public <K, V> Map<K, V> getDriverInfo(String driverInfoId);
	
	public <E, K, V> Page<E> pageDriverList(Map<K, V> params, int current, int pagesize);
	
	public <K, V> Map<K, V> getOtherDriverInfo(Map<K, V> params);
	
	public <T, K, V> List<T> getDriverInfoByCondition(String accountId, String driverInfoId, String idno, String qcNo);

	public <K, V> Map<K, V> queryDriverandVehicleDetail(String driverInfoId);
	
	public <T, K, V> List<T> getDriverListByCondition(Map<K, V> params);
	
	public <T, K, V> List<T> getYSJDriverInfo(String accountId, String driverInfoId, String idno);

	public <T, K, V>List<T> getDriverList(String vehicleInfoId, String accountId);
	
	public <T, K, V>List<T> getDriverWaitList(String plateNumber, String accountId,String idno);

}


