package com.gzzhwl.admin.agent.model;

import java.io.Serializable;

/**
 * 经纪人查询VO
 * @author UI-PC
 *
 */
public class AgentInfoVO implements Serializable{
	private String realName; // 经纪人姓名
	private java.lang.String sex; // 性别
	private java.lang.String idno; // 身份证号
	private java.lang.String telphone; // 联系电话
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public java.lang.String getSex() {
		return sex;
	}
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}
	public java.lang.String getIdno() {
		return idno;
	}
	public void setIdno(java.lang.String idno) {
		this.idno = idno;
	}
	public java.lang.String getTelphone() {
		return telphone;
	}
	public void setTelphone(java.lang.String telphone) {
		this.telphone = telphone;
	}
	
	
}
