package com.gzzhwl.core.data.dao;


import java.util.Map;
import java.util.List;
import com.gzzhwl.core.data.model.OrderHis;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface OrderHisDao {    
    public final static String PREFIX = OrderHisDao.class.getName();
    
	public OrderHis get(java.lang.String hisId);
	
	public <K, V> Map<K, V> findOne(java.lang.String hisId);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(OrderHis orderHis);
	
	public int update(OrderHis orderHis);
	
	public int updateSelective(OrderHis orderHis);
	
	public int delete(java.lang.String hisId );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}


