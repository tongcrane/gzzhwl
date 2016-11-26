package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_driver_bank_info表
 * @author mew
 *
 */
@Data
@ToString
public class DriverBankInfo implements Serializable {

	@Length(max = 36, message = "infoId超过长度限制")
	private java.lang.String infoId; // 信息标识

	@Length(max = 36, message = "driverInfoId超过长度限制")
	private java.lang.String driverInfoId; // 驾驶员信息标识

	@Length(max = 50, message = "cardName超过长度限制")
	private java.lang.String cardName; // 开户名

	@Length(max = 30, message = "cardNo超过长度限制")
	private java.lang.String cardNo; // 银行卡号

	@Length(max = 20, message = "depositBank超过长度限制")
	private java.lang.String depositBank; // 开户行

	@Length(max = 20, message = "bankName超过长度限制")
	private java.lang.String bankName; // 银行名称

	@Length(max = 10, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间

	@Length(max = 20, message = "updatedTime超过长度限制")
	private java.lang.String updatedTime; // 修改时间

	@Length(max = 2, message = "isDeleted超过长度限制")
	private java.lang.String isDeleted; // 是否删除
}