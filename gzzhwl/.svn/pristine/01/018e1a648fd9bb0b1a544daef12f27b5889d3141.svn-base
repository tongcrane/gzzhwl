package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.CustomerBankInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface CustomerBankInfoDao {    
    public final static String PREFIX = CustomerBankInfoDao.class.getName();
    
	public CustomerBankInfo get(java.lang.String customerId);
	
	public <K, V> Map<K, V> findOne(java.lang.String customerId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(CustomerBankInfo customerBankInfo);
	
	public int update(CustomerBankInfo customerBankInfo);
	
	public int updateSelective(CustomerBankInfo customerBankInfo);
	
	public int delete(java.lang.String customerId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


