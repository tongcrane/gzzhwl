package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.RealVehiclePlusInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface RealVehiclePlusInfoDao {    
    public final static String PREFIX = RealVehiclePlusInfoDao.class.getName();
    
	public RealVehiclePlusInfo get(java.lang.String vehicleInfoId);
	
	public <K, V> Map<K, V> findOne(java.lang.String vehicleInfoId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(RealVehiclePlusInfo realVehiclePlusInfo);
	
	public int update(RealVehiclePlusInfo realVehiclePlusInfo);
	
	public int updateSelective(RealVehiclePlusInfo realVehiclePlusInfo);
	
	public int delete(java.lang.String vehicleInfoId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


