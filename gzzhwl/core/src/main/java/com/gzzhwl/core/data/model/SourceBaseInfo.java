package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_source_base_info表
 * @author mew
 *
 */
@Data
@ToString
public class SourceBaseInfo implements Serializable {

	@Length(max = 36, message = "infoId超过长度限制")
	private java.lang.String infoId; // 信息标识

	@Length(max = 50, message = "sourceNo超过长度限制")
	private java.lang.String sourceNo; // 货源编号

	@Length(max = 36, message = "orderId超过长度限制")
	private java.lang.String orderId; // 订单标识
}