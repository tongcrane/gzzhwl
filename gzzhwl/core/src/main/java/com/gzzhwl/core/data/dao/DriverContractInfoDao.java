package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.DriverContractInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface DriverContractInfoDao {    
    public final static String PREFIX = DriverContractInfoDao.class.getName();
    
	public DriverContractInfo get(java.lang.String contractId);
	
	public <K, V> Map<K, V> findOne(java.lang.String contractId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(DriverContractInfo driverContractInfo);
	
	public int update(DriverContractInfo driverContractInfo);
	
	public int updateSelective(DriverContractInfo driverContractInfo);
	
	public int delete(java.lang.String contractId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

	public String getContractByLoad(Map<String, Object> params);

}


