package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_load_driver_info表
 * @author mew
 *
 */
@Data
@ToString
public class LoadDriverInfo implements Serializable {

	@Length(max = 36, message = "loadId超过长度限制")
	private java.lang.String loadId; // 提货单标识

	@Length(max = 36, message = "driverInfoId超过长度限制")
	private java.lang.String driverInfoId; // 司机信息标识

	@Length(max = 2, message = "isMajor超过长度限制")
	private java.lang.String isMajor; // 是否主司机
	
	
	public static final String MAJOR_NO="00";
	public static final String MAJOR_YES="01";
	
}