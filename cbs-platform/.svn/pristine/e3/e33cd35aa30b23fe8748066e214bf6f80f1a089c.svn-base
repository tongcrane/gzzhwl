package com.gzzhwl.cbs.support;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.gzzhwl.cbs.model.MenuInfo;
import com.gzzhwl.cbs.service.MenuService;
import com.gzzhwl.cbs.vo.MenuVO;

public class MenuSupport {

	public static List<MenuVO> getMenu(List<Integer> userMenu) {
		MenuService menusService = (MenuService) SpringContext.getBean(MenuService.class);
		return menusService.getUserMenu(userMenu);
	}

	public static MenuVO getFirstFun(List<MenuVO> subMenu) {
		if (CollectionUtils.isEmpty(subMenu)) {
			return null;
		}
		MenuVO first = subMenu.get(0);
		if (StringUtils.equals(first.getIsLeaf(), MenuInfo.LEAF_YES)) {
			return first;
		} else {
			return getFirstFun(first.getSubMenu());
		}
	}
}
