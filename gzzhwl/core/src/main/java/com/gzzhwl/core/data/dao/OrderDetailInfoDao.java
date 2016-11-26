package com.gzzhwl.core.data.dao;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.data.model.OrderDetailInfo;
import com.gzzhwl.core.page.Page;

/**
 * 数据访问接口
 *
 */
public interface OrderDetailInfoDao {
	public final static String PREFIX = OrderDetailInfoDao.class.getName();

	public OrderDetailInfo get(java.lang.String orderId);

	public OrderDetailInfo getByOrderNo(java.lang.String orderNo);

	public <K, V> Map<K, V> findOne(java.lang.String orderId);

	public <T, K, V> List<T> find(Map<K, V> params);

	public int insert(OrderDetailInfo orderDetailInfo);

	public int update(OrderDetailInfo orderDetailInfo);

	public int updateSelective(OrderDetailInfo orderDetailInfo);

	public int delete(java.lang.String orderId);

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

}
