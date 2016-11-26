package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.OrderSenderInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface OrderSenderInfoDao {    
    public final static String PREFIX = OrderSenderInfoDao.class.getName();
    
	public OrderSenderInfo get(java.lang.String infoId);
	
	public <K, V> Map<K, V> findOne(java.lang.String infoId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(OrderSenderInfo orderSenderInfo);
	
	public int update(OrderSenderInfo orderSenderInfo);
	
	public int updateSelective(OrderSenderInfo orderSenderInfo);
	
	public int delete(java.lang.String infoId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


