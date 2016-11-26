package com.gzzhwl.admin.security.model;

import java.util.List;

import com.gzzhwl.core.data.model.Menu;

public class AuthStaffInfo {
	private java.lang.String staffId; // 员工标识
	private java.lang.String number; // 工号
	private java.lang.String realName; // 姓名
	private java.lang.String position; // 职位
	private java.lang.String telphone; // 手机号
	private java.lang.String email; // 电子邮箱
	private java.lang.String departCode; // 所属部门
	private java.lang.String createdTime; // 创建时间
	private String token;
	private List<Integer> functionIds;// 权限列表
	private List<Menu> menus;

	public java.lang.String getStaffId() {
		return staffId;
	}

	public void setStaffId(java.lang.String staffId) {
		this.staffId = staffId;
	}

	public java.lang.String getNumber() {
		return number;
	}

	public void setNumber(java.lang.String number) {
		this.number = number;
	}

	public java.lang.String getRealName() {
		return realName;
	}

	public void setRealName(java.lang.String realName) {
		this.realName = realName;
	}

	public java.lang.String getPosition() {
		return position;
	}

	public void setPosition(java.lang.String position) {
		this.position = position;
	}

	public java.lang.String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(java.lang.String createdTime) {
		this.createdTime = createdTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public java.lang.String getTelphone() {
		return telphone;
	}

	public void setTelphone(java.lang.String telphone) {
		this.telphone = telphone;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getDepartCode() {
		return departCode;
	}

	public void setDepartCode(java.lang.String departCode) {
		this.departCode = departCode;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public List<Integer> getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(List<Integer> functionIds) {
		this.functionIds = functionIds;
	}

}
