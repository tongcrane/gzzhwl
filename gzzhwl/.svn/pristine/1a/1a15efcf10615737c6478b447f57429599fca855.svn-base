package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.LoadException;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface LoadExceptionDao {    
    public final static String PREFIX = LoadExceptionDao.class.getName();
    
	public LoadException get(java.lang.String loadId);
	
	public <K, V> Map<K, V> findOne(java.lang.String loadId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(LoadException loadException);
	
	public int update(LoadException loadException);
	
	public int updateSelective(LoadException loadException);
	
	public int delete(java.lang.String loadId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


