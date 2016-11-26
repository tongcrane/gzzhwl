package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.DriverBankInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface DriverBankInfoDao {    
    public final static String PREFIX = DriverBankInfoDao.class.getName();
    
	public DriverBankInfo get(java.lang.String infoId);
	
	public <K, V> Map<K, V> findOne(java.lang.String infoId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(DriverBankInfo driverBankInfo);
	
	public int update(DriverBankInfo driverBankInfo);
	
	public int updateSelective(DriverBankInfo driverBankInfo);
	
	public int delete(java.lang.String infoId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

	public DriverBankInfo findByDriver(String driverInfoId);

}


