package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.SupplyInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface SupplyInfoDao {    
    public final static String PREFIX = SupplyInfoDao.class.getName();
    
	public SupplyInfo get(java.lang.String supplyId);
	
	public <K, V> Map<K, V> findOne(java.lang.String supplyId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(SupplyInfo supplyInfo);
	
	public int update(SupplyInfo supplyInfo);
	
	public int updateSelective(SupplyInfo supplyInfo);
	
	public int delete(java.lang.String supplyId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);
	
	public String getSupplyName(java.lang.String supplyId);

}


