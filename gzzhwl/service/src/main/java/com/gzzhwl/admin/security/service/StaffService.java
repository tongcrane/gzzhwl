package com.gzzhwl.admin.security.service;

import java.util.List;
import java.util.Map;

import com.gzzhwl.admin.security.model.AuthStaffInfo;
import com.gzzhwl.core.data.model.ResetPasswordLog;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.exception.RestException;

public interface StaffService {

	public AuthStaffInfo doLogin(String number, String password) throws RestException;

	public boolean doLogout(String staffId) throws RestException;

	/**
	 * 添加员工
	 * 
	 * @param number
	 * @param password
	 * @param realName
	 * @param position
	 * @param telphone
	 * @param email
	 * @param departCode
	 * @param createdBy
	 * @throws RestException
	 */
	public String addStaff(String number, String realName, String position, String telphone, String email,
			Integer departId, String createdBy) throws RestException;

	/**
	 * 编辑员工
	 * 
	 * @param staffId
	 * @param number
	 * @param realName
	 * @param position
	 * @param telphone
	 * @param email
	 * @param departCode
	 * @param createdBy
	 * @throws RestException
	 */
	public void editStaff(String staffId, String number, String realName, String position, String telphone,
			String email, Integer departId, String createdBy) throws RestException;

	/**
	 * 删除员工
	 * 
	 * @param staffId
	 * @throws RestException
	 */
	public void delStaff(String staffId, String opId) throws RestException;

	/**
	 * 获取员工列表
	 * 
	 * @return
	 * @throws RestException
	 */
	public Page<Map<String, Object>> getStaffList(String keyword, int pageIndex, int pageSize) throws RestException;

	/**
	 * 获取员工详情
	 * 
	 * @param staffId
	 * @return
	 * @throws RestException
	 */
	public Map<String, Object> getStaffInfo(String staffId) throws RestException;

	/**
	 * 绑定员工菜单
	 * 
	 * @param staffId
	 * @param menuIds
	 * @throws RestException
	 */
	public void bindStaffMenu(String staffId, Integer[] menuIds, Integer[] departIds) throws RestException;

	/**
	 * 修改密码
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @throws RestException
	 */
	public void changePassword(String staffId, String oldPassword, String newPassword) throws RestException;

	/**
	 * 根据工号获取邮箱/手机号
	 * 
	 * @param number
	 * @return
	 * @throws RestException
	 */
	public Map<String, String> getEmailAndTelphoneByNumber(String number) throws RestException;

	/**
	 * 根据邮箱重置密码
	 * 
	 * @param number
	 * @param createdBy
	 * @return
	 * @throws RestException
	 */
	public boolean resetPasswordByEmail(String number) throws RestException;

	/**
	 * 根据手机号重置密码
	 * 
	 * @param number
	 * @param createdBy
	 * @return
	 * @throws RestException
	 */
	public String resetPasswordByTelphone(String number) throws RestException;

	/**
	 * 验证票据有效性
	 * 
	 * @param ticketId
	 * @return
	 * @throws RestException
	 */
	public ResetPasswordLog validTicket(String ticketId) throws RestException;

	/**
	 * 根据票据重置密码
	 * 
	 * @param ticketId
	 * @param password
	 * @throws RestException
	 */
	public void resetPasswordByTicket(String ticketId, String password) throws RestException;

	/**
	 * 获取部门列表
	 * 
	 * @return
	 * @throws RestException
	 */
	public List<Map<String, Object>> getDepartList(String name) throws RestException;

	/**
	 * 权限管理列表
	 * 
	 * @param queryType
	 * @param keyword
	 * @param number
	 * @param realName
	 * @param departName
	 * @param dataDepart
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws RestException
	 */
	public Page<Map<String, Object>> pageStaff(String queryType, String keyword, String number, String realName,
			String departName, String dataDepart, int pageIndex, int pageSize) throws RestException;

	/**
	 * 权限管理详情
	 * 
	 * @param staffId
	 * @return
	 * @throws RestException
	 */
	public Map<String, Object> getPermissionInfo(String staffId) throws RestException;

}
