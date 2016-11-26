package com.gzzhwl.core.data.dao;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.data.model.OrderDetailInfo;
import com.gzzhwl.core.data.model.OrderSourceInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface OrderSourceInfoDao {
	public final static String PREFIX = OrderSourceInfoDao.class.getName();

	public OrderSourceInfo get(java.lang.String sourceId);

	public <K, V> Map<K, V> findOne(java.lang.String sourceId);

	public <T, K, V> List<T> find(Map<K, V> params);

	public int insert(OrderSourceInfo orderSourceInfo);

	public int update(OrderSourceInfo orderSourceInfo);

	public int updateSelective(OrderSourceInfo orderSourceInfo);

	public int delete(java.lang.String sourceId);

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

	public <E, K, V> Page<E> pageSource(Map<K, V> params, int current, int pagesize);

	public int hasBidBegin(Map<String, Object> params);

	public String getSourceByOrder(Map<String, Object> params);

	public List<String> findSourceClose(Map<String, Object> params);

	public <E, K, V> Page<E> pageSourceHall(Map<K, V> params, int current, int pagesize);

	public OrderDetailInfo getOrderBySource(Map<String, Object> params);

}
