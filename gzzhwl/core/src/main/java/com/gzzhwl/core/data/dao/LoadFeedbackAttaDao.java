package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.LoadFeedbackAtta;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface LoadFeedbackAttaDao {    
    public final static String PREFIX = LoadFeedbackAttaDao.class.getName();
    
	public LoadFeedbackAtta get(java.lang.String attaId);
	
	public <K, V> Map<K, V> findOne(java.lang.String attaId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(LoadFeedbackAtta loadFeedbackAtta);
	
	public int update(LoadFeedbackAtta loadFeedbackAtta);
	
	public int updateSelective(LoadFeedbackAtta loadFeedbackAtta);
	
	public int delete(java.lang.String attaId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


