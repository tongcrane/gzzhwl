package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_payables_info表
 * @author mew
 *
 */
@Data
@ToString
public class PayablesInfo implements Serializable {

	@Length(max = 36, message = "contractId超过长度限制")
	private java.lang.String contractId; // 合同标识

	@Length(max = 10, message = "freightPrice超过长度限制")
	private java.lang.String freightPrice; // 纯运费

	@Length(max = 10, message = "prePay超过长度限制")
	private java.lang.String prePay; // 预付现金

	@Length(max = 10, message = "oilPay超过长度限制")
	private java.lang.String oilPay; // 预付油费

	@Length(max = 50, message = "oilCardNo超过长度限制")
	private java.lang.String oilCardNo; // 油卡卡号

	@Length(max = 10, message = "exceptionTotal超过长度限制")
	private java.lang.String exceptionTotal; // 异常总计

	@Length(max = 10, message = "total超过长度限制")
	private java.lang.String total; // 总计

	@Length(max = 36, message = "createdBy超过长度限制")
	private java.lang.String createdBy; // 创建人

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间
}