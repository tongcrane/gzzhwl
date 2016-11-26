package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_sms_captcha表
 * @author mew
 *
 */
@Data
@ToString
public class SmsCaptcha implements Serializable {

	@Length(max = 36, message = "captchaId超过长度限制")
	private java.lang.String captchaId; // 验证码标识

	@Length(max = 36, message = "accountId超过长度限制")
	private java.lang.String accountId; // 账户标识

	@Length(max = 11, message = "telphone超过长度限制")
	private java.lang.String telphone; // 手机号

	@Length(max = 50, message = "captcha超过长度限制")
	private java.lang.String captcha; // 验证码

	@Length(max = 2, message = "type超过长度限制")
	private java.lang.String type; // 类型

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间

	@Length(max = 2, message = "isDeleted超过长度限制")
	private java.lang.String isDeleted; // 是否删除
	

	/**
     * 正常
     */
	public static final String STATUS_NORMAL = "00"; // 正常
	/**
     * 过期
     */
	public static final String STATUS_OVERDUE = "01"; // 过期

}