package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.VehiclePlusInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface VehiclePlusInfoDao {    
    public final static String PREFIX = VehiclePlusInfoDao.class.getName();
    
	public VehiclePlusInfo get(java.lang.String vehicleInfoId);
	
	public <K, V> Map<K, V> findOne(java.lang.String vehicleInfoId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(VehiclePlusInfo vehiclePlusInfo);
	
	public int update(VehiclePlusInfo vehiclePlusInfo);
	
	public int updateSelective(VehiclePlusInfo vehiclePlusInfo);
	
	public int delete(java.lang.String vehicleInfoId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


