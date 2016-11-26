package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_charge_info表
 * @author mew
 *
 */
@Data
@ToString
public class ChargeInfo implements Serializable {

	@Length(max = 36, message = "chargeId超过长度限制")
	private java.lang.String chargeId; // 计费标识

	@Length(max = 10, message = "name超过长度限制")
	private java.lang.String name; // 名称

	@Length(max = 10, message = "unit超过长度限制")
	private java.lang.String unit; // 单位
}