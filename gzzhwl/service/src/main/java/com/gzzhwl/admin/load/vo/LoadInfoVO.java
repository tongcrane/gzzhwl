package com.gzzhwl.admin.load.vo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoadInfoVO {
	private java.lang.String loadId; // 提货单标识
	private java.lang.String vehicleInfoId; // 车辆信息标识
	private java.lang.String loadInfoId; // 车挂信息标识
	private java.lang.String freightPrice; // 运费
	private java.lang.String isPredict; // 是否预估
	private java.lang.String prePay; // 预付现金
	private java.lang.String oilPay; // 预付油费
	private java.lang.String oilCardNo; // 油卡卡号
	private java.lang.String supplyId; // 结算对象
	private java.lang.String remark; // 备注
	private List<LoadDriverVO> driverList; // 司机信息标识

}
