package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.LoadTripInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface LoadTripInfoDao {    
    public final static String PREFIX = LoadTripInfoDao.class.getName();
    
	public LoadTripInfo get(java.lang.String loadId);
	
	public <K, V> Map<K, V> findOne(java.lang.String loadId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(LoadTripInfo loadTripInfo);
	
	public int update(LoadTripInfo loadTripInfo);
	
	public int updateSelective(LoadTripInfo loadTripInfo);
	
	public int delete(java.lang.String loadId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


