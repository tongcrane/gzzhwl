package com.gzzhwl.admin.quoted.service;

import java.util.Map;

import com.gzzhwl.core.data.model.QuotedInfo;
import com.gzzhwl.core.page.Page;

public interface QuotedManageService {
	
	/**
	 * 中标
	 * @param accountId
	 * @param sourceId
	 * @return
	 */
	public String winTheBid(String quotedId,String staffId);
	
//	/**
//	 * 查询竞价管理列表
//	 * @param quotedManageVo
//	 * @return
//	 */
//	public Page<Map<String, Object>> queryQuotedManageList(QuotedManageVo quotedManageVo);
	
	/**
	 * 查询竞价详情
	 * @param account
	 * @param sourceId
	 * @return
	 */
	public Page<Map<String,Object>> queryQuotedManagerList(String sourceId,int pageIndex, int pageSize);
	
	/**
	 * 获取运输车辆信息
	 * @param account
	 * @param sourceId
	 * @return
	 */
	public Map<String, Object> getBidVehicleInfo(String sourceId);
	
	/**
	 * 作废
	 * @param accountId
	 * @param sourceId
	 * @return
	 */
	 public String invalidTheBid(String quotedId,String content,String staffId);
	 
	 /**
	   * 作废报价
	   * @param accountId
	   * @param sourceId
	   * @return
	   */
	public String invalidQuoted(String sourceId,String content,String staffId);
	 
	 /**
	   * 关闭报价
	   * @param accountId
	   * @param sourceId
	   * @return
	   */
	public String closeQuotedInfo(String quotedId,String content,String staffId);
	
	/**
	 * 中标联动处理
	 * @param sourceId
	 * @param quotedId
	 */
	public void winTheBidLinkageHandler(String sourceId,String quotedId,String staffId);
	
	/**
	 * 关闭货源报价    -联动处理
	 * @param sourceId
	 */
	public void closeSourceLinkageHandler(String sourceId,String staffId);
	
	/**
	 * 获取货源报价数量
	 * @param sourceId
	 * @return
	 */
	public Integer getSourceQuotedCount(String sourceId);
	
	/**
	 * 获取中标货源信息
	 * @param sourceId
	 * @return
	 */
	public QuotedInfo getBidQuoted(String sourceId);
	
	/**
	 * 获取全部报价列表
	 */
	public Page<Map<String, Object>> queryAllQuotedList(String sourceId, int pageIndex, int pageSize);
	
	/**
	 * 获取中标货源信息
	 * @param sourceId
	 * @return
	 */
	public Map<String, Object> getBidQuotedInfo(String sourceId);
	
	
	
}
