package com.gzzhwl.admin.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzzhwl.admin.menu.service.MenuService;
import com.gzzhwl.core.data.model.Menu;
import com.gzzhwl.rest.springmvc.model.ResponseModel;

@RestController
@RequestMapping("/admin/auth")
public class AuthController {
	@Autowired
	private MenuService menuService;

	/**
	 * 所有菜单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/menu", method = { RequestMethod.GET })
	public ResponseModel menu() {
		List<Menu> menus = menuService.getMenu();
		return new ResponseModel(menus);
	}

}
