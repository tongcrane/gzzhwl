package com.gzzhwl.admin.security.service;

import com.gzzhwl.core.constant.LoginType;
import com.gzzhwl.rest.exception.RestServerException;

public interface StaffLoginHisService {
	public static final LoginType DEFAULT_LOGIN_TYPE = LoginType.WEB;
	/**
	 * 保存人员的登录历史（获取token） 将该人员之前的登录历史置为失效
	 * 
	 * @param userId
	 * @param loginType
	 * @return
	 * @throws RestServerException
	 */
	public String saveUserLoginHis(String staffId) throws RestServerException;

	/**
	 * 使该人员，该类型的最新token失效
	 * 
	 * @param userId
	 * @param loginType
	 * @return
	 * @throws RestServerException
	 */
	public boolean updateInvalidToken(String accountId) throws RestServerException;
}
