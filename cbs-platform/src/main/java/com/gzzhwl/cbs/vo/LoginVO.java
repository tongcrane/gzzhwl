package com.gzzhwl.cbs.vo;

import java.util.List;

import com.gzzhwl.cbs.model.Subject;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginVO {
	private String position;
	private String staffId;
	private String departCode;
	private String token;
	private String email;
	private String createdTime;
	private String realName;
	private String number;
	private String telphone;
	private List<Integer> functionIds;// 权限列表

	public Subject getSubject() {
		Subject subject = new Subject();
		subject.setDepartCode(departCode);
		subject.setEmail(email);
		subject.setNumber(number);
		subject.setPosition(position);
		subject.setRealName(realName);
		subject.setStaffId(staffId);
		subject.setTelphone(telphone);
		subject.setToken(token);
		subject.setFunctionIds(functionIds);
		return subject;
	}
}
