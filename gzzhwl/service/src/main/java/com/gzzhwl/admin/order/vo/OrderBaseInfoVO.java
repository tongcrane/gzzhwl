package com.gzzhwl.admin.order.vo;

import org.apache.commons.lang3.StringUtils;

import com.gzzhwl.core.utils.JodaDateUtils;

public class OrderBaseInfoVO {
	private java.lang.String orderId; // 订单标识
	private java.lang.String agreementId; // 所用合同
	private java.lang.String needType; // 车型要求
	private java.lang.String needLength; // 车长要求
	private java.lang.String needStartTime; // 要求发车时间
	private java.lang.String needArriveTime; // 要求到达时间
	private java.lang.String orderType; // 订单类型
	private java.lang.String lineAttribute; // 线路属性
	private java.lang.String goodsName; // 货品名称
	private java.lang.String goodsWeight; // 货物重量
	private java.lang.String goodsVolume; // 货物体积
	private java.lang.String needImportedVehicles; // 要求进口车
	private java.lang.String needOwnVehicles; // 要求志鸿车
	private java.lang.String chargeId; // 计费标识
	private java.lang.String unitPrice; // 单价
	private java.lang.String miles; // 公里数
	private java.lang.String paymentType; // 结算方式
	private java.lang.String orderAdvice; // 回单要求
	private java.lang.String customerBillNo; // 客户单号
	private java.lang.String remark; // 备注
	private java.lang.String addrId; // 货场地址标识
	private java.lang.String pickUpTime; // 要求达到场地时间
	private java.lang.String isPredict; // 是否预估

	public java.lang.String getOrderId() {
		return orderId;
	}

	public void setOrderId(java.lang.String orderId) {
		this.orderId = orderId;
	}

	public java.lang.String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(java.lang.String agreementId) {
		this.agreementId = agreementId;
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

	public java.lang.String getNeedStartTime() {
		if (StringUtils.isNotBlank(needStartTime)) {
			return JodaDateUtils.parse(needStartTime, "yyyy-MM-dd HH:mm").toString("yyyy-MM-dd HH:mm:ss");
		}
		return needStartTime;
	}

	public void setNeedStartTime(java.lang.String needStartTime) {
		this.needStartTime = needStartTime;
	}

	public java.lang.String getNeedArriveTime() {
		if (StringUtils.isNotBlank(needArriveTime)) {
			return JodaDateUtils.parse(needArriveTime, "yyyy-MM-dd HH:mm").toString("yyyy-MM-dd HH:mm:ss");
		}
		return needArriveTime;
	}

	public void setNeedArriveTime(java.lang.String needArriveTime) {
		this.needArriveTime = needArriveTime;
	}

	public java.lang.String getOrderType() {
		return orderType;
	}

	public void setOrderType(java.lang.String orderType) {
		this.orderType = orderType;
	}

	public java.lang.String getLineAttribute() {
		return lineAttribute;
	}

	public void setLineAttribute(java.lang.String lineAttribute) {
		this.lineAttribute = lineAttribute;
	}

	public java.lang.String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(java.lang.String goodsName) {
		this.goodsName = goodsName;
	}

	public java.lang.String getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(java.lang.String goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public java.lang.String getGoodsVolume() {
		return goodsVolume;
	}

	public void setGoodsVolume(java.lang.String goodsVolume) {
		this.goodsVolume = goodsVolume;
	}

	public java.lang.String getNeedImportedVehicles() {
		return needImportedVehicles;
	}

	public void setNeedImportedVehicles(java.lang.String needImportedVehicles) {
		this.needImportedVehicles = needImportedVehicles;
	}

	public java.lang.String getNeedOwnVehicles() {
		return needOwnVehicles;
	}

	public void setNeedOwnVehicles(java.lang.String needOwnVehicles) {
		this.needOwnVehicles = needOwnVehicles;
	}

	public java.lang.String getChargeId() {
		return chargeId;
	}

	public void setChargeId(java.lang.String chargeId) {
		this.chargeId = chargeId;
	}

	public java.lang.String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(java.lang.String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public java.lang.String getMiles() {
		return miles;
	}

	public void setMiles(java.lang.String miles) {
		this.miles = miles;
	}

	public java.lang.String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(java.lang.String paymentType) {
		this.paymentType = paymentType;
	}

	public java.lang.String getOrderAdvice() {
		return orderAdvice;
	}

	public void setOrderAdvice(java.lang.String orderAdvice) {
		this.orderAdvice = orderAdvice;
	}

	public java.lang.String getCustomerBillNo() {
		return customerBillNo;
	}

	public void setCustomerBillNo(java.lang.String customerBillNo) {
		this.customerBillNo = customerBillNo;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	public java.lang.String getAddrId() {
		return addrId;
	}

	public void setAddrId(java.lang.String addrId) {
		this.addrId = addrId;
	}

	public java.lang.String getPickUpTime() {
		if (StringUtils.isNotBlank(pickUpTime)) {
			return JodaDateUtils.parse(pickUpTime, "yyyy-MM-dd HH:mm").toString("yyyy-MM-dd HH:mm:ss");
		}
		return pickUpTime;
	}

	public void setPickUpTime(java.lang.String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}

	public java.lang.String getIsPredict() {
		return isPredict;
	}

	public void setIsPredict(java.lang.String isPredict) {
		this.isPredict = isPredict;
	}

}
