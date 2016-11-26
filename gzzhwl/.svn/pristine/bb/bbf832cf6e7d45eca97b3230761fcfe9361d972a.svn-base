package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_order_line_info表
 * @author mew
 *
 */
@Data
@ToString
public class OrderLineInfo implements Serializable {

	@Length(max = 36, message = "infoId超过长度限制")
	private java.lang.String infoId; // 订单标识

	@Length(max = 10, message = "startCodeP超过长度限制")
	private java.lang.String startCodeP; // 起始区域(省)

	@Length(max = 10, message = "startCodeC超过长度限制")
	private java.lang.String startCodeC; // 起始区域(市)

	@Length(max = 10, message = "transferCodeP超过长度限制")
	private java.lang.String transferCodeP; // 中转区域(省)

	@Length(max = 10, message = "transferCodeC超过长度限制")
	private java.lang.String transferCodeC; // 中转区域(市)

	@Length(max = 10, message = "endCodeP超过长度限制")
	private java.lang.String endCodeP; // 终点区域(省)

	@Length(max = 10, message = "endCodeC超过长度限制")
	private java.lang.String endCodeC; // 终点区域(市)
}