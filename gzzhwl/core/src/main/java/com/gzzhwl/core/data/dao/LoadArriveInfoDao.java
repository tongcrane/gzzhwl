package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.LoadArriveInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface LoadArriveInfoDao {    
    public final static String PREFIX = LoadArriveInfoDao.class.getName();
    
	public LoadArriveInfo get(java.lang.String loadId);
	
	public <K, V> Map<K, V> findOne(java.lang.String loadId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(LoadArriveInfo loadArriveInfo);
	
	public int update(LoadArriveInfo loadArriveInfo);
	
	public int updateSelective(LoadArriveInfo loadArriveInfo);
	
	public int delete(java.lang.String loadId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


