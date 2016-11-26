package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_staff_info表
 * @author mew
 *
 */
@Data
@ToString
public class StaffInfo implements Serializable {

	@Length(max = 36, message = "staffId超过长度限制")
	private java.lang.String staffId; // 员工标识

	@Length(max = 20, message = "number超过长度限制")
	private java.lang.String number; // 工号

	@Length(max = 32, message = "password超过长度限制")
	private java.lang.String password; // 密码

	@Length(max = 40, message = "realName超过长度限制")
	private java.lang.String realName; // 姓名

	@Length(max = 50, message = "position超过长度限制")
	private java.lang.String position; // 职位

	@Length(max = 20, message = "telphone超过长度限制")
	private java.lang.String telphone; // 手机号

	@Length(max = 50, message = "email超过长度限制")
	private java.lang.String email; // 电子邮箱

	private java.lang.Integer departId; // 所属部门

	@Length(max = 2, message = "type超过长度限制")
	private java.lang.String type; // 员工类型

	@Length(max = 36, message = "createdBy超过长度限制")
	private java.lang.String createdBy; // 创建人

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间

	@Length(max = 20, message = "updatedTime超过长度限制")
	private java.lang.String updatedTime; // 修改时间

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 2, message = "isDeleted超过长度限制")
	private java.lang.String isDeleted; // 是否删除
	
	public static final String STAFF_TYPE="02";  //员工

}