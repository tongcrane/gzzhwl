package com.gzzhwl.admin.security.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.admin.security.service.StaffService;
import com.gzzhwl.core.page.Page;
import com.gzzhwl.rest.security.annotation.RequirePerm;
import com.gzzhwl.rest.security.model.AuthType;
import com.gzzhwl.rest.security.model.Subject;
import com.gzzhwl.rest.security.utils.SecurityUtils;
import com.gzzhwl.rest.springmvc.annotation.Pagination;
import com.gzzhwl.rest.springmvc.model.PageParameter;
import com.gzzhwl.rest.springmvc.model.ResponseModel;
import com.gzzhwl.rest.springmvc.validate.ParamEmptyValidator;

@RestController
@RequestMapping("/admin/staff")
public class StaffController {

	@Autowired
	private StaffService staffService;

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
	 * @return
	 */
	@RequirePerm
	@RequestMapping(value = "/addStaff", method = { RequestMethod.POST })
	public ResponseModel addStaff(@RequestParam String number, @RequestParam String realName,
			@RequestParam String position, @RequestParam String telphone, @RequestParam(required = false) String email,
			@RequestParam Integer departId) {
		ParamEmptyValidator.VALID_PARAM_EMPTY(number, realName, position, telphone);
		String staffId = staffService.addStaff(number, realName, position, telphone, email, departId,
				SecurityUtils.getSubject().getStaffId());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("staffId", staffId);
		return new ResponseModel(result);
	}

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
	 * @return
	 */
	@RequirePerm
	@RequestMapping(value = "/editStaff", method = { RequestMethod.POST })
	public ResponseModel editStaff(@RequestParam String staffId, @RequestParam(required = false) String number,
			@RequestParam(required = false) String realName, @RequestParam(required = false) String position,
			@RequestParam(required = false) String telphone, @RequestParam(required = false) String email,
			@RequestParam(required = false) Integer departId) {
		ParamEmptyValidator.VALID_PARAM_EMPTY(staffId);
		staffService.editStaff(staffId, number, realName, position, telphone, email, departId,
				SecurityUtils.getSubject().getStaffId());
		return new ResponseModel(null);
	}

	/**
	 * 删除员工
	 * 
	 * @param staffId
	 * @return
	 */
	@RequirePerm
	@RequestMapping(value = "/delStaff", method = { RequestMethod.POST })
	public ResponseModel delStaff(@RequestParam String staffId) {
		ParamEmptyValidator.VALID_PARAM_EMPTY(staffId);
		Subject subject = SecurityUtils.getSubject();
		String opId = subject.getStaffId();
		staffService.delStaff(staffId, opId);
		return new ResponseModel(null);
	}

	/**
	 * 获取员工列表
	 * 
	 * @param page
	 * @return
	 */
	@RequirePerm
	@RequestMapping(value = "/getStaffList", method = { RequestMethod.GET })
	public ResponseModel getStaffList(@RequestParam(required = false) String keyword, @Pagination PageParameter page) {
		Page<Map<String, Object>> result = staffService.getStaffList(keyword, page.getPageIndex(), page.getPageSize());
		return new ResponseModel(result);
	}

	/**
	 * 获取员工详情
	 * 
	 * @param staffId
	 * @return
	 */
	@RequirePerm
	@RequestMapping(value = "/getStaffInfo", method = { RequestMethod.GET })
	public ResponseModel getStaffInfo(@RequestParam String staffId) {
		ParamEmptyValidator.VALID_PARAM_EMPTY(staffId);
		Map<String, Object> result = staffService.getStaffInfo(staffId);
		return new ResponseModel(result);
	}

	/**
	 * 员工绑定菜单
	 * 
	 * @param staffId
	 * @param menuIds
	 * @return
	 */
	@RequirePerm
	@RequestMapping(value = "/bindStaffMenu", method = { RequestMethod.POST })
	public ResponseModel bindStaffMenu(@RequestParam String staffId, @RequestParam Integer[] menuIds,
			@RequestParam Integer[] departIds) {
		staffService.bindStaffMenu(staffId, menuIds, departIds);
		return new ResponseModel(null);
	}

	/**
	 * 修改密码
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@RequirePerm
	@RequestMapping(value = "/changePassword", method = { RequestMethod.POST })
	public ResponseModel changePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
		staffService.changePassword(SecurityUtils.getSubject().getStaffId(), oldPassword, newPassword);
		return new ResponseModel(null);
	}

	/**
	 * 根据工号获取邮箱/手机号
	 * 
	 * @param number
	 * @return
	 */
	@RequirePerm(type = AuthType.ANON)
	@RequestMapping(value = "/getEmailAndTelphoneByNumber", method = { RequestMethod.GET })
	public ResponseModel getEmailAndTelphoneByNumber(@RequestParam String number) {
		Map<String, String> result = staffService.getEmailAndTelphoneByNumber(number);
		return new ResponseModel(result);
	}

	/**
	 * 根据邮箱重置密码
	 * 
	 * @param number
	 * @return
	 */
	@RequirePerm(type = AuthType.ANON)
	@RequestMapping(value = "/resetPasswordByEmail", method = { RequestMethod.POST })
	public ResponseModel resetPasswordByEmail(@RequestParam String number) {
		staffService.resetPasswordByEmail(number);
		return new ResponseModel(null);
	}

	/**
	 * 根据手机号重置密码
	 * 
	 * @param number
	 * @return
	 */
	@RequirePerm(type = AuthType.ANON)
	@RequestMapping(value = "/resetPasswordByTelphone", method = { RequestMethod.POST })
	public ResponseModel resetPasswordByTelphone(@RequestParam String number) {
		String ticketId = staffService.resetPasswordByTelphone(number);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("ticketId", ticketId);
		return new ResponseModel(result);
	}

	/**
	 * 验证票据有效性
	 * 
	 * @param ticketId
	 * @return
	 */
	@RequirePerm(type = AuthType.ANON)
	@RequestMapping(value = "/validTicket", method = { RequestMethod.POST })
	public ResponseModel validTicket(@RequestParam String ticketId) {
		staffService.validTicket(ticketId);
		return new ResponseModel(null);
	}

	/**
	 * 根据票据重置密码
	 * 
	 * @param ticketId
	 * @param password
	 * @return
	 */
	@RequirePerm(type = AuthType.ANON)
	@RequestMapping(value = "/resetPasswordByTicket", method = { RequestMethod.POST })
	public ResponseModel resetPasswordByTicket(@RequestParam String ticketId, @RequestParam String password) {
		staffService.resetPasswordByTicket(ticketId, password);
		return new ResponseModel(null);
	}

	/**
	 * 获取部门列表
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/getDepartList", method = { RequestMethod.GET })
	public ResponseModel getDepartList(@RequestParam(required = false) String name) {
		List<Map<String, Object>> result = staffService.getDepartList(name);
		return new ResponseModel(result);
	}

	/**
	 * 权限管理列表
	 * 
	 * @param queryType
	 * @param keyword
	 * @param number
	 * @param realName
	 * @param departName
	 * @param dataDepart
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/pageStaff", method = { RequestMethod.GET })
	public ResponseModel pageStaff(@RequestParam String queryType, @RequestParam(required = false) String keyword,
			@RequestParam(required = false) String number, @RequestParam(required = false) String realName,
			@RequestParam(required = false) String departName, @RequestParam(required = false) String dataDepart,
			@Pagination PageParameter page) {
		Page<Map<String, Object>> result = staffService.pageStaff(queryType, keyword, number, realName, departName,
				dataDepart, page.getPageIndex(), page.getPageSize());
		return new ResponseModel(result);
	}

	/**
	 * 获取权限详情
	 * 
	 * @param number
	 * @param realName
	 * @param departName
	 * @param perDepart
	 * @param keyword
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/getPermissionInfo", method = { RequestMethod.GET })
	public ResponseModel getPermissionInfo(@RequestParam String staffId) {
		Map<String, Object> result = staffService.getPermissionInfo(staffId);
		return new ResponseModel(result);
	}

}
