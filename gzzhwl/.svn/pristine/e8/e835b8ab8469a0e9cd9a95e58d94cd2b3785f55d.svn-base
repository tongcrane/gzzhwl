package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.RealDriverInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface RealDriverInfoDao {    
    public final static String PREFIX = RealDriverInfoDao.class.getName();
    
	public RealDriverInfo get(java.lang.String driverInfoId);
	
	public <K, V> Map<K, V> findOne(java.lang.String driverInfoId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(RealDriverInfo realDriverInfo);
	
	public int update(RealDriverInfo realDriverInfo);
	
	public int updateSelective(RealDriverInfo realDriverInfo);
	
	public int delete(java.lang.String driverInfoId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


