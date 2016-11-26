package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_order_detail_info表
 * @author mew
 *
 */
@Data
@ToString
public class OrderDetailInfo implements Serializable {

	@Length(max = 36, message = "orderId超过长度限制")
	private java.lang.String orderId; // 订单标识

	@Length(max = 50, message = "orderNo超过长度限制")
	private java.lang.String orderNo; // 订单编号

	@Length(max = 36, message = "infoId超过长度限制")
	private java.lang.String infoId; // 信息标识

	@Length(max = 10, message = "goodsWeight超过长度限制")
	private java.lang.String goodsWeight; // 货物重量

	@Length(max = 10, message = "goodsVolume超过长度限制")
	private java.lang.String goodsVolume; // 货物体积

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 订单状态

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