package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_lend_log表
 * @author mew
 *
 */
@Data
@ToString
public class LendLog implements Serializable {

	@Length(max = 36, message = "lendId超过长度限制")
	private java.lang.String lendId; // 借调标识

	@Length(max = 50, message = "lendNo超过长度限制")
	private java.lang.String lendNo; // 借调单号

	@Length(max = 36, message = "vehicleInfoId超过长度限制")
	private java.lang.String vehicleInfoId; // 车辆信息标识

	@Length(max = 36, message = "driver1InfoId超过长度限制")
	private java.lang.String driver1InfoId; // 司机1信息标识

	@Length(max = 36, message = "driver2InfoId超过长度限制")
	private java.lang.String driver2InfoId; // 司机2信息标识

	@Length(max = 36, message = "fromDepartId超过长度限制")
	private java.lang.String fromDepartId; // 出借方

	@Length(max = 36, message = "toDepartId超过长度限制")
	private java.lang.String toDepartId; // 借用方

	@Length(max = 20, message = "startTime超过长度限制")
	private java.lang.String startTime; // 开始借用时间

	@Length(max = 20, message = "endTime超过长度限制")
	private java.lang.String endTime; // 计划归还时间

	@Length(max = 20, message = "realReturnedTime超过长度限制")
	private java.lang.String realReturnedTime; // 实际归还时间

	@Length(max = 10, message = "departureCode超过长度限制")
	private java.lang.String departureCode; // 线路出发地

	@Length(max = 10, message = "destinationCode超过长度限制")
	private java.lang.String destinationCode; // 线路目的地

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 36, message = "createdBy超过长度限制")
	private java.lang.String createdBy; // 创建人

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间

	@Length(max = 36, message = "updatedBy超过长度限制")
	private java.lang.String updatedBy; // 修改人

	@Length(max = 20, message = "updatedTime超过长度限制")
	private java.lang.String updatedTime; // 修改时间

	@Length(max = 2, message = "isDeleted超过长度限制")
	private java.lang.String isDeleted; // 是否删除
	
	public static final String EFFECT = "01";// 借用生效中
	public static final String INEFFICACY = "02";// 失效
}