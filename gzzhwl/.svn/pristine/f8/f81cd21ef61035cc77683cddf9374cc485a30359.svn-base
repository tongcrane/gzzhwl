package com.gzzhwl.api.load.service;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.constant.DataSource;

public interface TripService {

	/**
	 * 获取司机合同订单列表
	 * 
	 * @param accountId
	 * @param statusArray
	 * @return
	 */
	public List<Map<String, Object>> getDriverOrderList(String accountId, String[] statusArray,DataSource source);

	/**
	 * 获取司机合同订单数量
	 * 
	 * @param accountId
	 * @param statusArray
	 * @return
	 */
	public Map<String, Object> getDriverOrderCount(String accountId,DataSource source);

	/**
	 * 获取司机合同在途中列表
	 * 
	 * @param accountId
	 * @return
	 */
	public List<Map<String, Object>> getDriverOrderList(String accountId,DataSource dataSource);

	/**
	 * 获取运势界司机合同详情
	 * 
	 * @param accountId
	 * @return
	 */
	public Map<String, Object> getDriverOrderDetail(String loadId, String accountId);

	/**
	 * 获取运势界司机合同已完成列表
	 * 
	 * @param accountId
	 * @return
	 */
	public List<Map<String, Object>> getFinishedOrderList(String accountId);

	/**
	 * 获取运势界司机合同全部列表
	 * 
	 * @param accountId
	 * @return
	 */
	public List<Map<String, Object>> getAllOrderList(String accountId);

	/**
	 * 获取内部司机司机历史订单
	 * 
	 * @param accountId
	 * @return
	 */
	public List<Map<String, Object>> getDriverOrderHistoryList(String accountId);

	// public OutputStream getDriverOrderListStream(String accountId,String
	// timeStamp,OutputStream outputStream) throws IOException;

	/**
	 * 运势界车检
	 * 
	 * @param loadId
	 */
	public void ysjVehicleCheck(String loadNo, String accountId, String actionTime);

	/**
	 * 运势界靠台
	 * 
	 * @param loadId
	 */
	public void ysjCloseToSurface(String loadNo, String accountId, String actionTime);

	/**
	 * 运势界发车
	 * 
	 * @param loadId
	 */
	public void ysjTripTheCar(String loadNo, String accountId, String actionTime);

	public boolean isLoadMajorDriver(String loadId, String accountId);

}
