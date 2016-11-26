package com.gzzhwl.admin.load.service;

import com.gzzhwl.rest.exception.RestException;

public interface LoadLinkStatusService {
	/**
	 * 创建提货单
	 */
	public String createLoad(String orderId, Integer departId, String staffId) throws RestException;

	/**
	 * 根据报价ID自动创建提货单
	 */
	public String createLoadFromYSJ(String quotedId,String accountId) throws RestException;

	/**
	 * 取消发车
	 */
	public boolean cancelTrip(String loadId, String staffId) throws RestException;
}
