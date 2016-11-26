package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.DriverInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface DriverInfoDao {    
    public final static String PREFIX = DriverInfoDao.class.getName();
    
	public DriverInfo get(java.lang.String driverInfoId);
	
	public <K, V> Map<K, V> findOne(java.lang.String driverInfoId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(DriverInfo driverInfo);
	
	public int update(DriverInfo driverInfo);
	
	public int updateSelective(DriverInfo driverInfo);
	
	public int delete(java.lang.String driverInfoId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


