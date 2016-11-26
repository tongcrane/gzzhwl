package com.gzzhwl.rest.security;

import com.gzzhwl.core.data.model.AccountInfo;
import com.gzzhwl.core.data.model.LoginHistory;
import com.gzzhwl.rest.exception.RestServerException;

public interface AuthorizationService {
	public static final String AUTHORIZATION_SPLIT = ":";// token分隔符

	/**
	 * 根据http header参数获取当前登录人信息。
	 * 
	 * @param authorization
	 * @return
	 * @throws RestServerException
	 */
	public AccountInfo getCurrentAccount(String authorization) throws RestServerException;

	/**
	 * 根据登录人ID及token获取登录历史，并按登录时间降序。
	 * 
	 * @param userId
	 * @param token
	 * @return
	 * @throws RestServerException
	 */
	public LoginHistory getAccountHis(String userId, String token) throws RestServerException;

	/**
	 * 根据登录人ID获取登录人基本登录信息
	 * 
	 * @param loginId
	 * @return
	 * @throws RestServerException
	 */
	public AccountInfo getCurrentAccountById(String loginId) throws RestServerException;
}
