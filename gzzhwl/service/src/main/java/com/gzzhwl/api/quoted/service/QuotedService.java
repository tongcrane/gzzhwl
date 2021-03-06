package com.gzzhwl.api.quoted.service;

import java.util.Map;

import com.gzzhwl.api.quoted.vo.FinishQuotedVo;
import com.gzzhwl.core.data.model.QuotedInfo;
import com.gzzhwl.core.page.Page;

public interface QuotedService {
	 
//	/**
//	 * 是否允许报价
//	 * @param accountId
//	 * @param sourceId
//	 * @return true 允许 false 不允许
//	 */
//	public boolean isAllowAddQuotedInfo(String accountId,String sourceId);
	
//	/**
//	 * 获取货源竞标状态
//	 * @param sourceId
//	 * @return
//	 */
//	public QuotedType getSourceQuotedState(String sourceId);
	
	/**
	 * 获取投标人数
	 * @param sourceId
	 * @return
	 */
	public Integer getQuotedCountBySourceId(String sourceId);
	
	/**
	 * 添加报价
	 * @param accountId
	 * @param sourceId
	 * @param price
	 * @param remark
	 * @return
	 */
	public String addQuoted(String accountId,String sourceId,String price,String remark);
	
	/**
	 * 获取我的有效报价
	 * @param sourceId
	 * @return
	 */
	public QuotedInfo getMyQuoted(String sourceId,String accountId);
	
	/**
	 * 完成竞标
	 * @param accountId
	 * @param sourceId
	 * @return
	 */
	public String finishQuoted (FinishQuotedVo finishQuotedVo,String accountId);
	
	/**
	 * 关闭报价
	 * @param accountId
	 * @param sourceId
	 * @return
	 */
	public String closeQuotedInfo(String sourceId,String accountId);
	
	/**
	 * 作废
	 * @param accountId
	 * @param sourceId
	 * @return
	 */
	public String invalidTheBid(String sourceId,String accountId);
	
	/**
	 * 获取订单列表
	 * @param sourceType
	 * @param accountId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Page<Map<String, Object>> getQuotedOrderList(String sourceType,String accountId, int pageIndex, int pageSize);
	
	
	public Map<String, Object> getQuotedOrderDetail(String quotedId,String accountId);
	
	public Map<String,Object> getOrderCount(String accountId);
	
	public Map<String,Object> getLoadBillByQuotedId(String quotedId);
	
	 
}
