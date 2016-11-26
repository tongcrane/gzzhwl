package com.gzzhwl.api.message.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.api.message.service.MessageTipsService;
import com.gzzhwl.core.data.model.AccountInfo;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.springmvc.annotation.Authorization;
import com.gzzhwl.rest.springmvc.annotation.Pagination;
import com.gzzhwl.rest.springmvc.model.PageParameter;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/api/message")
public class MessageTipsController {
	
	@Autowired
	private MessageTipsService messageTipsService;
	
	/**
	 * 删除消息
	 */
	@RequestMapping(value = "/delMessage", method = RequestMethod.POST)
	public ResponseModel delMessage(@RequestParam(required=true) String messageId,@Authorization AccountInfo accountInfo) throws ParseException, IOException {
		String accountId=accountInfo.getAccountId();
		messageTipsService.delMessage(messageId,accountId);
		return new ResponseModel(null);
	}

	/**
	 * 获取消息列表
	 */
	@RequestMapping(value = "/getMessageList", method = RequestMethod.GET)
	public ResponseModel getMessageList(@Authorization AccountInfo accountInfo,@Pagination PageParameter page) throws ParseException, IOException {
		String accountId=accountInfo.getAccountId();
		Page<Map<String, Object>> result = messageTipsService.updateAndPageMessageList(accountId, null, page);
		return new ResponseModel(result);
	}
	
	/**
	 * 获取未读消息数
	 */
	@RequestMapping(value = "/getUnreadMessageCount", method = RequestMethod.GET)
	public ResponseModel getUnreadMessageCount(@Authorization AccountInfo accountInfo) throws ParseException, IOException {
		String accountId = accountInfo.getAccountId();
		int count = messageTipsService.getUnreadMessageCount(accountId);
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("count", count);
		return new ResponseModel(result);
	}

}
