package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_customer_info表
 * @author mew
 *
 */
@Data
@ToString
public class CustomerInfo implements Serializable {

	@Length(max = 36, message = "customerId超过长度限制")
	private java.lang.String customerId; // 客户标识

	@Length(max = 50, message = "customerNo超过长度限制")
	private java.lang.String customerNo; // 客户编号

	@Length(max = 2, message = "type超过长度限制")
	private java.lang.String type; // 客户类型

	@Length(max = 50, message = "fullName超过长度限制")
	private java.lang.String fullName; // 客户全名

	@Length(max = 20, message = "taxRegCode超过长度限制")
	private java.lang.String taxRegCode; // 税务登记号

	@Length(max = 50, message = "contactName超过长度限制")
	private java.lang.String contactName; // 联系人

	@Length(max = 20, message = "telphone超过长度限制")
	private java.lang.String telphone; // 联系电话

	@Length(max = 20, message = "mobile超过长度限制")
	private java.lang.String mobile; // 联系手机

	@Length(max = 50, message = "email超过长度限制")
	private java.lang.String email; // 联系邮箱

	@Length(max = 10, message = "areaCode超过长度限制")
	private java.lang.String areaCode; // 所在区域

	@Length(max = 200, message = "address超过长度限制")
	private java.lang.String address; // 联系地址

	@Length(max = 2, message = "isAgreement超过长度限制")
	private java.lang.String isAgreement; // 是否合同

	@Length(max = 20, message = "agreementNo超过长度限制")
	private java.lang.String agreementNo; // 合同编码

	@Length(max = 400, message = "remark超过长度限制")
	private java.lang.String remark; // 备注

	@Length(max = 10, message = "status超过长度限制")
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
}