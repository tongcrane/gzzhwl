package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.RemarkInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface RemarkInfoDao {    
    public final static String PREFIX = RemarkInfoDao.class.getName();
    
	public RemarkInfo get(java.lang.Long pid, java.lang.String remarkId);
	
	public <K, V> Map<K, V> findOne(java.lang.Long pid, java.lang.String remarkId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(RemarkInfo remarkInfo);
	
	public int update(RemarkInfo remarkInfo);
	
	public int updateSelective(RemarkInfo remarkInfo);
	
	public int delete(java.lang.Long pid , java.lang.String remarkId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


