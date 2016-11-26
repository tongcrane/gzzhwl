package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_agreement_info表
 * @author mew
 *
 */
@Data
@ToString
public class AgreementInfo implements Serializable {

	@Length(max = 36, message = "agreementId超过长度限制")
	private java.lang.String agreementId; // 合同标识

	@Length(max = 36, message = "customerId超过长度限制")
	private java.lang.String customerId; // 客户标识

	@Length(max = 10, message = "startCodeP超过长度限制")
	private java.lang.String startCodeP; // 起始区域(省)

	@Length(max = 10, message = "startCodeC超过长度限制")
	private java.lang.String startCodeC; // 起始区域(市)

	@Length(max = 10, message = "transferCodeP超过长度限制")
	private java.lang.String transferCodeP; // 中转区域(省)

	@Length(max = 10, message = "transferCodeC超过长度限制")
	private java.lang.String transferCodeC; // 中转区域(市)

	@Length(max = 10, message = "endCodeP超过长度限制")
	private java.lang.String endCodeP; // 终点区域(省)

	@Length(max = 10, message = "endCodeC超过长度限制")
	private java.lang.String endCodeC; // 终点区域(市)

	@Length(max = 10, message = "carryType超过长度限制")
	private java.lang.String carryType; // 运输方式

	@Length(max = 36, message = "chargeId超过长度限制")
	private java.lang.String chargeId; // 计费标识

	@Length(max = 10, message = "unitPrice超过长度限制")
	private java.lang.String unitPrice; // 单价

	@Length(max = 10, message = "lineAttribute超过长度限制")
	private java.lang.String lineAttribute; // 线路属性

	@Length(max = 10, message = "needType超过长度限制")
	private java.lang.String needType; // 车型要求

	@Length(max = 10, message = "needLength超过长度限制")
	private java.lang.String needLength; // 车长要求

	@Length(max = 2, message = "needImportedVehicles超过长度限制")
	private java.lang.String needImportedVehicles; // 要求进口车

	@Length(max = 2, message = "needOwnVehicles超过长度限制")
	private java.lang.String needOwnVehicles; // 要求志鸿车

	@Length(max = 10, message = "paymentType超过长度限制")
	private java.lang.String paymentType; // 结算方式

	@Length(max = 20, message = "startTime超过长度限制")
	private java.lang.String startTime; // 合同生效日期

	@Length(max = 20, message = "endTime超过长度限制")
	private java.lang.String endTime; // 合同结束日期

	@Length(max = 10, message = "executeCycle超过长度限制")
	private java.lang.String executeCycle; // 运行周期

	@Length(max = 200, message = "remark超过长度限制")
	private java.lang.String remark; // 备注

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
}