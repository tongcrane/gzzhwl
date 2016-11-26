package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_menu_info表
 * @author mew
 *
 */
@Data
@ToString
public class MenuInfo implements Serializable {
	
	public static final String LEAF_YES = "1";
	public static final String LEAF_NO = "0";
	public static final Integer ROOT_MENU_ID = 0;
	

	private java.lang.Integer menuId; // 菜单标识

	@Length(max = 100, message = "menuName超过长度限制")
	private java.lang.String menuName; // 菜单名称

	@Length(max = 200, message = "menuPath超过长度限制")
	private java.lang.String menuPath; // 菜单访问路径

	private java.lang.Integer menuGroupId; // 所属分组

	@Length(max = 2, message = "isLeaf超过长度限制")
	private java.lang.String isLeaf; // 是否为菜单

	@Length(max = 50, message = "description超过长度限制")
	private java.lang.String description; // 菜单描述

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态
}