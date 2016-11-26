package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.CustomerInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface CustomerInfoDao {    
    public final static String PREFIX = CustomerInfoDao.class.getName();
    
	public CustomerInfo get(java.lang.String customerId);
	
	public <K, V> Map<K, V> findOne(java.lang.String customerId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(CustomerInfo customerInfo);
	
	public int update(CustomerInfo customerInfo);
	
	public int updateSelective(CustomerInfo customerInfo);
	
	public int delete(java.lang.String customerId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


