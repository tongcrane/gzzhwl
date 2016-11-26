package com.gzzhwl.admin.account.service;

import java.util.List;
import java.util.Map;

import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.exception.RestException;
import com.gzzhwl.rest.springmvc.model.PageParameter;

public interface UserService {
	/**
	 * 获取审核用户列表
	 * @param checkStatus
	 * @param userName
	 * @param realName
	 * @param companyName
	 * @param page
	 * @return
	 * @throws RestException
	 */
	public Page<Map<String,Object>> getCheckList(String checkStatus,String telphone,String realName,String companyName,String queryType,String queryContent,PageParameter page) throws RestException;
	/**
	 * 获取注册用户详情
	 * @param accountId
	 * @return
	 * @throws RestException
	 */
	public Map<String,Object> getUserDetail(String accountId) throws RestException;
	/**
	 * 审核通过
	 * @param accountId
	 * @throws RestException
	 */
	public void checkPass(String accountId,String createdBy) throws RestException;
	/**
	 * 审核未通过
	 * @param accountId
	 * @throws RestException
	 */
	public void checkUnpass(String accountId,String createdBy) throws RestException;
	/**
	 * 获取注册账号列表
	 * @return
	 * @throws RestException
	 */
	public Page<Map<String,Object>> getAccountList(String telphone,String realName,String status,String queryType,String queryContent,PageParameter page) throws RestException;
	/**
	 * 冻结账号
	 * @param accountId
	 * @throws RestException
	 */
	public void freeze(String accountId,String createdBy) throws RestException;
	/**
	 * 账号解冻
	 * @param accountId
	 * @throws RestException
	 */
	public void thaw(String accountId,String createdBy) throws RestException;

	/**
	 * 获取账户下线路列表
	 */
	public List<Map<String, Object>> getLineList(String accountId);
	
	public Map<String,Object> getUserDetail2(String accountId) throws RestException;
	public Map<String,Object> getAccountDetail2(String accountId) throws RestException;
}
