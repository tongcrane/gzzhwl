package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.OrderReceiverInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface OrderReceiverInfoDao {    
    public final static String PREFIX = OrderReceiverInfoDao.class.getName();
    
	public OrderReceiverInfo get(java.lang.String infoId);
	
	public <K, V> Map<K, V> findOne(java.lang.String infoId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(OrderReceiverInfo orderReceiverInfo);
	
	public int update(OrderReceiverInfo orderReceiverInfo);
	
	public int updateSelective(OrderReceiverInfo orderReceiverInfo);
	
	public int delete(java.lang.String infoId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


