package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.QuotedPlusInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface QuotedPlusInfoDao {    
    public final static String PREFIX = QuotedPlusInfoDao.class.getName();
    
	public QuotedPlusInfo get(java.lang.String driverInfoId, java.lang.String quotedId, java.lang.String vehicleInfoId);
	
	public <K, V> Map<K, V> findOne(java.lang.String driverInfoId, java.lang.String quotedId, java.lang.String vehicleInfoId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(QuotedPlusInfo quotedPlusInfo);
	
	public int update(QuotedPlusInfo quotedPlusInfo);
	
	public int updateSelective(QuotedPlusInfo quotedPlusInfo);
	
	public int delete(java.lang.String driverInfoId , java.lang.String quotedId , java.lang.String vehicleInfoId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


