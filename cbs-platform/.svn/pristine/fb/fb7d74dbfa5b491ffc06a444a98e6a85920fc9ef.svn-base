package com.gzzhwl.cbs.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.list.SetUniqueList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.gzzhwl.cbs.exception.HttpException;
import com.gzzhwl.cbs.exception.RestException;
import com.gzzhwl.cbs.model.HttpConfig;
import com.gzzhwl.cbs.model.MenuInfo;
import com.gzzhwl.cbs.model.RemotingResp;
import com.gzzhwl.cbs.service.MenuService;
import com.gzzhwl.cbs.support.HttpSupport;
import com.gzzhwl.cbs.vo.MenuVO;

@Service
public class MenuServiceImpl implements MenuService, InitializingBean {
	@Autowired
	private HttpSupport httpSupport;

	private Map<Integer, MenuInfo> menus;

	@Override
	public List<MenuVO> getUserMenu(List<Integer> userMenu) {
		List<Integer> allMenuIds = convertMenuIds(userMenu);
		if (CollectionUtils.isNotEmpty(allMenuIds)) {
			Collections.sort(allMenuIds);
			SetUniqueList.decorate(allMenuIds);
			List<MenuInfo> allMenu = this.getAllMenus(allMenuIds);
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
					if (!result.contains(groupId)) {
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

	private List<MenuVO> getMenu(MenuInfo parent, List<MenuInfo> allMenu) {
		if (StringUtils.equals(parent.getIsLeaf(), MenuInfo.LEAF_NO)) {
			List<MenuVO> result = new ArrayList<MenuVO>();
			for (MenuInfo menuInfo : allMenu) {
				if (menuInfo.getMenuGroupId().compareTo(parent.getMenuId()) == 0) {
					MenuVO info = new MenuVO();
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
	public void afterPropertiesSet() throws Exception {
		menus = new HashMap<Integer, MenuInfo>();
		List<MenuInfo> allMenu = this.getAllMenu();
		for (MenuInfo menu : allMenu) {
			menus.put(menu.getMenuId(), menu);
		}
	}

	@Override
	public List<MenuInfo> getAllMenu() throws RestException {
		String url = "/admin/menu/list";
		try {
			String json = httpSupport.getAsParam(url, null, HttpConfig.createDefault());
			RemotingResp<List<MenuInfo>> resp = JSON.parseObject(json,
					new TypeReference<RemotingResp<List<MenuInfo>>>() {
					});
			if (resp.isSuccess()) {
				return resp.getData();
			} else {
				throw new RestException(resp.getStatus());
			}
		} catch (HttpException e) {
			throw new RestException("90009", "无法连接到接口服务器");
		} catch (RestException e) {
			throw e;
		} catch (Exception e) {
			throw new RestException("90008", "调用接口发生内部错误");
		}
	}

}
