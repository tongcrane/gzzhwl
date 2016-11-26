package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.QuotedHis;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface QuotedHisDao {    
    public final static String PREFIX = QuotedHisDao.class.getName();
    
	public QuotedHis get(java.lang.String hisId);
	
	public <K, V> Map<K, V> findOne(java.lang.String hisId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(QuotedHis quotedHis);
	
	public int update(QuotedHis quotedHis);
	
	public int updateSelective(QuotedHis quotedHis);
	
	public int delete(java.lang.String hisId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


