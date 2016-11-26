package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_push_info表
 * @author mew
 *
 */
@Data
@ToString
public class PushInfo implements Serializable {

	@Length(max = 36, message = "accountId超过长度限制")
	private java.lang.String accountId; // 账户标识

	@Length(max = 36, message = "deviceId超过长度限制")
	private java.lang.String deviceId; // 设备标识

	@Length(max = 200, message = "token超过长度限制")
	private java.lang.String token; // 推送标识

	@Length(max = 36, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 登记时间

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 2, message = "isDeleted超过长度限制")
	private java.lang.String isDeleted; // 是否删除
}