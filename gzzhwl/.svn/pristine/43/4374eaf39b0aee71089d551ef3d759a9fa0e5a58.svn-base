package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_consignment_his表
 * @author mew
 *
 */
@Data
@ToString
public class ConsignmentHis implements Serializable {

	@Length(max = 36, message = "hisId超过长度限制")
	private java.lang.String hisId; // 记录标识

	@Length(max = 36, message = "consignId超过长度限制")
	private java.lang.String consignId; // 合同标识

	@Length(max = 2, message = "srcStatus超过长度限制")
	private java.lang.String srcStatus; // 原始状态

	@Length(max = 2, message = "destStatus超过长度限制")
	private java.lang.String destStatus; // 目标状态

	@Length(max = 400, message = "msg超过长度限制")
	private java.lang.String msg; // 消息信息

	@Length(max = 36, message = "createdBy超过长度限制")
	private java.lang.String createdBy; // 创建人

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间
}