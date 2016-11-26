package com.gzzhwl.core.data.extdao;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.constant.DataSource;
import com.gzzhwl.core.data.model.OrderLoadInfo;
import com.gzzhwl.core.page.Page;

public interface OrderLoadInfoExtDao {
	public static final String PREFIX = OrderLoadInfoExtDao.class.getName();

	public <E, K, V> Page<E> pageTripList(Map<K, V> params, int currentPage, int pageSizes);

	/**
	 * 获取发车反馈信息
	 * 
	 * @param loadId
	 * @return
	 */
	public Map<String, Object> getTripDetail(String loadId,String consignId);

	public <T, K, V> List<T> getTripDrivers(String loadId);

	public <T, K, V> List<T> getLoadInfoByOrderId(Map<String, Object> params);

	// 根据货场和关键字查询提货单列表
	public <T, K, V> List<T> getFieldControlList(String addrId, String keyWord, String timeStamp);
	
	//获取提货单主司机信息
	public <K, V> Map<K, V>  getLoadMajorDriver(Map<K, V> params);
	
	//查询线控历史订单
	public <T, K, V> List<T> getFieldControlHis(Map<K, V> params);
	
	//查询在途/到达的车辆详情
	public <K, V> Map<K, V>  getLoadVehicleDetail(Map<K, V> params);
	
	//查询到达列表
	public <E, K, V> Page<E> pageArriveList(Map<K, V> params, int currentPage, int pageSizes);
	
	/**
	 * 回单查询
	 * @param params
	 * @return
	 */
	public <E, K, V> Page<E> pageReceiptList(Map<String, Object> params, int currentPage, int pageSize);
	
	
	public Integer getDriverOrderCount(String accountId,String[] statusArray,DataSource dataSource);
	
	public <T, K, V> List<T> getDriverOrderList(String idno,String[] statusArray,DataSource dataSource);
	
	public <K, V> Map<K, V> getDriverOrderDetail(String loadId,String idno);
	
	public OrderLoadInfo getForUpdate(String loadId);

	public long isMajorDriver(Map<String, Object> params);

}
