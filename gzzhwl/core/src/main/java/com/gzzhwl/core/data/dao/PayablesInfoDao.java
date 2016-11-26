package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.PayablesInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface PayablesInfoDao {    
    public final static String PREFIX = PayablesInfoDao.class.getName();
    
	public PayablesInfo get(java.lang.String contractId);
	
	public <K, V> Map<K, V> findOne(java.lang.String contractId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(PayablesInfo payablesInfo);
	
	public int update(PayablesInfo payablesInfo);
	
	public int updateSelective(PayablesInfo payablesInfo);
	
	public int delete(java.lang.String contractId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


