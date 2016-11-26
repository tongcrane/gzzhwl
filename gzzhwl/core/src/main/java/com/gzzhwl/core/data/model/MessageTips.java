package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_message_tips表
 * @author mew
 *
 */
@Data
@ToString
public class MessageTips implements Serializable {
	
	public static final String IS_READ_TRUE="01"; //已读
	public static final String IS_READ_FALSE="00";  //未读

	@Length(max = 36, message = "messageId超过长度限制")
	private java.lang.String messageId; // 消息标识

	@Length(max = 2, message = "category超过长度限制")
	private java.lang.String category; // 消息分类

	@Length(max = 36, message = "accountId超过长度限制")
	private java.lang.String accountId; // 消息接收人

	@Length(max = 36, message = "sourceId超过长度限制")
	private java.lang.String sourceId; // 消息来源标识

	@Length(max = 2, message = "sourceType超过长度限制")
	private java.lang.String sourceType; // 来源类型

	@Length(max = 65535, message = "content超过长度限制")
	private java.lang.String content; // 消息内容

	@Length(max = 36, message = "createdBy超过长度限制")
	private java.lang.String createdBy; // 创建人

	@Length(max = 36, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间

	@Length(max = 36, message = "updatedBy超过长度限制")
	private java.lang.String updatedBy; // 修改人

	@Length(max = 36, message = "updatedTime超过长度限制")
	private java.lang.String updatedTime; // 修改时间

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 2, message = "isRead超过长度限制")
	private java.lang.String isRead; // 是否已读

	@Length(max = 2, message = "isDeleted超过长度限制")
	private java.lang.String isDeleted; // 是否删除
}