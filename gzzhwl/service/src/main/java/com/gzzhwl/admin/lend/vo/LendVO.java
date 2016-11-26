package com.gzzhwl.admin.lend.vo;

import org.apache.commons.lang3.StringUtils;

import com.gzzhwl.core.utils.JodaDateUtils;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LendVO {

	public java.lang.String getEndTime() {
		if (StringUtils.isNotBlank(endTime)) {
			return JodaDateUtils.parse(endTime, "yyyy-MM-dd").toString("yyyy-MM-dd");
		}
		return endTime;
	}

	private java.lang.String vehicleInfoId; // 车辆信息标识

	private java.lang.String driver1InfoId; // 司机1信息标识

	private java.lang.String driver2InfoId; // 司机2信息标识

	private java.lang.String toDepartId; // 借用方

	private java.lang.String endTime; // 计划归还时间

	private java.lang.String departureCode; // 线路出发地

	private java.lang.String destinationCode; // 线路目的地

}
