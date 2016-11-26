package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_account_operation_log表
 * @author mew
 *
 */
@Data
@ToString
public class AccountOperationLog implements Serializable {

	@Length(max = 36, message = "logId超过长度限制")
	private java.lang.String logId; // 记录标识

	@Length(max = 36, message = "accountId超过长度限制")
	private java.lang.String accountId; // 账户标识

	@Length(max = 2, message = "srcStatus超过长度限制")
	private java.lang.String srcStatus; // 原始状态

	@Length(max = 2, message = "destStatus超过长度限制")
	private java.lang.String destStatus; // 目标状态

	@Length(max = 36, message = "createdBy超过长度限制")
	private java.lang.String createdBy; // 创建人

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间
}