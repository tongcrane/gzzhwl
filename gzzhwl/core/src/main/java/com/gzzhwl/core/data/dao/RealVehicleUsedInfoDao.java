package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.RealVehicleUsedInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface RealVehicleUsedInfoDao {    
    public final static String PREFIX = RealVehicleUsedInfoDao.class.getName();
    
	public RealVehicleUsedInfo get(java.lang.String vehicleInfoId);
	
	public <K, V> Map<K, V> findOne(java.lang.String vehicleInfoId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(RealVehicleUsedInfo realVehicleUsedInfo);
	
	public int update(RealVehicleUsedInfo realVehicleUsedInfo);
	
	public int updateSelective(RealVehicleUsedInfo realVehicleUsedInfo);
	
	public int delete(java.lang.String vehicleInfoId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);
	
	public long getIsStatus(java.lang.String vehicleInfoId, java.lang.String useStatus);

	public RealVehicleUsedInfo getWithDepart(String vehicleInfoId);

}


