package com.gzzhwl.excel;

import com.gzzhwl.excel.annotations.ExcelFieldMappingByHeadName;
import com.gzzhwl.excel.annotations.ExcelObjectMappingByTableHead;
import com.gzzhwl.excel.enums.ParseType;

@ExcelObjectMappingByTableHead(sheetName = "hr", parseType = ParseType.ROW, headPosition = 1, headStart = 1, dataStart = 2)
public class AccountInfo {
	@ExcelFieldMappingByHeadName(headName = "工号")
	private java.lang.String number; // 账户标识
	@ExcelFieldMappingByHeadName(headName = "姓名")
	private java.lang.String name; // 昵称
	@ExcelFieldMappingByHeadName(headName = "departId")
	private java.lang.String departId; // 手机号
	@ExcelFieldMappingByHeadName(headName = "岗位名称")
	private java.lang.String position; // 密码
	@ExcelFieldMappingByHeadName(headName = "联系方式")
	private java.lang.String telphone; // 密码
	@ExcelFieldMappingByHeadName(headName = "邮箱")
	private java.lang.String email; // 密码

	public java.lang.String getNumber() {
		return number;
	}

	public void setNumber(java.lang.String number) {
		this.number = number;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getDepartId() {
		return departId;
	}

	public void setDepartId(java.lang.String departId) {
		this.departId = departId;
	}

	public java.lang.String getPosition() {
		return position;
	}

	public void setPosition(java.lang.String position) {
		this.position = position;
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

}