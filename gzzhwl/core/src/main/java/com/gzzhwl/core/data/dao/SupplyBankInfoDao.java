package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.SupplyBankInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface SupplyBankInfoDao {    
    public final static String PREFIX = SupplyBankInfoDao.class.getName();
    
	public SupplyBankInfo get(java.lang.String supplyId);
	
	public <K, V> Map<K, V> findOne(java.lang.String supplyId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(SupplyBankInfo supplyBankInfo);
	
	public int update(SupplyBankInfo supplyBankInfo);
	
	public int updateSelective(SupplyBankInfo supplyBankInfo);
	
	public int delete(java.lang.String supplyId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


