package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.ConsignmentInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface ConsignmentInfoDao {    
    public final static String PREFIX = ConsignmentInfoDao.class.getName();
    
	public ConsignmentInfo get(java.lang.String consignId);
	
	public <K, V> Map<K, V> findOne(java.lang.String consignId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(ConsignmentInfo consignmentInfo);
	
	public int update(ConsignmentInfo consignmentInfo);
	
	public int updateSelective(ConsignmentInfo consignmentInfo);
	
	public int delete(java.lang.String consignId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

	public String getConsignByOrder(Map<String, Object> params);

}


