package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_staff_login_history表
 * @author mew
 *
 */
@Data
@ToString
public class StaffLoginHistory implements Serializable {

	@Length(max = 36, message = "loginHistoryId超过长度限制")
	private java.lang.String loginHistoryId; // 历史标识

	@Length(max = 36, message = "accountId超过长度限制")
	private java.lang.String accountId; // 账户标识

	@Length(max = 36, message = "accessToken超过长度限制")
	private java.lang.String accessToken; // 登录凭证

	@Length(max = 2, message = "loginType超过长度限制")
	private java.lang.String loginType; // 登陆方式

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 20, message = "loginTime超过长度限制")
	private java.lang.String loginTime; // 登陆时间

	@Length(max = 20, message = "logoutTime超过长度限制")
	private java.lang.String logoutTime; // 登出时间
}