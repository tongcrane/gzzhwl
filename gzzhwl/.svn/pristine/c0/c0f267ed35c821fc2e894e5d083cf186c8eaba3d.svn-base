package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_load_elecreceipt表
 * @author mew
 *
 */
@Data
@ToString
public class LoadElecreceipt implements Serializable {

	private java.lang.Long pid; // 自增序列

	@Length(max = 36, message = "receiptId超过长度限制")
	private java.lang.String receiptId; // 单据标识

	@Length(max = 36, message = "loadId超过长度限制")
	private java.lang.String loadId; // 提货单标识

	@Length(max = 20, message = "opTime超过长度限制")
	private java.lang.String opTime; // 上报时间

	@Length(max = 36, message = "receiptImageRefId超过长度限制")
	private java.lang.String receiptImageRefId; // 回单照片

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 2, message = "source超过长度限制")
	private java.lang.String source; // 数据来源

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