package com.gzzhwl.admin.source.vo;

import com.gzzhwl.admin.order.vo.OrderBaseInfoVO;
import com.gzzhwl.admin.order.vo.OrderLineInfoVO;
import com.gzzhwl.admin.order.vo.OrderReceiverInfoVO;
import com.gzzhwl.admin.order.vo.OrderSenderInfoVO;
import com.gzzhwl.core.data.model.ChargeInfo;

public class SourceVo {
	private String orderNo;//订单编号
	private OrderBaseInfoVO orderBaseInfo;
	private ChargeInfo chargeInfo;
	private OrderSenderInfoVO orderSenderInfo;
	private OrderReceiverInfoVO orderReceiverInfo;
	private OrderLineInfoVO orderLineInfo;
	private PushSourceVO sourceInfo;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public OrderBaseInfoVO getOrderBaseInfo() {
		return orderBaseInfo;
	}
	public void setOrderBaseInfo(OrderBaseInfoVO orderBaseInfo) {
		this.orderBaseInfo = orderBaseInfo;
	}
	public OrderSenderInfoVO getOrderSenderInfo() {
		return orderSenderInfo;
	}
	public void setOrderSenderInfo(OrderSenderInfoVO orderSenderInfo) {
		this.orderSenderInfo = orderSenderInfo;
	}
	public OrderReceiverInfoVO getOrderReceiverInfo() {
		return orderReceiverInfo;
	}
	public void setOrderReceiverInfo(OrderReceiverInfoVO orderReceiverInfo) {
		this.orderReceiverInfo = orderReceiverInfo;
	}
	public OrderLineInfoVO getOrderLineInfo() {
		return orderLineInfo;
	}
	public void setOrderLineInfo(OrderLineInfoVO orderLineInfo) {
		this.orderLineInfo = orderLineInfo;
	}
	public PushSourceVO getSourceInfo() {
		return sourceInfo;
	}
	public void setSourceInfo(PushSourceVO sourceInfo) {
		this.sourceInfo = sourceInfo;
	}
	public ChargeInfo getChargeInfo() {
		return chargeInfo;
	}
	public void setChargeInfo(ChargeInfo chargeInfo) {
		this.chargeInfo = chargeInfo;
	}
	
}
