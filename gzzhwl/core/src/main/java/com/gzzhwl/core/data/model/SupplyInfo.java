package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_supply_info表
 * @author mew
 *
 */
@Data
@ToString
public class SupplyInfo implements Serializable {

	@Length(max = 36, message = "supplyId超过长度限制")
	private java.lang.String supplyId; // 供应商标识

	@Length(max = 50, message = "supplyNo超过长度限制")
	private java.lang.String supplyNo; // 供应商编号

	@Length(max = 2, message = "type超过长度限制")
	private java.lang.String type; // 供应商类型

	@Length(max = 50, message = "fullName超过长度限制")
	private java.lang.String fullName; // 供应商全名

	@Length(max = 20, message = "taxRegCode超过长度限制")
	private java.lang.String taxRegCode; // 身份证号/税务登记号

	@Length(max = 50, message = "contactName超过长度限制")
	private java.lang.String contactName; // 联系人

	@Length(max = 20, message = "mobile超过长度限制")
	private java.lang.String mobile; // 联系手机

	@Length(max = 10, message = "areaCode超过长度限制")
	private java.lang.String areaCode; // 所在区域

	@Length(max = 200, message = "address超过长度限制")
	private java.lang.String address; // 联系地址

	@Length(max = 10, message = "paymentType超过长度限制")
	private java.lang.String paymentType; // 结算方式

	@Length(max = 400, message = "remark超过长度限制")
	private java.lang.String remark; // 备注

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 36, message = "belongDepartId超过长度限制")
	private java.lang.String belongDepartId; // 所属部门

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

	@Length(max = 2, message = "source超过长度限制")
	private java.lang.String source; // 数据来源
}