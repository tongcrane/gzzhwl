package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_quoted_his表
 * @author mew
 *
 */
@Data
@ToString
public class QuotedHis implements Serializable {

	@Length(max = 36, message = "hisId超过长度限制")
	private java.lang.String hisId; // 记录标识

	@Length(max = 36, message = "quotedId超过长度限制")
	private java.lang.String quotedId; // 报价标识

	@Length(max = 2, message = "srcStatus超过长度限制")
	private java.lang.String srcStatus; // 原始状态

	@Length(max = 2, message = "destStatus超过长度限制")
	private java.lang.String destStatus; // 目标状态

	@Length(max = 2, message = "userType超过长度限制")
	private java.lang.String userType; // 用户类型

	@Length(max = 400, message = "msgInfo超过长度限制")
	private java.lang.String msgInfo; // 消息信息

	@Length(max = 36, message = "createdBy超过长度限制")
	private java.lang.String createdBy; // 创建人

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间
}