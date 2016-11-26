package com.gzzhwl.rest.security;

import com.gzzhwl.core.data.model.StaffInfo;
import com.gzzhwl.core.data.model.StaffLoginHistory;
import com.gzzhwl.rest.exception.RestServerException;

public interface AdminService {
	public static final String AUTHORIZATION_SPLIT = ":";// token分隔符

	/**
	 * 根据http header参数获取当前登录人信息。
	 * 
	 * @param authorization
	 * @return
	 * @throws RestServerException
	 */
	public StaffInfo getCurrentAdmin(String authorization) throws RestServerException;

	/**
	 * 根据登录人ID及token获取登录历史，并按登录时间降序。
	 * 
	 * @param userId
	 * @param token
	 * @return
	 * @throws RestServerException
	 */
	public StaffLoginHistory getAccountHis(String staffId, String token) throws RestServerException;

	/**
	 * 根据登录人ID获取登录人基本登录信息
	 * 
	 * @param loginId
	 * @return
	 * @throws RestServerException
	 */
	public StaffInfo getCurrentAdminById(String staffId) throws RestServerException;

}
