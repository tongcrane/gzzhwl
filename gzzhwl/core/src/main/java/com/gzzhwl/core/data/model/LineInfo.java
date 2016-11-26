package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_line_info表
 * @author mew
 *
 */
@Data
@ToString
public class LineInfo implements Serializable {

	private java.lang.Long pid; // 

	@Length(max = 36, message = "lineInfoId超过长度限制")
	private java.lang.String lineInfoId; // 线路标识

	@Length(max = 36, message = "accountId超过长度限制")
	private java.lang.String accountId; // 账户标识

	@Length(max = 36, message = "driverInfoId超过长度限制")
	private java.lang.String driverInfoId; // 驾驶员信息标识

	@Length(max = 10, message = "departureCode超过长度限制")
	private java.lang.String departureCode; // 出发地所在区域

	@Length(max = 10, message = "destinationCode超过长度限制")
	private java.lang.String destinationCode; // 目的地所在区域

	@Length(max = 2, message = "source超过长度限制")
	private java.lang.String source; // 数据来源

	@Length(max = 36, message = "createdBy超过长度限制")
	private java.lang.String createdBy; // 创建人

	@Length(max = 20, message = "createdTime超过长度限制")
	private java.lang.String createdTime; // 创建时间

	@Length(max = 20, message = "updatedTime超过长度限制")
	private java.lang.String updatedTime; // 修改时间

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 状态

	@Length(max = 2, message = "isDeleted超过长度限制")
	private java.lang.String isDeleted; // 是否删除
}