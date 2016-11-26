package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_real_vehicle_driver_info表
 * @author mew
 *
 */
@Data
@ToString
public class RealVehicleDriverInfo implements Serializable {

	@Length(max = 36, message = "accountId超过长度限制")
	private java.lang.String accountId; // 账户标识

	@Length(max = 36, message = "vehicleInfoId超过长度限制")
	private java.lang.String vehicleInfoId; // 车辆信息标识

	@Length(max = 36, message = "driverInfoId超过长度限制")
	private java.lang.String driverInfoId; // 驾驶员信息标识
}