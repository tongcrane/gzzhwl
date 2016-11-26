package com.gzzhwl.api.vehicle.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VehicleVo {
	private String vehicleInfoId;// 车辆信息标识

	private String plateNumber;// 车牌号
	
	private String type;//车型
	
	private String length;//车长

	private String licenseImageRefId;// 行驶证照片
	private java.lang.String licenseImage2RefId; // 行驶证照片2
	
	private String ocImageRefId;//营运证照片
	
	private java.lang.String ocImage2RefId; // 营运证照片2
	private java.lang.String ocImage3RefId; // 营运证照片3

	private String ciImageRefId; // 强制险图片

	private String viImageRefId; // 商业险图片

	private String status;// 审核状态

}
