package com.gzzhwl.rest.security.model;

import com.gzzhwl.core.data.model.StaffInfo;

public class Subject {
	private java.lang.String staffId; // 员工标识
	private java.lang.String number; // 工号
	private java.lang.String realName; // 姓名
	private java.lang.String position; // 职位
	private java.lang.String type; // 员工类型
	private java.lang.String status; // 状态
	private java.lang.String telphone; // 手机号
	private java.lang.String email; // 电子邮箱
	private java.lang.Integer departId; // 所属部门

	public Subject(StaffInfo staffInfo) {
		this.staffId = staffInfo.getStaffId();
		this.number = staffInfo.getNumber();
		this.realName = staffInfo.getRealName();
		this.position = staffInfo.getPosition();
		this.type = staffInfo.getType();
		this.status = staffInfo.getStatus();
		this.telphone = staffInfo.getTelphone();
		this.email = staffInfo.getEmail();
		this.departId = staffInfo.getDepartId();
	}

	public java.lang.String getStaffId() {
		return staffId;
	}

	public java.lang.String getNumber() {
		return number;
	}

	public java.lang.String getRealName() {
		return realName;
	}

	public java.lang.String getPosition() {
		return position;
	}

	public java.lang.String getType() {
		return type;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public java.lang.String getTelphone() {
		return telphone;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public java.lang.Integer getDepartId() {
		return departId;
	}

	@Override
	public String toString() {
		return "Subject [staffId=" + staffId + ", number=" + number + ", realName=" + realName + ", position="
				+ position + ", type=" + type + ", status=" + status + "]";
	}

}
