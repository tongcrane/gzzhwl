package com.gzzhwl.api.message.service;

import java.util.Map;

import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.springmvc.model.PageParameter;

public interface MessageTipsService {
	
	/**
	 * 添加消息
	 */
	public String addMessage(String category,String accountId,String sourceId,String sourceType,String content,String createdUser) throws RestException;
	
	/**
	 * 删除消息
	 */
	public void delMessage(String messageId,String createdUser) throws RestException;
	
	/**
	 * 获取消息列表
	 */
	public Page<Map<String,Object>> updateAndPageMessageList(String accountId,String category,PageParameter page) throws RestException;
		
	/**
	 * 更新消息状态
	 */
	public void updateMessageReadStatus(String[] messageIds) throws RestException;
		
	/**
	 * 获取未读消息数
	 */
	public Integer getUnreadMessageCount(String accountId) throws RestException;

}
