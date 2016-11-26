package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_order_receiver_info表
 * @author mew
 *
 */
@Data
@ToString
public class OrderReceiverInfo implements Serializable {

	@Length(max = 36, message = "infoId超过长度限制")
	private java.lang.String infoId; // 订单标识

	@Length(max = 50, message = "customerName超过长度限制")
	private java.lang.String customerName; // 客户名称

	@Length(max = 50, message = "contactName超过长度限制")
	private java.lang.String contactName; // 联系人

	@Length(max = 20, message = "telphone超过长度限制")
	private java.lang.String telphone; // 联系电话

	@Length(max = 20, message = "mobile超过长度限制")
	private java.lang.String mobile; // 联系手机

	@Length(max = 10, message = "areaCode超过长度限制")
	private java.lang.String areaCode; // 所在区域

	@Length(max = 200, message = "address超过长度限制")
	private java.lang.String address; // 联系地址

	@Length(max = 20, message = "longitude超过长度限制")
	private java.lang.String longitude; // 经度

	@Length(max = 20, message = "latitude超过长度限制")
	private java.lang.String latitude; // 纬度
}