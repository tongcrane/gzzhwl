package com.gzzhwl.core.data.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


/**
 * 
 * zh_order_source_info表
 * @author mew
 *
 */
@Data
@ToString
public class OrderSourceInfo implements Serializable {

	@Length(max = 36, message = "sourceId超过长度限制")
	private java.lang.String sourceId; // 货源标识

	@Length(max = 36, message = "infoId超过长度限制")
	private java.lang.String infoId; // 信息标识

	@Length(max = 10, message = "startCodeP超过长度限制")
	private java.lang.String startCodeP; // 起始区域(省)

	@Length(max = 10, message = "startCodeC超过长度限制")
	private java.lang.String startCodeC; // 起始区域(市)

	@Length(max = 10, message = "endCodeP超过长度限制")
	private java.lang.String endCodeP; // 终点区域(省)

	@Length(max = 10, message = "endCodeC超过长度限制")
	private java.lang.String endCodeC; // 终点区域(市)

	@Length(max = 20, message = "needType超过长度限制")
	private java.lang.String needType; // 车型要求

	@Length(max = 20, message = "needLength超过长度限制")
	private java.lang.String needLength; // 车长要求

	@Length(max = 20, message = "needArriveTime超过长度限制")
	private java.lang.String needArriveTime; // 要求到达时间

	@Length(max = 2, message = "needDriverNum超过长度限制")
	private java.lang.String needDriverNum; // 要求司机人数

	@Length(max = 10, message = "miles超过长度限制")
	private java.lang.String miles; // 公里数

	@Length(max = 10, message = "level超过长度限制")
	private java.lang.String level; // 优先级

	@Length(max = 10, message = "estimatedPrice超过长度限制")
	private java.lang.String estimatedPrice; // 预计应付

	@Length(max = 36, message = "transitRequire超过长度限制")
	private java.lang.String transitRequire; // 在途要求

	@Length(max = 20, message = "startTime超过长度限制")
	private java.lang.String startTime; // 竞价开始时间

	@Length(max = 20, message = "endTime超过长度限制")
	private java.lang.String endTime; // 竞价结束时间

	@Length(max = 400, message = "remark超过长度限制")
	private java.lang.String remark; // 备注

	@Length(max = 400, message = "rejectReason超过长度限制")
	private java.lang.String rejectReason; // 拒绝理由

	@Length(max = 2, message = "status超过长度限制")
	private java.lang.String status; // 货源状态

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