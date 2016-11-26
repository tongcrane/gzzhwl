package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.LoadFeedbackLog;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface LoadFeedbackLogDao {    
    public final static String PREFIX = LoadFeedbackLogDao.class.getName();
    
	public LoadFeedbackLog get(java.lang.String feedbackId);
	
	public <K, V> Map<K, V> findOne(java.lang.String feedbackId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(LoadFeedbackLog loadFeedbackLog);
	
	public int update(LoadFeedbackLog loadFeedbackLog);
	
	public int updateSelective(LoadFeedbackLog loadFeedbackLog);
	
	public int delete(java.lang.String feedbackId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


