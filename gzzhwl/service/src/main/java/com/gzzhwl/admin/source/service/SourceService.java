package com.gzzhwl.admin.source.service;

import java.util.List;
import java.util.Map;

import com.gzzhwl.admin.source.vo.PushSourceVO;
import com.gzzhwl.core.data.model.OrderDetailInfo;
import com.gzzhwl.rest.exception.RestException;

public interface SourceService {

	/**
	 * 拒绝发布货源
	 */
	public boolean rejectSource(String sourceId, String reason, String staffId, boolean notice) throws RestException;

	/**
	 * 发布货源
	 */
	public boolean publicSource(PushSourceVO pushSource, String staffId) throws RestException;

	/**
	 * 关闭货源
	 */
	public boolean closeSource(String sourceId, String reason, String staffId, boolean notice) throws RestException;

	/**
	 * 根据订单ID获取当前的货源ID
	 */
	public String getCurrentSource(String orderId) throws RestException;

	/**
	 * 根据货源ID获取当前的订单详情
	 */
	public OrderDetailInfo getOrderDetailInfo(String sourceId) throws RestException;

	public List<Map<String, Object>> whatToDo(String currentStatus) throws RestException;

	public List<String> findSourceClose() throws RestException;

}
