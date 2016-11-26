package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.SourceHis;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface SourceHisDao {    
    public final static String PREFIX = SourceHisDao.class.getName();
    
	public SourceHis get(java.lang.String hisId);
	
	public <K, V> Map<K, V> findOne(java.lang.String hisId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(SourceHis sourceHis);
	
	public int update(SourceHis sourceHis);
	
	public int updateSelective(SourceHis sourceHis);
	
	public int delete(java.lang.String hisId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


