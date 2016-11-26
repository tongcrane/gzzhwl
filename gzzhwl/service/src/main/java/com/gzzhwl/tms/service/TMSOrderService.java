package com.gzzhwl.tms.service;

import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.tms.vo.OrderVO;
import com.gzzhwl.tms.vo.SourceVO;

public interface TMSOrderService {
	/**
	 * 订单推送
	 */
	public String pushOrder(OrderVO orderInfo) throws RestException;

	/**
	 * 取消发车
	 */
	public boolean cancelTrip(SourceVO sourceVo) throws RestException;

	/**
	 * 订单完成
	 */
	public boolean complete(SourceVO sourceVo) throws RestException;

}
