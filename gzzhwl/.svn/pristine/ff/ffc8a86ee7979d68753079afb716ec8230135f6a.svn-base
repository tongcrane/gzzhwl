package com.gzzhwl.admin.quoted.service;

import java.util.List;

import com.gzzhwl.common.model.FlowActionCategory;
import com.gzzhwl.core.constant.QuotedType;
import com.gzzhwl.core.data.model.QuotedHis;

public interface QuotedHisService {
	
	/**
	 * 记录报价状态变更记录
	 * @param quotedId
	 * @param srcStatus
	 * @param destStatus
	 * @param flowActionCategory
	 * @param msgInfo
	 * @param createdBy
	 */
	public void saveQuotedHis(String quotedId,String srcStatus,String destStatus,FlowActionCategory flowActionCategory,String msgInfo,String staffId);
	
	public QuotedHis getQuotedHisBySourceId(String sourceId,QuotedType quotedType);
	
	public <T, K, V> List<T> getQuotedHisList(String sourceId);
	
}
