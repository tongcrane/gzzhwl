package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_quoted_plus_info表
 * @author mew
 *
 */
@Data
@ToString
public class QuotedPlusInfo implements Serializable {

	@Length(max = 36, message = "quotedId超过长度限制")
	private java.lang.String quotedId; // 报价标识

	@Length(max = 36, message = "vehicleInfoId超过长度限制")
	private java.lang.String vehicleInfoId; // 车辆标识

	@Length(max = 36, message = "driverInfoId超过长度限制")
	private java.lang.String driverInfoId; // 司机标识
}