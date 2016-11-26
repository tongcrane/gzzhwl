package com.gzzhwl.admin.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.admin.menu.service.MenuService;
import com.gzzhwl.admin.security.model.AuthStaffInfo;
import com.gzzhwl.admin.security.service.StaffService;
import com.gzzhwl.core.data.model.Menu;
import com.gzzhwl.rest.security.annotation.RequirePerm;
import com.gzzhwl.rest.security.model.AuthType;
import com.gzzhwl.rest.security.model.Subject;
import com.gzzhwl.rest.security.utils.SecurityUtils;
import com.gzzhwl.rest.springmvc.model.ResponseModel;
import com.gzzhwl.rest.springmvc.validate.ParamEmptyValidator;

@RestController
@RequestMapping("/admin/security")
public class SecurityController {
	@Autowired
	private StaffService staffService;
	@Autowired
	private MenuService menuService;

	/**
	 * 登录
	 */
	@RequirePerm(type = AuthType.ANON)
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public ResponseModel login(@RequestParam String number, @RequestParam String password) {
		ParamEmptyValidator.VALID_PARAM_EMPTY(number, password);
		AuthStaffInfo staffInfo = staffService.doLogin(number, password);
		return new ResponseModel(staffInfo);
	}

	/**
	 * 登出
	 */
	@RequirePerm
	@RequestMapping(value = "/logout", method = { RequestMethod.POST })
	public ResponseModel logout() {
		Subject subject = SecurityUtils.getSubject();
		staffService.doLogout(subject.getStaffId());
		SecurityUtils.logout();
		return new ResponseModel(null);
	}

	/**
	 * 获取用户菜单
	 */
	@RequirePerm
	@RequestMapping(value = "/menu", method = { RequestMethod.GET })
	public ResponseModel menu() {
		Subject subject = SecurityUtils.getSubject();
		List<Menu> menus = menuService.getUserMenu(subject.getStaffId());
		return new ResponseModel(menus);
	}
}
