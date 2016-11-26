package com.gzzhwl.api.account.service;

import com.gzzhwl.core.constant.LoginType;
import com.gzzhwl.rest.exception.RestServerException;

public interface UserLoginHisService {
	/**
	 * 保存人员的登录历史（获取token） 将该人员之前的登录历史置为失效
	 * 
	 * @param userId
	 * @param loginType
	 * @return
	 * @throws RestServerException
	 */
	public String saveUserLoginHis(String accountId, LoginType loginType) throws RestServerException;

	/**
	 * 使该人员，该类型的最新token失效
	 * 
	 * @param userId
	 * @param loginType
	 * @return
	 * @throws RestServerException
	 */
	public boolean updateInvalidToken(String accountId, LoginType loginType) throws RestServerException;
}
