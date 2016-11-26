package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.SourceQuotedInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface SourceQuotedInfoDao {    
    public final static String PREFIX = SourceQuotedInfoDao.class.getName();
    
	public SourceQuotedInfo get(java.lang.String quotedId, java.lang.String sourceId);
	
	public <K, V> Map<K, V> findOne(java.lang.String quotedId, java.lang.String sourceId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(SourceQuotedInfo sourceQuotedInfo);
	
	public int update(SourceQuotedInfo sourceQuotedInfo);
	
	public int updateSelective(SourceQuotedInfo sourceQuotedInfo);
	
	public int delete(java.lang.String quotedId , java.lang.String sourceId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


