package com.gzzhwl.admin.account.validate;

import org.apache.commons.lang3.StringUtils;

import com.gzzhwl.core.constant.ErrorCode;
import com.gzzhwl.core.data.model.AgentInfo;
import com.gzzhwl.core.utils.ValidateUtils;
import com.gzzhwl.rest.exception.RestException;

public class accountValidate {
	public static void valid_telphone(String telphone) {
		if (StringUtils.isBlank(telphone)) {
			throw new RestException(ErrorCode.ERROR_900003.getCode(), "手机号不能为空。");
		}

		if (!ValidateUtils.isMobileNo(telphone)) {
			throw new RestException(ErrorCode.ERROR_900004.getCode(), "手机号输入有误。");
		}
	}
	
	public static void valid_agent(AgentInfo agentInfo) {
		if(ValidateUtils.isEmpty(agentInfo)) {
			throw new RestException(ErrorCode.ERROR_900004.getCode(), "手机号重复，请确认手机号。");
		}
	}
	
	public static void valid_idno_Accordence(String agentIdno, String idno) {
		if(!idno.equals(agentIdno)) {
			throw new RestException(ErrorCode.ERROR_900004.getCode(), "手机号重复，请确认手机号。");
		}
	}
}
