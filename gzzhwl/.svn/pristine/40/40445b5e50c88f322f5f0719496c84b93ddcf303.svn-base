package com.gzzhwl.core.data.extdao;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.page.Page;

public interface OrderInfoExtDao {
	public final static String PREFIX = OrderInfoExtDao.class.getName();

	public <E, K, V> Page<E> pageOrderList(String keyWord, String sort, int currentPage, int pageSizes);

	public <E, K, V> Page<E> pageOrders(Map<K, V> params, int currentPage, int pageSizes);

	// 获取订单退回申请列表
	public <E, K, V> Page<E> pageOrderReturnList(Map<K, V> params, int currentPage, int pageSizes);

	public <K, V> Map<K, V> getLoadOrderInfo(String orderId);

	public <E> E getOrderInfoByQuoted(String quotedId);
	
	//查询在途列表
	public <E, K, V> Page<E> pageTransitList(Map<K, V> params, int currentPage, int pageSizes);
	
	//查询在途车辆详情
	public <K, V> Map<K, V>  getTransitOrderDetail(Map<K, V> params);
	
	public <K, V> Map<K, V> getLoadBillByQuotedId(String quotedId) ;
	
	// 获取应收列表
	public <E, K, V> Page<E> pageRecieveList(Map<K, V> params, int currentPage, int pageSizes);
	
	//查询订单概要信息
	public <K, V> Map<K, V> getOrderGenernalInfo(Map<K, V> params) ;
	
}
