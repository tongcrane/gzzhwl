package com.gzzhwl.api.message.validate;

import org.apache.commons.lang.StringUtils;

import com.gzzhwl.core.data.model.MessageTips;
import com.gzzhwl.rest.exception.RestException;

public class MessageValidator {
	
	public static void validateDelMessage(MessageTips message, String userId) {
		if (message == null) {
			throw new RestException("910031","消息不存在");
		}
		String accountId = message.getAccountId();
		if (!StringUtils.equals(accountId, userId)) {
			throw new RestException("910032","没有操作权限");
		}
	}


}
