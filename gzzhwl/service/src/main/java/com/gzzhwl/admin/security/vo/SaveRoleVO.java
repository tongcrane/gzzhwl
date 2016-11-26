package com.gzzhwl.admin.security.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class SaveRoleVO {
	private java.lang.String roleId; // 角色标识
	private java.lang.String name; // 角色名称
	private java.lang.String description; // 角色描述
	private java.lang.String menuIds; // 角色描述

	public java.lang.String getRoleId() {
		return roleId;
	}

	public void setRoleId(java.lang.String roleId) {
		this.roleId = roleId;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(java.lang.String menuIds) {
		this.menuIds = menuIds;
	}

	public List<Integer> getMenuList() {
		if (this.menuIds == null) {
			return null;
		}
		String[] ids = StringUtils.split(this.menuIds, ",");
		List<Integer> result = new ArrayList<Integer>(ids.length);
		for (String id : ids) {
			result.add(Integer.valueOf(id));
		}
		return result;
	}

}
