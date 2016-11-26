package com.gzzhwl.admin.source.vo;

import org.apache.commons.lang3.StringUtils;

import com.gzzhwl.core.utils.JodaDateUtils;

public class PushSourceVO {
	private java.lang.String sourceId;// 货源标识
	private java.lang.String needType; // 车型要求
	private java.lang.String needLength; // 车长要求
	private java.lang.String needDriverNum; // 要求司机人数
	private java.lang.String startTime; // 竞价开始时间
	private java.lang.String endTime; // 竞价结束时间
	private java.lang.String transitRequire; // 在途要求
	private java.lang.String estimatedPrice; // 预计应付
	private java.lang.String miles; // 公里数
	private java.lang.String level; // 优先级
	private java.lang.String remark; // 备注

	public java.lang.String getSourceId() {
		return sourceId;
	}

	public void setSourceId(java.lang.String sourceId) {
		this.sourceId = sourceId;
	}

	public java.lang.String getNeedType() {
		return needType;
	}

	public void setNeedType(java.lang.String needType) {
		this.needType = needType;
	}

	public java.lang.String getNeedLength() {
		return needLength;
	}

	public void setNeedLength(java.lang.String needLength) {
		this.needLength = needLength;
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

	public java.lang.String getTransitRequire() {
		return transitRequire;
	}

	public void setTransitRequire(java.lang.String transitRequire) {
		this.transitRequire = transitRequire;
	}

	public java.lang.String getEstimatedPrice() {
		return estimatedPrice;
	}

	public void setEstimatedPrice(java.lang.String estimatedPrice) {
		this.estimatedPrice = estimatedPrice;
	}

	public java.lang.String getMiles() {
		return miles;
	}

	public void setMiles(java.lang.String miles) {
		this.miles = miles;
	}

	public java.lang.String getLevel() {
		return level;
	}

	public void setLevel(java.lang.String level) {
		this.level = level;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
}
