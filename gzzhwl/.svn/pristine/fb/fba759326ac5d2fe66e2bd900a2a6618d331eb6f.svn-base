package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_real_vehicle_used_info表
 * @author mew
 *
 */
@Data
@ToString
public class RealVehicleUsedInfo implements Serializable {

	@Length(max = 36, message = "vehicleInfoId超过长度限制")
	private java.lang.String vehicleInfoId; // 车辆信息标识

	@Length(max = 36, message = "belongDepartId超过长度限制")
	private java.lang.String belongDepartId; // 所属部门

	@Length(max = 50, message = "belongDepartName超过长度限制")
	private java.lang.String belongDepartName; // 所属部门名称

	@Length(max = 36, message = "useDepartId超过长度限制")
	private java.lang.String useDepartId; // 使用部门

	@Length(max = 50, message = "useDepartName超过长度限制")
	private java.lang.String useDepartName; // 使用部门名称

	@Length(max = 10, message = "departureCode超过长度限制")
	private java.lang.String departureCode; // 线路出发地

	@Length(max = 10, message = "destinationCode超过长度限制")
	private java.lang.String destinationCode; // 线路目的地

	@Length(max = 2, message = "useStatus超过长度限制")
	private java.lang.String useStatus; // 使用状态
}