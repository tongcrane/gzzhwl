package com.gzzhwl.core.data.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.ToString;

/**
 * 
 * zh_real_driver_used_info表
 * 
 * @author mew
 *
 */
@Data
@ToString
public class RealDriverUsedInfo implements Serializable {

	@Length(max = 36, message = "driverInfoId超过长度限制")
	private java.lang.String driverInfoId; // 驾驶员信息标识

	@Length(max = 2, message = "useStatus超过长度限制")
	private java.lang.String useStatus; // 使用状态
}