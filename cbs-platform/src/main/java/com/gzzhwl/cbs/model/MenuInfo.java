package com.gzzhwl.cbs.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MenuInfo {

	public static final String LEAF_YES = "1";
	public static final String LEAF_NO = "0";
	public static final Integer ROOT_MENU_ID = 0;

	private java.lang.Integer menuId; // 菜单标识

	private java.lang.String menuName; // 菜单名称

	private java.lang.String menuPath; // 菜单访问路径

	private java.lang.Integer menuGroupId; // 所属分组

	private java.lang.String isLeaf; // 是否为菜单

	private java.lang.String description; // 菜单描述

	private java.lang.String status; // 状态
}