package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_reset_password_log表
 * @author mew
 *
 */
@Data
@ToString
public class ResetPasswordLog implements Serializable {

	@Length(max = 36, message = "ticketId超过长度限制")
	private java.lang.String ticketId; // 票据标识

	@Length(max = 36, message = "staffId超过长度限制")
	private java.lang.String staffId; // 员工标识

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 36, message = "createdBy超过长度限制")
	private java.lang.String createdBy; // 创建人

	@Length(max = 36, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 获取时间

	@Length(max = 36, message = "expirationTime超过长度限制")
	private java.lang.String expirationTime; // 失效时间
	
	private Integer minuteDiff;//当前时间与获取时间的间隔（分钟）

}