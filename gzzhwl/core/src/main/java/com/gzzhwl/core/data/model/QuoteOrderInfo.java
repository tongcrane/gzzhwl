package com.gzzhwl.core.data.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QuoteOrderInfo {
	private String orderId;// 订单标识
	private String belongDepartId;// 所属部门
	private String paymentType;// 结算方式
	private String pickUpTime;// 提货时间
}
