package com.gzzhwl.admin.menu.service;

import java.util.List;

import com.gzzhwl.core.data.model.Menu;
import com.gzzhwl.core.data.model.MenuInfo;
import com.gzzhwl.rest.exception.RestException;

public interface MenuService {
	public List<MenuInfo> getAllMenu() throws RestException;

	/**
	 * 获取所有菜单
	 * 
	 * @return
	 * @throws RestException
	 */
	public List<Menu> getMenu() throws RestException;

	/**
	 * 获取用户的菜单
	 * 
	 * @param staffId
	 * @return
	 * @throws RestException
	 */
	public List<Menu> getUserMenu(String staffId) throws RestException;

	/**
	 * 获取用户的功能权限
	 * 
	 * @param staffId
	 * @return
	 * @throws RestException
	 */
	public List<Integer> getUserFunction(String staffId) throws RestException;

	/**
	 * 判断是否是菜单
	 * 
	 * @param menuId
	 * @return
	 * @throws RestException
	 */
	public boolean isMenu(int menuId) throws RestException;

}
