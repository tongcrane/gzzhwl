package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_account_verify_log表
 * @author mew
 *
 */
@Data
@ToString
public class AccountVerifyLog implements Serializable {

	private java.lang.Long pid; // 

	@Length(max = 36, message = "logId超过长度限制")
	private java.lang.String logId; // 记录标识

	@Length(max = 36, message = "targetId超过长度限制")
	private java.lang.String targetId; // 目标标识

	@Length(max = 2, message = "targetType超过长度限制")
	private java.lang.String targetType; // 目标类别

	@Length(max = 2, message = "srcStatus超过长度限制")
	private java.lang.String srcStatus; // 原始状态

	@Length(max = 2, message = "destStatus超过长度限制")
	private java.lang.String destStatus; // 目标状态

	@Length(max = 36, message = "createdBy超过长度限制")
	private java.lang.String createdBy; // 创建人

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间
}