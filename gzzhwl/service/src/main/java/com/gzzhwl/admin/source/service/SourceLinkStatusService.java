package com.gzzhwl.admin.source.service;

import com.gzzhwl.admin.order.vo.PushOrderVO;
import com.gzzhwl.rest.exception.RestException;

public interface SourceLinkStatusService {
	/**
	 * 新增货源
	 */
	public String addSource(String orderId, PushOrderVO orderInfo, String staffId) throws RestException;

	/**
	 * YSJ 操作报价联动货源状态 ---start
	 */
	/**
	 * 管理员选中某条报价中标
	 */
	public boolean bidSource(String sourceId, String staffId) throws RestException;

	/**
	 * allocate vehicles to transport 用户完成信息补充
	 */
	public boolean avttSource(String sourceId, String staffId) throws RestException;

	/**
	 * 用户作废货源
	 */
	public boolean breakPromiseSourceByUser(String sourceId, String accountId) throws RestException;

	/**
	 * 管理员作废货源
	 */
	public boolean breakPromiseSourceByAdmin(String sourceId, String staffId, boolean notice) throws RestException;

	/**
	 * 自动关闭货源
	 */
	public boolean autoCloseSource(String sourceId, String staffId) throws RestException;

	/**
	 * YSJ 操作报价联动货源状态 ---end
	 */
	/**
	 * 取消发车
	 */
	public boolean cancelSetOut(String sourceId, String staffId) throws RestException;
}
