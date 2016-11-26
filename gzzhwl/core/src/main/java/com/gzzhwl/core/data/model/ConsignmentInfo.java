package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_consignment_info表
 * @author mew
 *
 */
@Data
@ToString
public class ConsignmentInfo implements Serializable {

	@Length(max = 36, message = "consignId超过长度限制")
	private java.lang.String consignId; // 合同标识

	@Length(max = 36, message = "orderId超过长度限制")
	private java.lang.String orderId; // 订单标识

	@Length(max = 50, message = "consignNo超过长度限制")
	private java.lang.String consignNo; // 合同编号

	@Length(max = 30, message = "freightPrice超过长度限制")
	private java.lang.String freightPrice; // 运费

	@Length(max = 10, message = "loadPrice超过长度限制")
	private java.lang.String loadPrice; // 装卸费

	@Length(max = 10, message = "otherPrice超过长度限制")
	private java.lang.String otherPrice; // 其他费用

	@Length(max = 100, message = "otherExplain超过长度限制")
	private java.lang.String otherExplain; // 其他费用说明

	@Length(max = 30, message = "total超过长度限制")
	private java.lang.String total; // 总费用

	@Length(max = 400, message = "remark超过长度限制")
	private java.lang.String remark; // 备注

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 合同状态

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