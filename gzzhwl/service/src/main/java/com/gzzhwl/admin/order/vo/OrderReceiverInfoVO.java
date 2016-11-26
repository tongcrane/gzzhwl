package com.gzzhwl.admin.order.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderReceiverInfoVO {
	private java.lang.String customerName; // 客户名称
	private java.lang.String contactName; // 联系人
	private java.lang.String telphone; // 联系电话
	private java.lang.String mobile; // 联系手机
	private java.lang.String areaCode; // 所在区域
	private java.lang.String address; // 联系地址
	private java.lang.String longitude; // 经度
	private java.lang.String latitude; // 纬度
}