package com.gzzhwl.admin.menu.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.list.SetUniqueList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.gzzhwl.admin.menu.service.MenuService;
import com.gzzhwl.core.data.dao.MenuInfoDao;
import com.gzzhwl.core.data.model.Menu;
import com.gzzhwl.core.data.model.MenuInfo;
import com.gzzhwl.rest.exception.RestException;

@Service
public class MenuServiceImpl implements MenuService, InitializingBean {
	@Autowired
	private MenuInfoDao menuDao;
	private Map<Integer, MenuInfo> menus;

	@Override
	public List<Menu> getMenu() throws RestException {
		// TODO:dao加缓存
		List<MenuInfo> allMenu = menuDao.findMenu();
		MenuInfo root = new MenuInfo();
		root.setIsLeaf(MenuInfo.LEAF_NO);
		root.setMenuId(MenuInfo.ROOT_MENU_ID);
		return getMenu(root, allMenu);
	}

	private List<Menu> getMenu(MenuInfo parent, List<MenuInfo> allMenu) {
		if (StringUtils.equals(parent.getIsLeaf(), MenuInfo.LEAF_NO)) {
			List<Menu> result = new ArrayList<Menu>();
			for (MenuInfo menuInfo : allMenu) {
				if (menuInfo.getMenuGroupId().compareTo(parent.getMenuId()) == 0) {
					Menu info = new Menu();
					info.setMenuId(menuInfo.getMenuId());
					info.setMenuName(menuInfo.getMenuName());
					info.setMenuPath(menuInfo.getMenuPath());
					info.setIsLeaf(menuInfo.getIsLeaf());
					info.setDescription(menuInfo.getDescription());
					info.setSubMenu(getMenu(menuInfo, allMenu));
					result.add(info);
				}
			}
			return result;
		} else {
			return null;
		}
	}

	@Override
	public List<Integer> getUserFunction(String staffId) throws RestException {
		return menuDao.findStaffFunction(staffId);
	}

	@Override
	public List<Menu> getUserMenu(String staffId) throws RestException {
		// TODO:用户菜单缓存下来
		List<Integer> userMenu = this.getUserFunction(staffId);
		List<Integer> allMenuIds = convertMenuIds(userMenu);
		if (CollectionUtils.isNotEmpty(allMenuIds)) {
			Collections.sort(allMenuIds);
			SetUniqueList<Integer> allMenuIds_set = SetUniqueList.setUniqueList(allMenuIds);
			List<MenuInfo> allMenu = this.getAllMenus(allMenuIds_set);
			MenuInfo root = new MenuInfo();
			root.setIsLeaf(MenuInfo.LEAF_NO);
			root.setMenuId(MenuInfo.ROOT_MENU_ID);
			return getMenu(root, allMenu);
		} else {
			return null;
		}
	}

	private List<MenuInfo> getAllMenus(List<Integer> userMenu) {
		List<MenuInfo> result = new ArrayList<MenuInfo>();
		for (Integer menuId : userMenu) {
			MenuInfo menuInfo = menus.get(menuId);
			result.add(menuInfo);
		}
		return result;
	}

	private List<Integer> convertMenuIds(List<Integer> userMenu) {
		if (CollectionUtils.isNotEmpty(userMenu)) {
			List<Integer> result = new ArrayList<Integer>();
			for (Integer menuId : userMenu) {
				MenuInfo menuInfo = menus.get(menuId);
				Integer groupId = menuInfo.getMenuGroupId();
				if (groupId != MenuInfo.ROOT_MENU_ID) {
					if (!result.contains(result)) {
						result.add(groupId);
					}
				}
			}
			if (CollectionUtils.isNotEmpty(result)) {
				List<Integer> parentIds = convertMenuIds(result);
				if (parentIds != null) {
					result.addAll(parentIds);
				}
				result.addAll(userMenu);
				return result;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		menus = Maps.newHashMap();
		List<MenuInfo> allMenu = menuDao.findMenu();
		for (MenuInfo menu : allMenu) {
			menus.put(menu.getMenuId(), menu);
		}
	}

	@Override
	public boolean isMenu(int menuId) throws RestException {
		MenuInfo menuInfo = menus.get(menuId);
		if (menuInfo == null) {
			return false;
		} else {
			String isLeaf = menuInfo.getIsLeaf();
			return StringUtils.equals(isLeaf, MenuInfo.LEAF_YES);
		}
	}

	@Override
	public List<MenuInfo> getAllMenu() throws RestException {
		return menuDao.findMenu();
	}
}
