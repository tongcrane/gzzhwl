package com.gzzhwl.rest.security.validate;

import org.apache.commons.lang3.StringUtils;

import com.gzzhwl.core.constant.Global;
import com.gzzhwl.core.data.model.StaffInfo;
import com.gzzhwl.core.data.model.StaffLoginHistory;
import com.gzzhwl.rest.exception.ExceptionCodeDef;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.security.AuthorizationService;

/**
 * 当前用户验证器
 * 
 * @author anycrane
 *
 */
public class AdminValidator {
	/**
	 * 根据http header请求参数获取当前登录人验证器
	 * 
	 * @param authorization
	 * @throws ValidateException
	 */
	public static void validAuthorization(String authorization) throws RestException {
		if (StringUtils.isEmpty(authorization)) {
			throw new RestException(ExceptionCodeDef.SC_EMPTY_FORBIDDEN, "token为空");// token为空
		} else if (StringUtils.split(authorization, AuthorizationService.AUTHORIZATION_SPLIT).length != 2) {
			throw new RestException(ExceptionCodeDef.SC_FORMAT_FORBIDDEN, "token格式不正确");// token格式不正确
		}
	}

	/**
	 * 验证参数中的token是否有效
	 * 
	 * @param loginHistory
	 * @param lastest
	 * @throws ValidateException
	 */
	public static void validAuthorization(StaffLoginHistory loginHistory, String lastest) throws RestException {
		if (loginHistory == null) {
			throw new RestException(ExceptionCodeDef.SC_ERROR_FORBIDDEN, "当前token不正确");// 当前token不正确
		}
		String current = loginHistory.getAccessToken();// 当前可用的token
		if (!StringUtils.equals(current, lastest)) {
			throw new RestException(ExceptionCodeDef.SC_LASTEST_FORBIDDEN, "当前token不是最新");// 当前token不是最新
		} else {
			// TODO 验证token过期时间
		}
	}

	public static void validStaffInfo(StaffInfo staffInfo) {
		if (staffInfo == null) {
			throw new RestException(ExceptionCodeDef.SC_EMPTY_FORBIDDEN, "用户ID不存在");
		}
		if (StringUtils.equals(staffInfo.getIsDeleted(), Global.ISDEL_DELETE.toString())) {
			throw new RestException(ExceptionCodeDef.SC_FORMAT_FORBIDDEN, "用户ID不存在");
		}
	}
}
