package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.ReceivablesInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface ReceivablesInfoDao {    
    public final static String PREFIX = ReceivablesInfoDao.class.getName();
    
	public ReceivablesInfo get(java.lang.String consignId);
	
	public <K, V> Map<K, V> findOne(java.lang.String consignId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(ReceivablesInfo receivablesInfo);
	
	public int update(ReceivablesInfo receivablesInfo);
	
	public int updateSelective(ReceivablesInfo receivablesInfo);
	
	public int delete(java.lang.String consignId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


