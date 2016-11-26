package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_account_info表
 * @author mew
 *
 */
@Data
@ToString
public class AccountInfo implements Serializable {

	@Length(max = 36, message = "accountId超过长度限制")
	private java.lang.String accountId; // 账户标识

	@Length(max = 11, message = "telphone超过长度限制")
	private java.lang.String telphone; // 手机号

	@Length(max = 32, message = "password超过长度限制")
	private java.lang.String password; // 密码

	@Length(max = 50, message = "nickName超过长度限制")
	private java.lang.String nickName; // 昵称

	@Length(max = 36, message = "userHead超过长度限制")
	private java.lang.String userHead; // 用户头像

	@Length(max = 2, message = "accountSource超过长度限制")
	private java.lang.String accountSource; // 账户来源

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间

	@Length(max = 20, message = "updatedTime超过长度限制")
	private java.lang.String updatedTime; // 修改时间

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 2, message = "isDeleted超过长度限制")
	private java.lang.String isDeleted; // 是否删除
	
	private java.lang.String isStaff; // 是否是内部司机
	
}