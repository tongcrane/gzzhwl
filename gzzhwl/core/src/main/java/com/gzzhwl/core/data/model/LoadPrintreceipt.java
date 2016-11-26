package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_load_printreceipt表
 * @author mew
 *
 */
@Data
@ToString
public class LoadPrintreceipt implements Serializable {

	private java.lang.Long pid; // 自增序列

	@Length(max = 36, message = "receiptId超过长度限制")
	private java.lang.String receiptId; // 单据标识

	@Length(max = 36, message = "loadId超过长度限制")
	private java.lang.String loadId; // 提货单标识

	@Length(max = 2, message = "type超过长度限制")
	private java.lang.String type; // 提交方式

	@Length(max = 20, message = "expressDelivery超过长度限制")
	private java.lang.String expressDelivery; // 快递公司

	@Length(max = 50, message = "billNo超过长度限制")
	private java.lang.String billNo; // 快递单号

	@Length(max = 20, message = "signTime超过长度限制")
	private java.lang.String signTime; // 签收时间

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
	
	public static final String TYPE_OFFLINE = "00"; // 线下
	
	public static final String TYPE_EXPRESS = "01"; // 快递
	
}