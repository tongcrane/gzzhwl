package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_push_device表
 * @author mew
 *
 */
@Data
@ToString
public class PushDevice implements Serializable {

	@Length(max = 36, message = "deviceId超过长度限制")
	private java.lang.String deviceId; // 设备标识

	@Length(max = 50, message = "alias超过长度限制")
	private java.lang.String alias; // 别名

	@Length(max = 250, message = "name超过长度限制")
	private java.lang.String name; // 设备名称

	@Length(max = 2, message = "type超过长度限制")
	private java.lang.String type; // 类型

	@Length(max = 2, message = "channel超过长度限制")
	private java.lang.String channel; // 渠道

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 2, message = "isDeleted超过长度限制")
	private java.lang.String isDeleted; // 是否删除
}