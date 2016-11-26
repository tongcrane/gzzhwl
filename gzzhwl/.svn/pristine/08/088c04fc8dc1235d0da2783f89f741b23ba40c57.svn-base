package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_order_return_log表
 * @author mew
 *
 */
@Data
@ToString
public class OrderReturnLog implements Serializable {

	private java.lang.Long pid; // 自增标识

	@Length(max = 36, message = "logId超过长度限制")
	private java.lang.String logId; // 记录标识

	@Length(max = 36, message = "sourceId超过长度限制")
	private java.lang.String sourceId; // 货源标识

	@Length(max = 36, message = "orderId超过长度限制")
	private java.lang.String orderId; // 订单标识

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 100, message = "remark超过长度限制")
	private java.lang.String remark; // 备注

	@Length(max = 36, message = "createdBy超过长度限制")
	private java.lang.String createdBy; // 创建人

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间

	@Length(max = 36, message = "updatedBy超过长度限制")
	private java.lang.String updatedBy; // 修改人

	@Length(max = 20, message = "updatedTime超过长度限制")
	private java.lang.String updatedTime; // 修改时间

	@Length(max = 2, message = "isDeleted超过长度限制")
	private java.lang.String isDeleted; // 是否删除
}