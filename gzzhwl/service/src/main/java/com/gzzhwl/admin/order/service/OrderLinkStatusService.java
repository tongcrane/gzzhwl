package com.gzzhwl.admin.order.service;

import com.gzzhwl.rest.exception.RestException;

public interface OrderLinkStatusService {
	/**
	 * YSJ 操作货源联动订单状态 ---start
	 */
	/**
	 * 拒绝订单
	 */
	public boolean rejectOrder(String orderId, String reason, String staffId) throws RestException;

	/**
	 * 关闭订单
	 */
	public boolean closeSourceOrder(String orderId, String reason, String staffId) throws RestException;

	/**
	 * 发布订单
	 */
	public boolean publicOrder(String orderId, String staffId) throws RestException;

	/**
	 * 中标订单
	 */
	public boolean bidOrder(String orderId, String staffId) throws RestException;

	/**
	 * 配载订单
	 */
	public boolean avttSource(String orderId, String staffId) throws RestException;

	/**
	 * 违约了订单
	 */
	public boolean breakPromiseOrder(String orderId, String operatorId) throws RestException;

	/**
	 * 竞价流拍
	 */
	public boolean autoClose(String orderId, String staffId) throws RestException;

	/**
	 * YSJ 操作货源联动订单状态 ---end
	 */

	/**
	 * 配载
	 */
	public boolean loadOrder(String orderId, String staffId) throws RestException;

	/**
	 * 取消配载
	 */
	public boolean cancelLoad(String orderId, String staffId) throws RestException;

	/**
	 * 发车(做车检、靠台)
	 */
	public boolean doTrip(String orderId, String staffId) throws RestException;

	/**
	 * 车辆已发车
	 */
	public boolean setOut(String orderId, String staffId) throws RestException;

	/**
	 * 取消发车
	 */
	public boolean cancelSetOut(String orderId, String staffId) throws RestException;

	/**
	 * 车辆到达
	 */
	public boolean doArrive(String orderId, String staffId) throws RestException;
	
	/**
	 * 收到纸质回单
	 */
	public boolean doPrintReceipt(String orderId, String staffId) throws RestException;
	
	/**
	 * 车辆到达关闭
	 */
	public boolean doClose(String orderId, String staffId) throws RestException;
}
