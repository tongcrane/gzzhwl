package com.gzzhwl.admin.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.admin.menu.service.MenuService;
import com.gzzhwl.core.data.model.MenuInfo;
import com.gzzhwl.rest.security.annotation.RequirePerm;
import com.gzzhwl.rest.security.model.AuthType;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/admin/menu")
public class MenuController {
	@Autowired
	private MenuService menuService;

	/**
	 * 所有菜单
	 * 
	 * @return
	 */
	@RequirePerm(type = AuthType.ANON)
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public ResponseModel list() {
		List<MenuInfo> menus = menuService.getAllMenu();
		return new ResponseModel(menus);
	}
}
