package com.gzzhwl.core.data.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.ToString;

/**
 * 
 * zh_internal_driver表
 * 
 * @author mew
 *
 */
@Data
@ToString
public class InternalDriver implements Serializable {

	@Length(max = 36, message = "accountId超过长度限制")
	private java.lang.String accountId; // 账户标识

	@Length(max = 2, message = "isStaff超过长度限制")
	private java.lang.String isStaff; // 内部司机

	public static final String YES = "00";// 是内部司机
	public static final String NO = "01";// 不是内部司机
}