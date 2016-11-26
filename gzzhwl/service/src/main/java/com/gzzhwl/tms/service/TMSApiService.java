package com.gzzhwl.tms.service;

import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.tms.model.Operate;

public interface TMSApiService {
	/**
	 * 货源 确认发布 拒绝发布 关闭货源 毁约货源
	 */
	public boolean doOperateOrder(String sourceId, Operate operate, String reason) throws RestException;

	/**
	 * 报价中标
	 */
	public boolean doBidOrder(String quotedId) throws RestException;

	/**
	 * 完善了司机车辆的信息
	 */
	public boolean doFinishBid(String loadId) throws RestException;

	/**
	 * 申请退回通知结果
	 */
	public boolean doReturnResponse(String sourceId, boolean agree) throws RestException;

	/**
	 * 超时自动关闭
	 */
	public boolean doUnbid(String sourceId) throws RestException;
}
