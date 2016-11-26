package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.VehicleInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface VehicleInfoDao {    
    public final static String PREFIX = VehicleInfoDao.class.getName();
    
	public VehicleInfo get(java.lang.String vehicleInfoId);
	
	public <K, V> Map<K, V> findOne(java.lang.String vehicleInfoId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(VehicleInfo vehicleInfo);
	
	public int update(VehicleInfo vehicleInfo);
	
	public int updateSelective(VehicleInfo vehicleInfo);
	
	public int delete(java.lang.String vehicleInfoId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


