package com.gzzhwl.admin.order.vo;

public class OrderVO {
	private OrderBaseInfoVO baseInfo;
	private OrderSenderInfoVO sender;
	private OrderReceiverInfoVO receiver;
	private OrderLineInfoVO lineInfo;

	public OrderBaseInfoVO getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(OrderBaseInfoVO baseInfo) {
		this.baseInfo = baseInfo;
	}

	public OrderSenderInfoVO getSender() {
		return sender;
	}

	public void setSender(OrderSenderInfoVO sender) {
		this.sender = sender;
	}

	public OrderReceiverInfoVO getReceiver() {
		return receiver;
	}

	public void setReceiver(OrderReceiverInfoVO receiver) {
		this.receiver = receiver;
	}

	public OrderLineInfoVO getLineInfo() {
		return lineInfo;
	}

	public void setLineInfo(OrderLineInfoVO lineInfo) {
		this.lineInfo = lineInfo;
	}

}
