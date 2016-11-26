package com.gzzhwl.core.data.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.ToString;

/**
 * 
 * zh_push_record表
 * 
 * @author mew
 *
 */
@Data
@ToString
public class PushRecord implements Serializable {

	@Length(max = 36, message = "recordId超过长度限制")
	private java.lang.String recordId; // 记录标识

	@Length(max = 36, message = "accountId超过长度限制")
	private java.lang.String accountId; // 接收人

	@Length(max = 36, message = "deviceId超过长度限制")
	private java.lang.String deviceId; // 设备标识

	@Length(max = 200, message = "token超过长度限制")
	private java.lang.String token; // 推送标识

	private java.lang.String content; // 推送内容

	@Length(max = 36, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间

	@Length(max = 2, message = "result超过长度限制")
	private java.lang.String result; // 推送结果

	@Length(max = 250, message = "remark超过长度限制")
	private java.lang.String remark; // 其他备注
}