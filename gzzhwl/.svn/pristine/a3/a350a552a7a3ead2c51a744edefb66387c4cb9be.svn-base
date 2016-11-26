package com.gzzhwl.admin.remark.service;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.constant.RemarkType;

public interface RemarkInfoService {
	
	public void saveRemark(String targetId,RemarkType remarkType,String content,String createdBy);

	
	public <T, K, V> List<T> getRecordList(String targetId,RemarkType remarkType);
	
	/**
	 * 获取最新一条的在途跟踪记录
	 * @param targetId
	 * @return
	 */
	public Map<String,Object> getOnlineRecordNew(String loadId);
	
	
}
