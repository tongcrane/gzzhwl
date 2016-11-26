package com.gzzhwl.admin.order.vo;

import org.apache.commons.lang3.StringUtils;

import com.gzzhwl.core.utils.JodaDateUtils;

public class PushOrderVO {
	private java.lang.String orderId; // 订单标识
	private java.lang.String needDriverNum; // 要求司机人数
	private java.lang.String startTime; // 竞价开始时间
	private java.lang.String endTime; // 竞价结束时间

	public java.lang.String getOrderId() {
		return orderId;
	}

	public void setOrderId(java.lang.String orderId) {
		this.orderId = orderId;
	}

	public java.lang.String getNeedDriverNum() {
		return needDriverNum;
	}

	public void setNeedDriverNum(java.lang.String needDriverNum) {
		this.needDriverNum = needDriverNum;
	}

	public java.lang.String getStartTime() {
		if (StringUtils.isNotBlank(startTime)) {
			return JodaDateUtils.parse(startTime, "yyyy-MM-dd HH:mm").toString("yyyy-MM-dd HH:mm:ss");
		}
		return startTime;
	}

	public void setStartTime(java.lang.String startTime) {
		this.startTime = startTime;
	}

	public java.lang.String getEndTime() {
		if (StringUtils.isNotBlank(endTime)) {
			return JodaDateUtils.parse(endTime, "yyyy-MM-dd HH:mm").toString("yyyy-MM-dd HH:mm:ss");
		}
		return endTime;
	}

	public void setEndTime(java.lang.String endTime) {
		this.endTime = endTime;
	}

}
