package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.LoadDriverInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface LoadDriverInfoDao {    
    public final static String PREFIX = LoadDriverInfoDao.class.getName();
    
	public LoadDriverInfo get(java.lang.String driverInfoId, java.lang.String loadId);
	
	public <K, V> Map<K, V> findOne(java.lang.String driverInfoId, java.lang.String loadId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(LoadDriverInfo loadDriverInfo);
	
	public int update(LoadDriverInfo loadDriverInfo);
	
	public int updateSelective(LoadDriverInfo loadDriverInfo);
	
	public int delete(java.lang.String driverInfoId , java.lang.String loadId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);
	
	public int delLoadDriver(String loadId);

}


