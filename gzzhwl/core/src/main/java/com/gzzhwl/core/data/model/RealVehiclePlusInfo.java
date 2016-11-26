package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_real_vehicle_plus_info表
 * @author mew
 *
 */
@Data
@ToString
public class RealVehiclePlusInfo implements Serializable {

	@Length(max = 36, message = "vehicleInfoId超过长度限制")
	private java.lang.String vehicleInfoId; // 车辆信息标识

	@Length(max = 20, message = "ciEffectDate超过长度限制")
	private java.lang.String ciEffectDate; // 交强险有效期(起)

	@Length(max = 20, message = "ciValidDate超过长度限制")
	private java.lang.String ciValidDate; // 交强险有效期(止)

	@Length(max = 50, message = "ciName超过长度限制")
	private java.lang.String ciName; // 交强险公司名称

	@Length(max = 20, message = "ciNo超过长度限制")
	private java.lang.String ciNo; // 交强险保单号

	@Length(max = 20, message = "viEffectDate超过长度限制")
	private java.lang.String viEffectDate; // 商业险有效期(起)

	@Length(max = 20, message = "viValidDate超过长度限制")
	private java.lang.String viValidDate; // 商业险有效期(止)

	@Length(max = 50, message = "viName超过长度限制")
	private java.lang.String viName; // 商业险公司名称

	@Length(max = 20, message = "viNo超过长度限制")
	private java.lang.String viNo; // 商业险保单号

	@Length(max = 36, message = "ciImageRefId超过长度限制")
	private java.lang.String ciImageRefId; // 交强险图片

	@Length(max = 36, message = "viImageRefId超过长度限制")
	private java.lang.String viImageRefId; // 商业险图片
}