package com.gzzhwl.tms.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderVO {
	private OrderBaseInfoVO baseInfo;
	private OrderSenderInfoVO sender;
	private OrderReceiverInfoVO receiver;
	private OrderLineInfoVO lineInfo;
	private OrderSourceVO sourceInfo;

}
