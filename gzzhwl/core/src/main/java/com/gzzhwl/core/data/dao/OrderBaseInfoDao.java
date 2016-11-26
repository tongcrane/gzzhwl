package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.OrderBaseInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface OrderBaseInfoDao {    
    public final static String PREFIX = OrderBaseInfoDao.class.getName();
    
	public OrderBaseInfo get(java.lang.String infoId);
	
	public <K, V> Map<K, V> findOne(java.lang.String infoId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(OrderBaseInfo orderBaseInfo);
	
	public int update(OrderBaseInfo orderBaseInfo);
	
	public int updateSelective(OrderBaseInfo orderBaseInfo);
	
	public int delete(java.lang.String infoId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


