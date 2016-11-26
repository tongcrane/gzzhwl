package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.OrderLineInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface OrderLineInfoDao {    
    public final static String PREFIX = OrderLineInfoDao.class.getName();
    
	public OrderLineInfo get(java.lang.String infoId);
	
	public <K, V> Map<K, V> findOne(java.lang.String infoId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(OrderLineInfo orderLineInfo);
	
	public int update(OrderLineInfo orderLineInfo);
	
	public int updateSelective(OrderLineInfo orderLineInfo);
	
	public int delete(java.lang.String infoId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


