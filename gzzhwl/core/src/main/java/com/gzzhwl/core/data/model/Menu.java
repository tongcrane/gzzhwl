package com.gzzhwl.core.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * zh_menu表
 * 
 * @author mew
 *
 */
public class Menu implements Serializable {
	private java.lang.Integer menuId; // 菜单标识
	private java.lang.String menuName; // 菜单名称
	private java.lang.String menuPath; // 菜单访问路径
	private java.lang.String isLeaf; // 是否为菜单
	private java.lang.String description; // 菜单描述
	private List<Menu> subMenu;

	/**
	 * 获取菜单标识属性
	 *
	 * @return menuId
	 */
	public java.lang.Integer getMenuId() {
		return menuId;
	}

	/**
	 * 设置菜单标识属性
	 *
	 * @param menuId
	 */
	public void setMenuId(java.lang.Integer menuId) {
		this.menuId = menuId;
	}

	/**
	 * 获取菜单名称属性
	 *
	 * @return menuName
	 */
	public java.lang.String getMenuName() {
		return menuName;
	}

	/**
	 * 设置菜单名称属性
	 *
	 * @param menuName
	 */
	public void setMenuName(java.lang.String menuName) {
		this.menuName = menuName;
	}

	/**
	 * 获取菜单访问路径属性
	 *
	 * @return menuPath
	 */
	public java.lang.String getMenuPath() {
		return menuPath;
	}

	/**
	 * 设置菜单访问路径属性
	 *
	 * @param menuPath
	 */
	public void setMenuPath(java.lang.String menuPath) {
		this.menuPath = menuPath;
	}

	/**
	 * 获取是否为菜单属性
	 *
	 * @return isLeaf
	 */
	public java.lang.String getIsLeaf() {
		return isLeaf;
	}

	/**
	 * 设置是否为菜单属性
	 *
	 * @param isLeaf
	 */
	public void setIsLeaf(java.lang.String isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * 获取菜单描述属性
	 *
	 * @return description
	 */
	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * 设置菜单描述属性
	 *
	 * @param description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public List<Menu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<Menu> subMenu) {
		this.subMenu = subMenu;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Menu");
		sb.append("{menuId=").append(menuId);
		sb.append(", menuName=").append(menuName);
		sb.append(", menuPath=").append(menuPath);
		sb.append(", isLeaf=").append(isLeaf);
		sb.append(", description=").append(description);
		sb.append('}');
		return sb.toString();
	}
}