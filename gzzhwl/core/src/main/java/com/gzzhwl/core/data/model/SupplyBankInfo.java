package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_supply_bank_info表
 * @author mew
 *
 */
@Data
@ToString
public class SupplyBankInfo implements Serializable {

	@Length(max = 36, message = "supplyId超过长度限制")
	private java.lang.String supplyId; // 供应商标识

	@Length(max = 50, message = "cardName超过长度限制")
	private java.lang.String cardName; // 开户名

	@Length(max = 30, message = "cardNo超过长度限制")
	private java.lang.String cardNo; // 银行卡号

	@Length(max = 20, message = "depositBank超过长度限制")
	private java.lang.String depositBank; // 开户行

	@Length(max = 20, message = "bankName超过长度限制")
	private java.lang.String bankName; // 银行名称

	@Length(max = 50, message = "invoiceTitle超过长度限制")
	private java.lang.String invoiceTitle; // 发票抬头
}