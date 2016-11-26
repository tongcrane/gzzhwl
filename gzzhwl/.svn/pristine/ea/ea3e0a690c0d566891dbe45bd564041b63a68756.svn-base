package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.LoadHis;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface LoadHisDao {    
    public final static String PREFIX = LoadHisDao.class.getName();
    
	public LoadHis get(java.lang.String hisId);
	
	public <K, V> Map<K, V> findOne(java.lang.String hisId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(LoadHis loadHis);
	
	public int update(LoadHis loadHis);
	
	public int updateSelective(LoadHis loadHis);
	
	public int delete(java.lang.String hisId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


